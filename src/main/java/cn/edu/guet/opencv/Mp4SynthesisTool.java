package cn.edu.guet.opencv;

import java.io.IOException;

/**
 * @version 1.0
 * @Author qin
 * @Date 2023/3/17 23:36
 */
public class Mp4SynthesisTool {
    public static void main(String[] args) throws IOException {
        String inputFolderPath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\";
        String outputFilePath = "C:\\Users\\qin\\Desktop\\code\\smart-server\\file.mp4";
        VideoProcessor.mergeJpegsToMp4(inputFolderPath,outputFilePath);
    }
}
