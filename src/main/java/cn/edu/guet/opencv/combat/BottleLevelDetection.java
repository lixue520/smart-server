package cn.edu.guet.opencv.combat;

import java.net.URL;

import org.opencv.core.Mat;

import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * 读取图像并将其转换为灰度图像。
 *
 * 对图像进行高斯模糊，以去除噪声。
 *
 * 使用Canny边缘检测算法找到瓶子的边缘。
 *
 * 对边缘进行霍夫变换，找到瓶子的直线。
 *
 * 根据瓶子的直线，确定瓶子的肩部和瓶颈的位置。
 *
 * 根据瓶颈和肩部的位置，计算液位的高度。
 */

public class BottleLevelDetection {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/a1.jpg");
        Mat gray = new Mat();

        Size newSize = new Size(src.width() * 0.5, src.height() * 0.5);
        Mat dst = new Mat();//目标图像
        Imgproc.resize(src, dst, newSize);//缩放一半
        /**
         * 1.灰度和高斯滤波
         */
        Imgproc.cvtColor(dst, gray, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(gray, gray, new Size(3, 3), 0.2);
        HighGui.imshow("灰度和高斯滤波",gray);
        HighGui.waitKey(0);

        /**
         * Canny寻找轮廓
         */
        Mat edges = new Mat();
        Imgproc.Canny(gray, edges, 30, 150);
        HighGui.imshow("CannyEdges",edges);
        HighGui.waitKey(0);

        Mat lines = new Mat();
        Imgproc.HoughLines(edges, lines, 1, Math.PI / 180, 80);
        /**
         * 1.调整线段阈值可以找到合适的线段作为液位线这里为160,要根据瓶子形状修改
          */
        double x1 = 0, y1 = 0, x2 = 0, y2 = 0;
        Point op1,op2;//目标点
        for (int i = 0; i < lines.rows(); i++) {
            double[] data = lines.get(i, 0);
            double rho = data[0];
            double theta = data[1];
            double a = Math.cos(theta);
            double b = Math.sin(theta);
            double x0 = a * rho;
            double y0 = b * rho;
            Point pt1 = new Point(Math.round(x0 + 1000 * (-b)), Math.round(y0 + 1000 * a));
            Point pt2 = new Point(Math.round(x0 - 1000 * (-b)), Math.round(y0 - 1000 * a));
            Imgproc.line(dst, pt1, pt2, new Scalar(0, 0, 255), 3, Imgproc.LINE_AA, 0);   //检测到阈值内红色线段3条
            if (Math.abs(theta - Math.PI / 2) < 0.1) {//找到90度的那根，就是液位水平线(找到了液位的水平线以及坐标)
                x1 = pt1.x;
                y1 = pt1.y;
                x2 = pt2.x;
                y2 = pt2.y;
             //两点确定一条直线
             Imgproc.line(dst, pt1, pt2, new Scalar(255, 255, 255), 3, Imgproc.LINE_AA, 0);//白色
             op1=pt1;op2=pt2;//获取液位关键点
            }
        }
        HighGui.imshow("水位线",dst);
        HighGui.waitKey(0) ;
        /**
         * 确定瓶子的肩部与颈部 ,这三根线有问题，需要用霍夫圆来检测
         */

        

// 代码有误，只是参考
//        double shoulder = (x1 + x2) / 2;
//        double bottleneck = (src.cols() - 1) / 2;
//        double level = (shoulder + bottleneck) / 2;
//        //黄色
//        Imgproc.line(src, new Point(0, shoulder), new Point(src.rows() - 1,shoulder ), new Scalar(0, 255, 255), 2);
//        Imgproc.line(src, new Point(0, bottleneck), new Point(src.rows() - 1, bottleneck), new Scalar(0, 255, 255), 2);
//        //绿色
//        Imgproc.line(src, new Point(0, level), new Point(src.rows() - 1, level), new Scalar(0, 255, 0), 2);
//
//
//        double percentage = (src.cols() - level) / src.cols() * 100;
//        System.out.println("液位百分比：" + percentage + "%");
//        Imgcodecs.imwrite("bottle_level_detection.jpg", src);
}
}