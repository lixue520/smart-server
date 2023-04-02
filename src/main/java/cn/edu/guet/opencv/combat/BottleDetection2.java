package cn.edu.guet.opencv.combat;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgcodecs.Imgcodecs;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BottleDetection2 {

    public static void main(String[] args) {

        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态
        System.load(url.getPath());
        Mat image = Imgcodecs.imread("C:\\Users\\qin\\Desktop\\code\\smart-server\\video-stream.jpeg");

        // 1.转换为灰度图像
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);
        Size newSize = new Size(gray.width() * 1.5, gray.height() * 1.5);
//        Mat dst = new Mat();//目标图像
        Imgproc.resize(gray, gray, newSize);//缩放一半
        HighGui.imshow("gray",gray);
        HighGui.waitKey(0);

        // 2.高斯滤波
        Mat blur = new Mat();
        Imgproc.GaussianBlur(gray, blur, new Size(5, 5), 0.25);
        HighGui.imshow("gs",blur);
        HighGui.waitKey(0);

        // 3.  Canny边缘检测
        Mat edges = new Mat();
        Imgproc.Canny(blur, edges, 30, 170);
        HighGui.imshow("edges",edges);
        HighGui.waitKey(0);

        // 4.膨胀操作
        Mat dilate = new Mat();
        Mat kernel = Imgproc.getStructuringElement(Imgproc.MORPH_RECT, new Size(5, 5));
        Imgproc.dilate(edges, dilate, kernel);
        HighGui.imshow("dilate",dilate);
        HighGui.waitKey(0);

        // 查找轮廓
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        Imgproc.findContours(edges, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);
        Mat target = new Mat(edges.height(),edges.width(), CvType.CV_8UC3,new Scalar(255,255,255));

        for(int i=0;i<contours.size();i++)  //从得到矩阵个数来算图层
            Imgproc.drawContours(target,contours,i,new Scalar(0,0,0),3);//target上面(白色)轮廓的一层
        HighGui.imshow("Contours",target);
        HighGui.waitKey(0);


        // 遍历轮廓
        for (int i = 0; i < contours.size(); i++) {
            MatOfPoint contour = contours.get(i);

            // 计算轮廓面积
            double area = Imgproc.contourArea(contour);

            // 过滤面积过小的轮廓
            if (area < 100) {
                continue;
            }

            // 外接矩形
            Rect rect = Imgproc.boundingRect(contour);

            // 计算液位所占矩形面积的百分比
            int liquidLevel = (int) ((rect.height - (rect.y + rect.height - i)) * 100.0 / rect.height);

            // 绘制矩形和液位百分比
            Imgproc.rectangle(gray, rect, new Scalar(0, 0, 255), 2);
           // Imgproc.putText(image, String.format("%.2f%%", liquidLevel), new Point(rect.x + rect.width + 10, rect.y + rect.height / 2), Imgproc.FONT_HERSHEY_SIMPLEX, 0.5, new Scalar(255, 0, 0), 1);
        }

        // 显示结果
        HighGui.imshow("Bottle Detection", gray);
        HighGui.waitKey();
    }
}