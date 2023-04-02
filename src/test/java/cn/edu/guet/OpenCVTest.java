package cn.edu.guet;

import org.junit.jupiter.api.Test;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.FeatureDetector;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.*;

import static org.opencv.core.Core.merge;
import static org.opencv.core.Core.split;
import static org.opencv.highgui.HighGui.imshow;
import static org.opencv.highgui.HighGui.waitKey;
import static org.opencv.imgcodecs.Imgcodecs.imread;
import static org.opencv.imgproc.Imgproc.equalizeHist;
/**
 * @ClassName OpenCVTest
 * @Description TODO
 * @Author L
 * @Date Create by 2020/1/11
 */
public class OpenCVTest {

    //这段代码只有关键点是错的的
    @Test
    public void test(){
        // 加载OpenCV库
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");
        System.load(url.getPath());
        // 读取待检测水桶液位的图片
        Mat src = Imgcodecs.imread("C:\\Users\\qin\\Desktop\\code\\smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\b4.jpg");

        // 图像预处理
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.blur(gray, gray, new Size(3, 3));
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        // 形态学操作
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Mat opened = new Mat();
        Imgproc.morphologyEx(binary, opened, Imgproc.MORPH_OPEN, kernel);

        // 轮廓检测
        Mat contoursImage = src.clone();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(opened, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        for (int i = 0; i < contours.size(); i++) {
            double area = Imgproc.contourArea(contours.get(i));
            if (area > 5000 && area < 100000) { // 根据实际情况调整筛选条件
                Imgproc.drawContours(contoursImage, contours, i, new Scalar(0, 255, 0), 2);
            }
        }

        // 关键点检测
        Mat keyPointsImage = src.clone();
        MatOfPoint2f corners = new MatOfPoint2f();
        Imgproc.cornerHarris(gray, corners, 2, 3, 0.04);
        Mat cornerStrength = new Mat();
        Core.normalize(corners, cornerStrength, 0, 255, Core.NORM_MINMAX, CvType.CV_8UC1);
        List<Point> cornerList = new ArrayList<>();
        for (int i = 0; i < cornerStrength.rows(); i++) {
            for (int j = 0; j < cornerStrength.cols(); j++) {
                double[] point = cornerStrength.get(i, j);
                if (point[0] > 50) { // 根据实际情况调整关键点筛选条件
                    Point p = new Point(j, i);
                    cornerList.add(p);
                }
            }
        }
        MatOfPoint points = new MatOfPoint();
        points.fromList(cornerList);
        Imgproc.drawContours(keyPointsImage, Collections.singletonList(points), -1, new Scalar(0, 255, 0), 2);

        // 液位曲线提取和液位百分比计算
        // 这部分需要根据具体的算法和方法进行实现

        // 显示结果
        HighGui.imshow("Original", src);
        HighGui.imshow("Contours", contoursImage);
        HighGui.imshow("Key Points", keyPointsImage);
        HighGui.waitKey();
    }

    @Test
    public void testopencv2(){
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");
        System.load(url.getPath());
        // Load OpenCV library and initialize it
       // System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Read image from file
        String imagePath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\b5.jpg";
        Mat src = Imgcodecs.imread(imagePath);

        // Convert image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply threshold to segment the bottle
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY+Imgproc.THRESH_OTSU);

        // Find contours of the segmented bottle
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // Find the largest contour (assumed to be the bottle)
        int largestIndex = -1;
        double largestArea = 0;
        for (int i = 0; i < contours.size(); i++) {
            double area = Imgproc.contourArea(contours.get(i));
            if (area > largestArea) {
                largestArea = area;
                largestIndex = i;
            }
        }

        // Calculate the percentage of the bottle filled with liquid
        double bottleHeight = binary.rows();
        double liquidHeight = bottleHeight - Imgproc.boundingRect(contours.get(largestIndex)).y;
        double percentageFilled = liquidHeight / bottleHeight * 100;

        // Print the percentage filled
        System.out.println("Bottle is " + percentageFilled + "% filled.");
    }
    /**
     * 测试OpenCV是否能运行：需要自行修改图片位置
     * @throws Exception 测试是否成功
     */
    @Test
    public void testOpenCV() throws Exception {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");
        System.load(url.getPath());
        //填你的图片地址
        Mat image = imread("C:\\Users\\qin\\Desktop\\code\\smart-server\\src\\main\\resources\\static\\DavidNan.jpg", 1);
        if (image.empty()){
            throw new Exception("image is empty!");
        }
        imshow("Original Image", image);
        List<Mat> imageRGB = new ArrayList<>();
        split(image, imageRGB);
        for (int i = 0; i < 3; i++) {
            equalizeHist(imageRGB.get(i), imageRGB.get(i));
        }
        merge(imageRGB, image);
        imshow("Processed Image", image);
        waitKey();
    }

    /**
     * 提取水桶区域、计算水桶容积、检测液位高度、判断水桶是否被装满和输出未装满液位占水桶容量的百分比等功能
     * ，我们首先使用Imgcodecs.imread()函数从文件中读取输入图像，然后使用OpenCV的核心类Mat表示图像数据。
     * 然后，我们将图像转换为灰度图像，使用形态学开运算操作对图像进行处理，并使用Canny算子提取轮廓信息。接下来，
     * 我们计算水桶体积，检测液位高度，并根据液位高度和水桶体积计算未装满液位占水桶容量的百分比。最后，我们打印结果。
     *
     * 请注意，此代码假设水桶底部与图像底部重合，因此液位高度是从图像底部开始计算的。如果水桶位置不是固定的，
     * 可能需要进行更多的处理和计算来准确确定液位高度。
     * 有错但可能合理
     */

    @Test
    public void Water_tank_level() {
//        // 加载OpenCV库
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        // 读取待检测水瓶液位的图片
//        Mat src = Imgcodecs.imread("b4.jpg");
//
//        // 图像预处理
//        Mat gray = new Mat();
//        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
//        Imgproc.blur(gray, gray, new Size(3, 3));
//        Mat binary = new Mat();
//        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);
//
//        // 形态学操作
//        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
//        Mat opened = new Mat();
//        Imgproc.morphologyEx(binary, opened, Imgproc.MORPH_OPEN, kernel);
//
//        // 轮廓检测
//        List<MatOfPoint> contours = new ArrayList<>();
//        Imgproc.findContours(opened, contours, new Mat(), Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
//        double maxArea = -1;
//        int maxIndex = -1;
//        for (int i = 0; i < contours.size(); i++) {
//            double area = Imgproc.contourArea(contours.get(i));
//            if (area > maxArea) {
//                maxArea = area;
//                maxIndex = i;
//            }
//        }
//
//        // 提取水瓶轮廓和液体轮廓
//        Mat bottleContour = new Mat();
//        Mat levelContour = new Mat();
//        if (maxIndex >= 0) {
//            MatOfPoint2f contour = new MatOfPoint2f();
//            Imgproc.approxPolyDP(new MatOfPoint2f(contours.get(maxIndex).toArray()), contour, 10, true);
//            Mat hierarchy = new Mat();
//            Imgproc.findContours(opened, contours, hierarchy, Imgproc.RETR_CCOMP, Imgproc.CHAIN_APPROX_SIMPLE);
//            bottleContour = contours.get(maxIndex);
//            while (hierarchy.get(0, maxIndex)[2] != -1) {
//                maxIndex = (int) hierarchy.get(0, maxIndex)[2];
//                levelContour = contours.get(maxIndex);
//            }
//        }
//
//        // 计算液位百分比
//        double levelPercent = -1;
//        if (!levelContour.empty()) {
//            Rect boundingBox = Imgproc.boundingRect(levelContour);
//            int topY = boundingBox.y;
//            for (int i = boundingBox.y; i < boundingBox.y + boundingBox.height; i++) {
//                boolean foundForeground = false;
//                for (int j = boundingBox.x; j < boundingBox.x + boundingBox.width; j++) {
//                    if (Imgproc.pointPolygonTest(new MatOfPoint2f(levelContour.toArray()), new Point(j, i), false) >= 0) {
//                        foundForeground = true;
//                        break;
//                    }
//                }
//                if (foundForeground) {
//                    topY = i;
//                    break;
//                }
//            }
//            int bottomY = boundingBox.y + boundingBox.height - 1;
//            for (int i = boundingBox.y + boundingBox.height - 1; i >= boundingBox.y; i--) {
//                boolean foundForeground = false;
//                for (int j = boundingBox.x; j < boundingBox.x + boundingBox.width; j++) {
//                    if (Imgproc.pointPolygonTest(new MatOfPoint2f(levelContour.toArray()), new Point(j, i), false) >= 0) {
//                        foundForeground = true;
//                        break;
//                    }
//                }
//                if (foundForeground) {
//                    bottomY = i;
//                    break;
//                }
//            }
//            levelPercent = ((double) (bottomY - topY) / boundingBox.height) * 100;
//        }
//
//        // 显示结果和打印液位百分比
//        Scalar bottleColor = new Scalar(0, 255, 0); // 绿色
//        Scalar levelColor = new Scalar(0, 0, 255); // 红色
//        HighGui.imshow("Original", src);
//        if (!bottleContour.empty()){
//            Imgproc.drawContours(src, List.of(bottleContour), 0, bottleColor, 2);
//        }
//        if (!levelContour.empty()) {
//            Imgproc.drawContours(src, List.of(levelContour), 0, levelColor, 2);
//        }
//        if (levelPercent >= 0) {
//            System.out.println("The water level is at " + levelPercent + "%");
//        } else {
//            System.out.println("Could not detect the water level.");
//        }
//        HighGui.imshow("Result", src);
//        HighGui.waitKey();
//    }

    }

    @Test
    public void m(){
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");
        System.load(url.getPath());
      //  System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat src = Imgcodecs.imread("C:\\Users\\qin\\Desktop\\code\\smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\b5.jpg");
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(gray, gray, new org.opencv.core.Size(5, 5), 0);

        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, 50, 150, 3, false);

        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

        int maxIndex = -1;
        double maxArea = 0;
        for (int i = 0; i < contours.size(); i++) {
            double area = Imgproc.contourArea(contours.get(i));
            if (area > maxArea) {
                maxIndex = i;
                maxArea = area;
            }
        }

        MatOfPoint bottleContour = contours.get(maxIndex);
        Mat mask = Mat.zeros(src.size(), CvType.CV_8UC1);
        Imgproc.drawContours(mask, Collections.singletonList(bottleContour), 0, new Scalar(255), -1);
        Core.bitwise_and(gray, mask, gray);

        // Convert contour to a format that can be used by pointPolygonTest()
        MatOfPoint2f contour = new MatOfPoint2f();
        bottleContour.convertTo(contour, CvType.CV_32F);

        // Find the water level
        Mat leveled = new Mat();
        Mat levelMask = Mat.zeros(src.size(), CvType.CV_8UC1);
        Imgproc.threshold(gray, leveled, 0, 255, Imgproc.THRESH_BINARY_INV | Imgproc.THRESH_OTSU);
        List<MatOfPoint> levelContours = new ArrayList<>();
        Imgproc.findContours(leveled, levelContours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        MatOfPoint levelContour = new MatOfPoint();
        double levelPercent = -1;
        if (!levelContours.isEmpty()) {
            for (int i = 0; i < levelContours.size(); i++) {
                MatOfPoint2f levelContour2f = new MatOfPoint2f();
                levelContours.get(i).convertTo(levelContour2f, CvType.CV_32F);
                Imgproc.approxPolyDP(levelContour2f, levelContour2f, 3, true);
                levelContour2f.convertTo(levelContours.get(i), CvType.CV_32S);
            }
            double maxHeight = Imgproc.boundingRect(levelContours.get(0)).height;
            for (int i = 0; i < levelContours.size(); i++) {
                double height = Imgproc.boundingRect(levelContours.get(i)).height;
                if (height > maxHeight) {
                    levelContour = levelContours.get(i);
                    maxHeight = height;
                }
            }
           // Imgproc.drawContours(levelMask, Collections.singletonList(levelContour), 0, new Scalar(255), -1);
            Core.bitwise_and(leveled, levelMask, leveled);
            levelPercent = 100 * Core.countNonZero(leveled) / (double)mask.size().area();
        }

        // Draw results
        Scalar bottleColor = new Scalar(0, 255, 0);
        Scalar levelColor = new Scalar(255, 0, 0);
        Imgproc.drawContours(src, Collections.singletonList(bottleContour), 0, bottleColor, 2);
        if (!levelContour.empty()) {
            Imgproc.drawContours(src, Collections.singletonList(levelContour), 0, levelColor, 2);
        }
        if (levelPercent >= 0) {
            System.out.println("The water level is at " + levelPercent + "%");
        } else {
            System.out.println("Could not detect the water level.");
        }
        HighGui.imshow("Result", src);
        HighGui.waitKey();
        System.exit(0);
    }


}



