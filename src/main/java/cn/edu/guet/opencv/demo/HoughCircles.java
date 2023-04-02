package cn.edu.guet.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 22:49
 */
public class HoughCircles {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b3.jpg");
        Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图

        Mat gray = new Mat();
        Imgproc.cvtColor(src,gray,Imgproc.COLOR_GRAY2BGR);
        Imgproc.medianBlur(gray,gray,5);
        HighGui.imshow("加中值滤波的灰度图",gray);
        HighGui.waitKey(0);
        /**
         * 	函数：Imgproc.HoughCircles(Mat image, Mat circles, int method, double dp, double minDist,
         * 	double param1, double param2, int minRadius, int maxRadius)；
         * 【参数涵义】
         * image：输入图像（灰度图像）
         * method：检测算法，Imgproc.HOUGH_GRADIENT代表霍夫梯度法:
         * dp：霍夫空间的分辨率；
         * minDist为圆心之间的最小距离，如果检测到两个圆心距离小于该值，则认为它们是同一个圆心；
         * param1：边缘检测时使用Canny算子的高阈值，低阈值是高阈值的一半；
         * param2：检测圆心和确定半径时所共有的阈值；
         * minRadius和maxRadius为所检测到的圆半径的最小值和最大值；
         */
        Mat circles = new Mat();
        Imgproc.HoughCircles(gray,circles,Imgproc.HOUGH_GRADIENT,
                1.0,20,5.0,50.0,20,50);

        for(int x=0;x<circles.cols();x++){
            double[] c= circles.get(0,x);
            Point center = new Point(Math.round(c[0]),Math.round(c[1])); //圆心
            int radius = (int) Math.round(c[2]);
            Imgproc.circle(src,center,radius,new Scalar(255,0,255),3,8,0);
        }

        HighGui.imshow("Circles",src);
        HighGui.waitKey();
        System.exit(0);



    }
}
















