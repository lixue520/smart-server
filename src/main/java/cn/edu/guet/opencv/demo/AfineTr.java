package cn.edu.guet.opencv.demo;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 17:13
 * 仿射变换
 */
public class AfineTr {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b3.jpg");
        Mat dst = new Mat();
        Point[] pt1 = new Point[4];//输入像素点集
        pt1[0] = new Point(145,78);
        pt1[1] = new Point(502,114);
        pt1[2] = new Point(115,484);
        pt1[3] = new Point(470,475);

        Point[] pt2 = new Point[4];//输出图像点集
        pt2[0] = new Point(0,0);
        pt2[1] = new Point(350,0);
        pt2[2] = new Point(0,350);
        pt2[3] = new Point(350,350);

        MatOfPoint2f mop1 = new MatOfPoint2f(pt1);
        MatOfPoint2f mop2 = new MatOfPoint2f(pt2);

        Mat perspectiveMat = Imgproc.getPerspectiveTransform(mop1,mop2);//获得旋转矩阵
        Imgproc.warpPerspective(src,dst,perspectiveMat,new Size(350,350));

        HighGui.imshow("book",dst);
        HighGui.waitKey(0);
    }
}
