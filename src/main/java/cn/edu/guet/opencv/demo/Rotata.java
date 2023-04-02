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
 * @Date 2023/3/19 16:16
 * 图像旋转与缩放
 */
public class Rotata {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b3.jpg");
//        HighGui.imshow("lena",src);
//        HighGui.waitKey(0);
        Mat dst = src.clone();
        Point center = new Point(src.width()/2.0,src.height()/2.0);//取中心点
        Mat affineTrans = Imgproc.getRotationMatrix2D(center,33.0,0.2);//中心点，旋转角，缩放比例1.0为不缩放(放射变化矩阵)
        Imgproc.warpAffine(src,dst,affineTrans,dst.size(),Imgproc.INTER_NEAREST);//翘曲仿射
        HighGui.imshow("lena",dst);
        HighGui.waitKey(0);
    }
}
