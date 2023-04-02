package cn.edu.guet.opencv;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/18 21:12
 */
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class BottleLevelDetector {

    public static void main(String[] args) {
        URL url = ClassLoader.getSystemResource("lib/opencv_java460.dll");
        System.load(url.getPath());
        // Load OpenCV library and initialize it
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Read image from file
        String imagePath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\src\\main\\java\\cn\\edu\\guet\\opencv\\a1.jpg";
        Mat src = Imgcodecs.imread(imagePath);

        // Convert image to grayscale
        Mat gray = new Mat();
        Imgproc.cvtColor(src, gray, Imgproc.COLOR_BGR2GRAY);

        // Apply threshold to segment the bottle
        Mat binary = new Mat();
        Imgproc.threshold(gray, binary, 0, 255, Imgproc.THRESH_BINARY+Imgproc.THRESH_OTSU);

        // Find contours of the segmented bottle
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();
        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // Find the largest contour (assumed to be the bottle)
        int largestIndex = -1;
        double largestArea = 0;
        for (int i = 0; i < contours.size(); i++) {
            double area = Imgproc.contourArea(contours.get(i));
            if (area > largestArea) {
                largestArea = area;
                largestIndex = i;
            }
        }

        // Calculate the percentage of the bottle filled with liquid
        double bottleHeight = binary.rows();
        double liquidHeight = bottleHeight - Imgproc.boundingRect(contours.get(largestIndex)).y;
        double percentageFilled = liquidHeight / bottleHeight * 100;

        // Print the percentage filled
        System.out.println("Bottle is " + percentageFilled + "% filled.");
    }

}
