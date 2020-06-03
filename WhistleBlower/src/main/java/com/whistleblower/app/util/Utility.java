package com.whistleblower.app.util;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utility {


    public static void removeEXIF(File file, String filename) {
        String extension = "";

        int i = filename.lastIndexOf('.');
        if (i > 0) {
            extension = filename.substring(i + 1);
            System.out.println("EXTENSION: " + extension);

            BufferedImage image;
            try {
                image = ImageIO.read(file);
                ImageIO.write(image, extension, file);
                System.out.println("removeEXIF SUCCESS!");
            } catch (IOException e) {
               e.printStackTrace();
                System.out.println("removeEXIF FAILED!");
            }
        }

    }
}
