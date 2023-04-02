package cn.edu.guet.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 20:16
 * 边缘检测算法
 */
public class DetectEdge {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b6.jpg",Imgcodecs.IMREAD_GRAYSCALE);
//        Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat dst = new Mat();
        Imgproc.Sobel(src,dst,-1,0,1);

        HighGui.imshow("sobel",dst);
        HighGui.waitKey(0);

        Imgproc.Scharr(src,dst,-1,0,1);
        HighGui.imshow("scharr",dst);
        HighGui.waitKey(0);

        Imgproc.GaussianBlur(src,dst,new Size(31,5),80,3);
        Imgproc.Laplacian(src,dst,0);
        HighGui.imshow("Laplacian",dst);
        HighGui.waitKey(0);

        Imgproc.Canny(src,dst,60,200);
        HighGui.imshow("Canny",dst);
        HighGui.waitKey(0);

    }
}
