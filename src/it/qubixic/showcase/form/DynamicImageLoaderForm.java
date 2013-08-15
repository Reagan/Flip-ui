package it.qubixic.showcase.form;

import it.qubixic.component.dynamicImageLoader.DynamicImageLoader;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.midlet.*;

public class DynamicImageLoaderForm extends BaseForm {
    
    private Vector remoteImages  = new Vector() ;
    private DynamicImageLoader dynamicImageLoader ;
    private final int LOADER_HEIGHT = 200 ;
    private final String LOADER_TITLE = "Dynamic Image Loader" ;
    private final String IMAGE_1 = "http://www.nodl.es/test_images/thumbnail_1.png" ;
    private final String IMAGE_2 = "http://www.nodl.es/test_images/thumbnail_2.png" ;
    private final String IMAGE_3 = "http://www.nodl.es/test_images/thumbnail_3.png" ;
    private final String IMAGE_4 = "http://www.nodl.es/test_images/thumbnail_4.png" ;
    private final String IMAGE_5 = "http://www.nodl.es/test_images/thumbnail_5.png" ;    
    private final Command PREVIOUS_IMAGE_COMMAND 
            = new Command("Previous Image", Command.SCREEN, 1) ;
    private final Command NEXT_IMAGE_COMMAND 
            = new Command("Next Image", Command.SCREEN, 1) ;
    private int currentlyDisplayedImage = 0 ;
    
    public DynamicImageLoaderForm (MIDlet midlet) {
        super("", midlet) ;
        populateRemoteImages() ; 
        appendDynamicImageLoader(createDynamicImageLoader()) ;
    }
    
    public void populateRemoteImages() {
        remoteImages.addElement(IMAGE_1);
        remoteImages.addElement(IMAGE_2);
        remoteImages.addElement(IMAGE_3);
        remoteImages.addElement(IMAGE_4);
        remoteImages.addElement(IMAGE_5);
    }
    
    public void appendDynamicImageLoader(DynamicImageLoader 
            dynamicImageLoader) {
        append(dynamicImageLoader) ;
    }
    
    public DynamicImageLoader createDynamicImageLoader() {
        dynamicImageLoader = new DynamicImageLoader(LOADER_TITLE,
                getWidth(), LOADER_HEIGHT) ;
        dynamicImageLoader.addCommand(PREVIOUS_IMAGE_COMMAND);
        dynamicImageLoader.addCommand(NEXT_IMAGE_COMMAND);
        dynamicImageLoader.setDefaultCommand(NEXT_IMAGE_COMMAND);
        dynamicImageLoader.setItemCommandListener(new ItemCommandListener() {
            public void commandAction(Command c, Item item) {
                if (c == PREVIOUS_IMAGE_COMMAND) {
                    dynamicImageLoader.setImageURL(getPreviousImageURL());
                } else if (c == NEXT_IMAGE_COMMAND) {
                    dynamicImageLoader.setImageURL(getNextImageURL());
                }
            }
        });
        return dynamicImageLoader ;
    }
    
    private String getPreviousImageURL() {
        if (currentlyDisplayedImage == 0) {
            currentlyDisplayedImage = remoteImages.size() - 1 ;
        } else {
            currentlyDisplayedImage-- ;
        }
        return (String) remoteImages.elementAt(currentlyDisplayedImage) ;
    }
    
    private String getNextImageURL() {
        if(currentlyDisplayedImage + 1 == remoteImages.size()) {
            currentlyDisplayedImage = 0 ;             
        } else {
            currentlyDisplayedImage++ ;
        }
        return (String) remoteImages.elementAt(currentlyDisplayedImage) ;
    }
}
