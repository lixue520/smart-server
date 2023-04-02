package cn.edu.guet.opencv.demo;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;



/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 11:31
 * opencv基础之读取图片Mat函数的运用
 */
public class ReadFile {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        // Load OpenCV library and initialize it
        //System.loadLibrary(Core.NATIVE_LIBRARY_NAME);-->读取opencv的路径由于环境环境变量可能没有就先加静态引用了
        Mat src = Imgcodecs.imread("C:\\Users\\qin\\Desktop\\code" +
                "\\smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\b5.jpg");//读取jpg文件到src中(矩阵化,图像的本质是矩阵)
        HighGui.imshow("bottle",src);//在屏幕显示图像
        HighGui.waitKey(0);//等待xms,0表示任意退出
        System.out.println("opencv show Source img is ok");

        Mat mat = Imgcodecs.imread("C:\\Users\\qin\\Desktop\\code\\" +
                "smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\b5.jpg",Imgcodecs.IMREAD_GRAYSCALE);//读灰度图
        Imgcodecs.imwrite("b4_gray.jpg",mat);
        System.out.println("opencv show gray img is ok");

    }
}
