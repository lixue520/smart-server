package cn.edu.guet.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 15:43
 */
public class AddWeighted {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src1= Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b3.jpg");
        Mat src2= Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
//        System.out.println(src1.reshape(1));
//        System.out.println(src2.reshape(1));
        Mat dst = new Mat();//目标图像
        //Core.add(src1,src2,dst);
        //Core.addWeighted(src1,0.5,src2,0.5,0,dst);//加权平均
        Core.bitwise_not(src1,dst);//图像的反相,差不多就是取底片
        HighGui.imshow("mixed",dst);
        HighGui.waitKey(0);

    }
}
