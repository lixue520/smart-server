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
 * @Date 2023/3/19 17:34
 *1.阈值化与二值图像：
 * Imgproc.threshold(Mat src, Mat dst, double thresh, double maxval, int type)；
 * 其中type：二值化操作类型，包含下面5种，其中第一种最基本：
 * Imgproc.THRESH_BINARY：当像素值超过阈值thresh时取maxval，否则取0；
 * Imgproc.THRESH_BINARY_INV：Imgproc.THRESH_BINARY的反转；
 * Imgproc.THRESH_TRUNC：大于阈值时设为阈值，否则不变；
 * Imgproc.THRESH_TOZERO：大于阈值时不变，否则设为0；
 * Imgproc.THRESH_TOZERO_INV：Imgproc.THRESH_TOZERO的反转；
 */
public class blur {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
        Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat mat = new Mat();
//        Imgproc.threshold(src,mat,125,255,0);//二值化：像素大于125就转为255(白色)，否则就是0(灰色)
//        HighGui.imshow("thresh",mat);
//        HighGui.waitKey(0);

        /*滤波函数运用*/
        /**
         * 均值滤波：Imgproc.blur(Mat src, Mat dst, Size ksize)；			//简单的算术平均
         * 高斯滤波：Imgproc.GaussianBlur(Mat src, Mat dst, Size ksize,
         * double sigmaX, double sigmaY)；	//靠近中心权重高
         * 中值滤波：Imgproc.medianBlur(Mat src, Mat dst, int ksize)；		//去除椒盐噪声
         */
        Mat dst = new Mat();
        //Imgproc.blur(src,dst,new Size(3,3));//普通过滤波：Size()卷积和，将矩阵中每一个像素拿来做卷积和
        //Imgproc.GaussianBlur(src,dst,new Size(5,5),10,10);//高斯过滤波
        Imgproc.medianBlur(src,dst,2);//中值滤波
        HighGui.imshow("b4",dst);
        HighGui.waitKey(0);

        System.out.println("OK");

    }
}
