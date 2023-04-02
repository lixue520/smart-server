package cn.edu.guet.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 19:13
 * 图像金字塔，向上或向下采样图片
 */
public class pyramid {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
        //Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat dst = new Mat();

        Imgproc.pyrDown(src,dst);
        HighGui.imshow("Down",dst);
        HighGui.waitKey(0);

        Imgproc.pyrUp(dst,dst);
        HighGui.imshow("Up",dst);
        HighGui.waitKey(0);

        Mat mat = new Mat(src.size(), CvType.CV_64F);
        Core.subtract(dst,src,mat);//图像想减，取轮廓
        HighGui.imshow("Lap",mat);
        HighGui.waitKey(0);
        System.out.println("Running Ok!");
    }
}
