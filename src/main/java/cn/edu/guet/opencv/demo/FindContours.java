package cn.edu.guet.opencv.demo;

import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Scalar;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/19 20:38
 */
public class FindContours {
    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");//静态调用opencv库
        System.load(url.getPath());
        Mat src = Imgcodecs.imread("src/main/java/cn/edu/guet/opencv/b4.jpg");
        Imgproc.cvtColor(src,src,Imgproc.COLOR_BGR2GRAY);//灰度图
        Mat gray = new Mat();
        Imgproc.Canny(src,gray,60,200);
        HighGui.imshow("Canny",gray);

        List<MatOfPoint> contours = new ArrayList<MatOfPoint>(); //接收结果集
        /**
         *
         */
        Imgproc.findContours(gray,contours,new Mat(),Imgproc.RETR_LIST,Imgproc.CHAIN_APPROX_SIMPLE);
        Mat target = new Mat(gray.height(),gray.width(), CvType.CV_8UC3,new Scalar(255,255,255));
        for(int i=0;i<contours.size();i++)  //从得到矩阵个数来算图层
            Imgproc.drawContours(target,contours,i,new Scalar(0,0,0),3);//target上面(白色)轮廓的一层
        HighGui.imshow("Contours",target);
        HighGui.waitKey(0);
    }
}
