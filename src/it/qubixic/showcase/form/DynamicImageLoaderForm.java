package it.qubixic.showcase.form;

import it.qubixic.component.dynamicImageLoader.DynamicImageLoader;
import it.qubixic.component.dynamicImageLoader.event.ImageLoadingEvent;
import it.qubixic.component.dynamicImageLoader.event.ImageLoadingListener;
import it.qubixic.component.dynamicImageLoader.event.ImageLoadingStatus;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class DynamicImageLoaderForm extends BaseForm {
    
    private Vector remoteImages  = new Vector() ;    
    private DynamicImageLoaderComponent dynamicImageLoaderComponent ;
    private final String LOADER_TITLE = "Dynamic Image Loader" ;
    private final String IMAGE_1 = "http://www.nodl.es/test_images/thumbnail_1.png" ;
    private final String IMAGE_2 = "http://www.nodl.es/test_images/thumbnail_2.png" ;
    private final String IMAGE_3 = "http://www.nodl.es/test_images/thumbnail_3.png" ;
    private final String IMAGE_4 = "http://www.nodl.es/test_images/thumbnail_4.png" ;
    private final String IMAGE_5 = "http://www.nodl.es/test_images/thumbnail_5.png" ;    
    private final Command PREVIOUS_IMAGE_COMMAND = new Command("Previous Image", Command.SCREEN, 1) ;
    private final Command NEXT_IMAGE_COMMAND = new Command("Next Image", Command.SCREEN, 1) ;
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
        dynamicImageLoaderComponent = new DynamicImageLoaderComponent(LOADER_TITLE);
        dynamicImageLoaderComponent.addCommand(PREVIOUS_IMAGE_COMMAND);
        dynamicImageLoaderComponent.addCommand(NEXT_IMAGE_COMMAND);
        dynamicImageLoaderComponent.setDefaultCommand(NEXT_IMAGE_COMMAND);
        dynamicImageLoaderComponent.setItemCommandListener(new ItemCommandListener() {
            public void commandAction(Command c, Item item) {
                if (c == PREVIOUS_IMAGE_COMMAND) {
                    dynamicImageLoaderComponent.setImageURL(getPreviousImageURL());
                } else if (c == NEXT_IMAGE_COMMAND) {
                    dynamicImageLoaderComponent.setImageURL(getNextImageURL());
                }
            }
        });
        return dynamicImageLoaderComponent;
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

    
    
    class DynamicImageLoaderComponent extends CustomItem implements ImageLoadingListener  {
        
        public int loader_width = getWidth();
        public int loader_height = 200 ;
        private String imageURL = "";
        private String title = "" ;
        private int topX = 0 ;
        private int topY = 0 ;
        private final int BUFFER = 10 ;
        protected int dynamicImageBgColor = 0xcccccc ;
        protected int dynamicImageTitleColor = 0x000000;
        protected Font dynamicImageTitleFont = Font.getFont(Font.FACE_SYSTEM,
                Font.STYLE_PLAIN, Font.SIZE_MEDIUM);        
        private DynamicImageLoader dynamicImageLoader ;
        private Image loadedImage; 
        
        DynamicImageLoaderComponent (String title) {
            super("") ;
            setTitle(title);
            initializeDynamicImageLoader();
            addImageLoadingListener() ;
        }

        public void setTitle(String title) {
            this.title = title ;
        }
        
        public void initializeDynamicImageLoader() {
            dynamicImageLoader = new DynamicImageLoader(getWidth(), loader_height);
            loadedImage = dynamicImageLoader.fetchImage("", true) ;
        }

        protected void addImageLoadingListener() {
            dynamicImageLoader.addImageLoadingListener(this);
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
        
        protected void paint(Graphics g, int w, int h) {
            System.out.println("paint called ");
            System.out.println("A-top " + topX + "," + topY);
            drawTitle(g, topX, topY) ;
            drawBackground(g, topX, topY, getWidth(), loader_height) ;
            drawLoadedImage(g, loadedImage, topX, topY) ;
            topY = 0 ;
        }
        
        protected void drawTitle(Graphics g, int x, int y) {
            if (!title.equals("")) {
                g.setColor(dynamicImageTitleColor);
                g.setFont(dynamicImageTitleFont);
                g.drawString(title, x, y, Graphics.TOP | Graphics.LEFT);
                topY = g.getFont().getHeight() + BUFFER;
                loader_height = 120 + topY;
                System.out.println("Intop " + topX + "," + topY);
            }
        }

        protected void drawBackground(Graphics g, int topX, int topY, int w, int h) {
            System.out.println("B - top " + topX + "," + topY); 
            g.setColor(dynamicImageBgColor);
            g.fillRect(topX, topY, w, h);
        }

        protected void drawLoadedImage(Graphics g, Image image, int topX, int topY) {
            g.drawImage(image, topX, topY, Graphics.TOP|Graphics.LEFT);
        }
        
        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            System.out.println("Image URL run " + imageURL) ;
            this.imageURL = imageURL;
            boolean createPlaceHolderImage = true ;
            loadedImage = dynamicImageLoader.fetchImage(imageURL, createPlaceHolderImage);     
            repaint();
        }

        public void imageLoaded(ImageLoadingEvent e) {
            System.out.println("imageLoaded called " + e.getLoadedStatus()) ;
            if (e.getLoadedStatus() == ImageLoadingStatus.LOADED) {
                System.out.println("Image reloaded. Repainting...");
                loadedImage = dynamicImageLoader.getLoadedImage() ;
                repaint();
            }
        }
    }
}
