package cn.edu.guet.opencv;

import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.opencv.imgcodecs.Imgcodecs.imread;

/**
 * 加载输入图像并将其缩小一半（为了提高处理速度）。
 * 将图像转换为灰度图像，并使用Canny边缘检测算法提取瓶子区域。
 * 过滤掉非瓶子形状的轮廓，并找到包含瓶子的最大轮廓。然后从瓶子区域中提取肩部点和颈部点。
 * 计算液体的体积百分比。首先计算瓶子的体积（假设每个像素代表0.1厘米），然后从肩部到液位处提取液位曲线并进行二值化处理。计算液体区域的像素数，然后根据像素大小将其转换为体积（以立方厘米为单位）。
 * 在输出图像上绘制检测结果，并保存输出图像。
 */
public class LiquidLevelDetector {
    public static void main(String[] args) {

        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat inputImage = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/a1.jpg");
        Mat resizedImage = new Mat();
        Size newSize = new Size(inputImage.width() * 0.5, inputImage.height() * 0.5);
        Imgproc.resize(inputImage, resizedImage, newSize);
        Mat grayImage = new Mat();
        Imgproc.cvtColor(resizedImage, grayImage, Imgproc.COLOR_BGR2GRAY);

        // Extract bottle region using Canny edge detection
        Mat edges = new Mat();
        Imgproc.Canny(grayImage, edges, 50, 200);
        Mat hierarchy = new Mat();
//        List<Mat> contours = new ArrayList();
        List<MatOfPoint> contours = new ArrayList<>();

        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        double maxArea = -1;
        Rect bottleRect = null;
        for (int i = 0; i < contours.size(); i++) {
            Mat contour = contours.get(i);
            double area = Imgproc.contourArea(contour);
            if (area > 10000) { // Filter out small contours
                Rect rect = Imgproc.boundingRect(contour);
                double aspectRatio = (double) rect.width / rect.height;
                if (aspectRatio > 0.5 && aspectRatio < 2.0) { // Filter out non-bottle-like contours
                    if (area > maxArea) {
                        maxArea = area;
                        bottleRect = rect;
                    }
                }
            }
        }

        // Extract shoulder point and neck point
        Mat bottleRegion = new Mat(grayImage, bottleRect);
        Mat bottleEdges = new Mat();
        Imgproc.Canny(bottleRegion, bottleEdges, 50, 200);
        Mat bottleHierarchy = new Mat();

        List<MatOfPoint> bottleContours = new ArrayList<>();
        Imgproc.findContours(bottleEdges, bottleContours, bottleHierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
        Point shoulder = null;
        Point neck = null;
        double minShoulderY = Double.MAX_VALUE;
        for (int i = 0; i < bottleContours.size(); i++) {
            Mat contour = bottleContours.get(i);
            double area = Imgproc.contourArea(contour);//等高线面积
            if (area > 100) { // Filter out small contours/过滤掉小轮廓
                Point[] points = getContourPoints(contour);//获取轮廓点
                double minX = Double.MAX_VALUE;
                double maxX = Double.MIN_VALUE;
                double minY = Double.MAX_VALUE;
                double maxY = Double.MIN_VALUE;
                for (Point point : points) {
                    if (point.y < minShoulderY) {
                        minShoulderY = point.y;
                        shoulder = new Point(point.x + bottleRect.x, point.y + bottleRect.y);
                    }
                    if (point.y > maxY) {
                        maxY = point.y;
                        neck = new Point(point.x + bottleRect.x, point.y + bottleRect.y);
                    }
                }
            }
        }

        // Compute liquid level percentage
        double bottleVolumeCm3 = bottleRect.width * bottleRect.height * 10.0; // Assume 1 pixel = 0.1 cm
        Mat liquidLevelCurve = new Mat(grayImage, new Rect((int) shoulder.x, (int) shoulder.y,
                (int) (neck.x - shoulder.x), (int) (bottleRect.height - (neck.y - bottleRect.y))));
        Mat liquidLevelGray = new Mat();
        Imgproc.cvtColor(liquidLevelCurve, liquidLevelGray, Imgproc.COLOR_BGR2GRAY);
        Mat liquidLevelThresh = new Mat();
        Imgproc.threshold(liquidLevelGray, liquidLevelThresh, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        double liquidArea = Core.countNonZero(liquidLevelThresh);
        double liquidVolumeCm3 = liquidArea / liquidLevelThresh.cols() * bottleRect.width * bottleRect.height * 10.0; // Assume 1 pixel = 0.1 cm
        double liquidPercentage = liquidVolumeCm3 / bottleVolumeCm3 * 100.0;

        // Draw result on output image and save
        Imgproc.rectangle(resizedImage, bottleRect, new Scalar(0, 255, 0), 2);
        Imgproc.line(resizedImage, shoulder, neck, new Scalar(0, 0, 255), 2);
        Imgproc.putText(resizedImage, String.format("%.1f%%", liquidPercentage),
                new Point(bottleRect.x, bottleRect.y - 20), Imgproc.FONT_HERSHEY_PLAIN, 2, new Scalar(0, 0, 255), 2);
        Imgcodecs.imwrite("output.jpg", resizedImage);
    }

    private static Point[] getContourPoints(Mat contour) {
        Point[] points = new Point[contour.cols()];
        for (int j = 0; j < contour.cols(); j++) {
            points[j] = new Point(contour.get(0, j));
        }
        return points;
    }
}

