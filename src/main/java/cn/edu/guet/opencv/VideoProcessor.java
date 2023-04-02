package cn.edu.guet.opencv;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class VideoProcessor {

    public static void mergeJpegsToMp4(String inputFolderPath, String outputFilePath) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("ffmpeg", "-framerate", "30", "-i",
                inputFolderPath + "video-stream-%013d.jpeg", "-c:v", "libx264", "-pix_fmt", "yuv420p", "-vf", "pad=ceil(iw/2)*2:ceil(ih/2)*2",
                outputFilePath);

        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        try (InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream());
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        String inputFolderPath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\";
        String outputFilePath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\file.mp4";

        // Get a list of all JPEG files in the input folder
        File[] jpegFiles = new File(inputFolderPath).listFiles((dir, name) -> name.endsWith(".jpeg"));

//        // Sort the JPEG files by filename (assuming filenames are numbers)
//        Arrays.sort(jpegFiles, Comparator.comparingInt(file -> Integer.parseInt(file.getName().replaceAll("\\D+", ""))));

        // Rename the JPEG files to have sequential filenames starting at 1
        for (int i = 0; i < jpegFiles.length; i++) {
            File jpegFile = jpegFiles[i];
            jpegFile.renameTo(new File(inputFolderPath, String.format("video-stream-%013d.jpeg", i + 1)));
        }

        // Merge the renamed JPEG files into an MP4 video file
        mergeJpegsToMp4(inputFolderPath, outputFilePath);
    }
}
