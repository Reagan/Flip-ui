package it.qubixic.showcase.utils;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Image;

public class ImageUtils {

    public static Image loadJarImage(String file) {
        Image image = null;
        try {
            image = Image.createImage(file);
        } catch (NullPointerException npe) {
            System.out.println("No image file name specified " + npe.toString());
        } catch (IOException ioe) {
            System.out.println("Image [" + file + "] not found or invalid: " + ioe);
        }
        return image;
    }

    public static Image loadRemoteImage(String name) throws IOException {
        Image remoteImage = null ;
        
        HttpConnection c = null;
        DataInputStream is = null;
        try {
            c = (HttpConnection) Connector.open(name);
            int status = c.getResponseCode();
            if (status != 200) {
                throw new IOException("HTTP Response Code = " + status);
            }

            int len = (int) c.getLength();
            String type = c.getType();
            if (!type.equals("image/png")) {
                throw new IOException("Expecting image, received " + type);
            }

            if (len > 0) {
                is = c.openDataInputStream();
                byte[] data = new byte[len];
                is.readFully(data);
                remoteImage = Image.createImage(data, 0, len);
            } else {
                throw new IOException("Content length is missing");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
            if (c != null) {
                c.close();
            }
        }
        return remoteImage ;
    }        
}
