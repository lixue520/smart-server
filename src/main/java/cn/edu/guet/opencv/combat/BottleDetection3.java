package cn.edu.guet.opencv.combat;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/20 22:35
 */
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;


public class BottleDetection3 {
    public static void main(String[] args) {
        // 加载OpenCV库

        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/a1.jpg");
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);



        // 对灰度图像进行二值化处理
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 100, 255, Imgproc.THRESH_BINARY);

        // 对二值化后的图像进行形态学操作
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(3, 3));
        Imgproc.dilate(binary, binary, kernel);
        Imgproc.erode(binary, binary, kernel);



        // 显示结果
        Imgcodecs.imwrite("result.jpg", src);
    }
}

/**
 *  Rect rect = Imgproc.boundingRect(new MatOfPoint(contours.get(i, 0))));
 *             Imgproc.rectangle(src, rect.tl(), rect.br(), new Scalar(0, 255, 0), 2);
 */