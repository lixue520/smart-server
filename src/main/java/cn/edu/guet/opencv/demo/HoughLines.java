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
 * @Date 2023/3/19 21:12
 * 霍夫直线检测
 */
public class HoughLines {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
        Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat dst = new Mat(),cdst = new Mat(),lines = new Mat();
        Imgproc.Canny(src,dst,50,200,3,false);
        Imgproc.cvtColor(dst,cdst,Imgproc.COLOR_GRAY2BGR);//灰色变彩色
        Imgproc.HoughLines(dst,lines,1,Math.PI/180,150);

        for(int x=0;x<lines.rows();x++){
            double rho  = lines.get(x,0)[0],theta = lines.get(x,0)[1];
            double a = Math.cos(theta),b = Math.sin(theta);
            double x0 = a*rho,y0 = b*rho;
            Point pt1 = new Point(Math.round(x0+1000*(-b)),Math.round(y0+1000*(a)));
            Point pt2 = new Point(Math.round(x0-1000*(-b)),Math.round(y0-1000*(a)));
            Imgproc.line(cdst,pt1,pt2,new Scalar(0,0,255),3);
        }
        HighGui.imshow("Source",src);
        HighGui.imshow("Detected",cdst);
        HighGui.waitKey(0);
        System.exit(0);
    }
}
















