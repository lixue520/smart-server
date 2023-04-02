package cn.edu.guet.opencv.combat;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/21 4:04
 */
public class testAutoCanny {
    public static void main(String[] args) {

        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b7.jpeg");
        // 转换为灰度图像
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        // 计算Otsu阈值
        Mat thresh = new Mat();
        double otsuThreshVal = Imgproc.threshold(gray, thresh, 50, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        // 自适应计算高斯尺寸
        int gaussianSize = (int) Math.round(otsuThreshVal / 2) * 2 + 1;

        // 自适应计算Canny边缘检测参数
        double cannyLowerThreshold = otsuThreshVal / 2;
        double cannyUpperThreshold = cannyLowerThreshold * 3;

        // 高斯滤波
        Mat blurred = new Mat();
        Imgproc.GaussianBlur(gray, blurred, new Size(gaussianSize, gaussianSize), 0, 0);

        // Canny边缘检测
        Mat edges = new Mat();
        Imgproc.Canny(blurred, edges, cannyLowerThreshold, cannyUpperThreshold);

        // 显示结果
        HighGui.imshow("edgs",edges);
        HighGui.waitKey(0);
    }
}
