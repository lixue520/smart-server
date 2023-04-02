package cn.edu.guet.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 18:36
 * 腐蚀与膨胀
 */
public class dilate {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
        //Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat dst = new Mat();
        Imgproc.erode(src,dst,new Mat());//腐蚀操作
        HighGui.imshow("腐蚀",dst);
        HighGui.waitKey(0);

        Imgproc.dilate(src,dst,new Mat());
        HighGui.imshow("膨胀",dst);
        HighGui.waitKey(0);
        //形态学操作，MORPH_OPEN先腐蚀再膨胀=>开运算，这里的迭代次数为3
        Imgproc.morphologyEx(src,dst,Imgproc.MORPH_OPEN,new Mat(),new Point(-1,-1),3);
        HighGui.imshow("变形",dst);
        HighGui.waitKey(0);

    }
}








