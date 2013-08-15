package it.qubixic.component.dynamicImageLoader;

import it.qubixic.component.dynamicImageLoader.event.ImageLoadingEvent;
import it.qubixic.component.dynamicImageLoader.event.ImageLoadingListener;
import it.qubixic.component.dynamicImageLoader.event.ImageLoadingStatus;
import it.qubixic.component.theme.Theme;
import it.qubixic.showcase.utils.ImageUtils;
import it.qubixic.showcase.utils.StringUtils;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
        
public class DynamicImageLoader {
     
    private int width  = 120 ;
    private int height = 120 ; 
    private Image loadedImage = null ;
    private ImageCache cache = new ImageCache() ;
    private final String LOADING_MESSAGE = "Loading..." ;
    private final String NO_IMAGE_SPECIFIED_MESSAGE = "No Image" ;
    public boolean dirty = false; 
    public Vector imageLoadingListeners = new Vector() ;
    
    /**
     * Creates a new instance of a dynamic image
     * loader
     */
    public DynamicImageLoader() {
    }
    
    /**
     * Creates a dynamic image loader with a 
     * width and height
     * @param width
     * @param height 
     */
    public DynamicImageLoader(int width, int height) {
        setWidth(width);
        setHeight(height);
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
    
    /**
     * gets the loaded image
     * @return 
     */
    public Image getLoadedImage () {
        return loadedImage ;
    }
    
    /**
     * Actually draws the fetched image onto the canvas
     * @param w width
     * @param h  height
     */
    public Image fetchImage(String imageURL, boolean generatePlaceLoaderImage) {
        Image generatedImage = null ;
        System.out.println("fetch Image called ");
        if (!imageURL.equals("") && StringUtils.validateURL(imageURL)) {
            System.out.println("A ");
            if (cache.contains(imageURL)) {
                System.out.println("C");
                generatedImage = cache.get(imageURL).getImage() ;
            } else {
                System.out.println("D");
                loadImage(imageURL);
                if(generatePlaceLoaderImage) {
                    generatedImage = drawPlaceHolder(LOADING_MESSAGE) ; 
                }
            }
        } else {
            System.out.println("B");
            if(generatePlaceLoaderImage) {
                generatedImage = drawPlaceHolder(NO_IMAGE_SPECIFIED_MESSAGE);
            }
        }
        return generatedImage ;
    }
    
    /**
     * This draws a place holder message
     * where an image to be fetched has not been
     * specified or is loading
     * @param g Graphics object
     * @param message place holder text
     */
    protected Image drawPlaceHolder(String message) {
        
        final int ARC_RADIUS = 5 ; 
        final int PADDING = 5 ;   
        Image placeHolderImage = Image.createImage(width, height) ;        
        Graphics g = placeHolderImage.getGraphics() ;
                            
        g.setFont(Theme.getDynamicImageMessageFont());
        g.setColor(Theme.getDynamicImageMessageBgColor()) ;
        
        int startX = width/ 2 - (g.getFont().charsWidth(message.toCharArray(), 
                0, message.length()) + 2 * PADDING) / 2 ;
        g.fillRoundRect(startX, 
                (int) (height / 2), 
                g.getFont().charsWidth(message.toCharArray(), 0, message.length()) +
                2 * PADDING, g.getFont().getHeight() + 2 *  PADDING,
                ARC_RADIUS, ARC_RADIUS);
        
        g.setColor(Theme.getDynamicImageMessageFontColor());
        g.drawString(message, startX + PADDING, 
                (int) (height / 2) + PADDING, Graphics.TOP | Graphics.LEFT);
        
        return placeHolderImage ;
    }
    
    /**
     * This method is delegated the actual responsibility
     * of fetching an image and redrawing the image
     * @param imageURL the image to be fetched
     */ 
    protected void loadImage(final String imageURL) {        
        new Thread(new Runnable() {
            public void run() {
                try {
                    loadedImage = createImage(imageURL) ;
                    cache.addImageEntity(new ImageEntity(imageURL, loadedImage));
                    triggerImageLoadingEvent(imageURL, ImageLoadingStatus.LOADED);
                    System.out.println("Image " + imageURL + " loaded " 
                            + ImageLoadingStatus.LOADED);
                    dirty = true ;
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    /**
     * This method creates an image instance from 
     * the fetched image content
     * @param name name of the image
     * @return image
     * @throws IOException 
     */
    private Image createImage(String name) throws IOException {
        if (name.startsWith("/")) {
            return ImageUtils.loadJarImage(name) ;
        } else if (name.startsWith("http:") || name.startsWith("https:")) {
            return ImageUtils.loadRemoteImage(name) ;
        } else {
            throw new IOException("Unsupported media");
        }
    }
    
    /**
     * Adds an image listener
     * @param imageLoadingListener 
     */
    public void addImageLoadingListener(ImageLoadingListener imageLoadingListener) {
        if (!imageLoadingListeners.contains(imageLoadingListener)) {
            imageLoadingListeners.addElement(imageLoadingListener);
        }
    }
    
    /**
     * Removes an image listener
     * @param imageLoadingListener 
     */
    public void removeImageLoadingListener(ImageLoadingListener imageLoadingListener) {
        imageLoadingListeners.removeElement(imageLoadingListener);
    }
    
    /**
     * Triggered when an image loading event occurs
     * @param imageName
     * @param loadingStatus 
     */
    protected void triggerImageLoadingEvent (String imageName, int loadingStatus) {
        System.out.println("S " + imageName + "-" + loadingStatus);
        ImageLoadingEvent imageLoadingEvent 
                = new ImageLoadingEvent(imageName, loadingStatus) ;
        for (int listenersCounter = 0 ; listenersCounter < imageLoadingListeners.size();
                listenersCounter++) {
            ((ImageLoadingListener) imageLoadingListeners.elementAt(listenersCounter))
                    .imageLoaded(imageLoadingEvent) ;
        }
    }
}
