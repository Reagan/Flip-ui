package it.qubixic.showcase.utils;

import java.io.IOException;
import javax.microedition.lcdui.Image;

public class ImageUtils {
    
    public static Image loadJarImage(String file) {       
        Image image = null;
        try {
            image = Image.createImage(file);
        }
        catch (NullPointerException npe) {
            System.out.println("No image file name specified " + npe.toString());
        }
        catch (IOException ioe) {
            System.out.println("Image [" + file + "] not found or invalid: " + ioe);
        }
        return image;
    }
}
