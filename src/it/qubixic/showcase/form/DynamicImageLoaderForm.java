package it.qubixic.showcase.form;

import it.qubixic.component.dynamicImageLoader.DynamicImageLoader;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class DynamicImageLoaderForm extends BaseForm {
    
    private Vector remoteImages  = new Vector() ;
    
    private DynamicImageLoaderComponent dynamicImageLoader ;
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
    
    protected void appendDynamicImageLoader(DynamicImageLoaderComponent 
            dynamicImageLoader) {
        append(dynamicImageLoader) ;
    }
    
    protected DynamicImageLoaderComponent createDynamicImageLoader() {
        dynamicImageLoader = new DynamicImageLoaderComponent();
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
        return dynamicImageLoader;
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
    
    class DynamicImageLoaderComponent extends CustomItem {

        private DynamicImageLoader dynamicImageLoader ;
        private int loader_width = getWidth();
        private int loader_height = 200 ;
        private String imageURL ;
        private String title = "" ;
        private int topX = 0 ;
        private int topY = 0 ;
        private final int BUFFER = 10 ;
        protected int dynamicImageBgColor = 0xcccccc ;
        protected int dynamicImageTitleColor = 0x000000;
        protected Font dynamicImageTitleFont = Font.getFont(Font.FACE_SYSTEM,
                Font.STYLE_PLAIN, Font.SIZE_MEDIUM);

        DynamicImageLoaderComponent () {
            super("") ;
            initializeDynamicImageLoader();
        }
        
        protected int getMinContentWidth() {
            return getWidth() ;
        }

        protected int getMinContentHeight() {
            return loader_height ;
        }

        protected int getPrefContentWidth(int height) {
            return getWidth() ;
        }

        protected int getPrefContentHeight(int width) {
            return loader_height ;
        }

        public void initializeDynamicImageLoader() {
            dynamicImageLoader = new DynamicImageLoader(LOADER_TITLE,
                    getWidth(), loader_height);            
        }

        protected void paint(Graphics g, int w, int h) {
            drawTitle(g, topX, topY) ;
            drawBackground(g, topX, topY, getWidth(), getHeight()) ;
            dynamicImageLoader.drawImage(g, topX, topY, w, h);
        }
        
        protected void drawTitle(Graphics g, int x, int y) {
            if (!title.equals("")) {
                g.setColor(dynamicImageTitleColor);
                g.setFont(dynamicImageTitleFont);
                g.drawString(title, x, y, Graphics.TOP | Graphics.LEFT);
                topY = g.getFont().getHeight() + BUFFER;
                loader_height = 120 + topY;
            }
        }

        protected void drawBackground(Graphics g, int topX, int topY, int w, int h) {
            g.setColor(dynamicImageBgColor);
            g.fillRect(topX, topY, loader_width, loader_height);
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
            repaint();
        }
    }
}
