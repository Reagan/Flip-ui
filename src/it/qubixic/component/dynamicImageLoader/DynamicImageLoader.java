package it.qubixic.component.dynamicImageLoader;

import it.qubixic.component.theme.Theme;
import it.qubixic.showcase.utils.ImageUtils;
import it.qubixic.showcase.utils.StringUtils;
import java.io.IOException;
import javax.microedition.lcdui.CustomItem ;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
        
public class DynamicImageLoader extends CustomItem {
    
    private String title = "" ;
    private String imageURL = "" ;    
    private int width  = 120 ;
    private int height = 120 ; 
    private Image displayedImage = null ;
    private ImageCache cache = new ImageCache() ;
    private final String LOADING_MESSAGE = "Loading..." ;
    private final String NO_IMAGE_SPECIFIED_MESSAGE = "No Image" ;
    
    /**
     * Creates a new instance of a dynamic image
     * loader
     */
    public DynamicImageLoader() {
        super ("") ; 
    }
    
    /**
     * Creates a dynamic image loader with a title
     * @param title 
     */
    public DynamicImageLoader(String title) {
        super ("") ;
        setTitle(title);
    }
    
    /**
     * Creates a dynamic loader image with a title, 
     * width and height
     * @param title
     * @param width
     * @param height 
     */
    public DynamicImageLoader (String title, int width, int height) {
        super("") ;
        setTitle(title);
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * Creates a dynamic image loader with a title
     * and initial image URL
     * @param title
     * @param imageURL 
     */
    public DynamicImageLoader(String title, String imageURL) {
        super ("") ;
        setTitle(title);
        setImageURL(imageURL);
    }
    
    /**
     * Creates a dynamic image loader with a title, 
     * initial image URL, width and height
     * @param title
     * @param imageURL
     * @param width
     * @param height 
     */
    public DynamicImageLoader(String title, String imageURL, 
            int width, int height) {
        super("") ;
        setTitle(title);
        setImageURL(imageURL);
        setWidth(width);
        setHeight(height);
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        if (StringUtils.validateURL(imageURL)) {
            this.imageURL = imageURL;
            repaint();
        }        
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
    
    protected int getMinContentWidth() {
        return getWidth() ;
    }

    protected int getMinContentHeight() {
        return getHeight() ;
    }

    protected int getPrefContentWidth(int height) {
        return getWidth() ;
    }

    protected int getPrefContentHeight(int width) {
        return getHeight();
    }

    protected void paint(Graphics g, int w, int h) {
        drawBackground(g, getWidth(), getHeight()) ;
        drawImage(g, getWidth(), getHeight()) ;
    }    
    
    /** 
     * Draws the background to the dynamic Image Loader
     * @param g Graphics object
     * @param w width
     * @param h  height
     */
    protected void drawBackground(Graphics g, int w, int h) {
        g.setColor(Theme.getDynamicImageBgColor());
        g.fillRect(0, 0, width, height);
    }
    
    /**
     * Actually draws the fetched image onto the canvas
   * @param g Graphics object
     * @param w width
     * @param h  height
     */
    protected void drawImage(Graphics g, int w, int h) {
        if (!imageURL.equals("")) {
            if (cache.contains(imageURL)) {
                displayedImage = cache.get(imageURL).getImage() ;
                g.drawImage(displayedImage, 0, 0, Graphics.TOP | Graphics.LEFT);
            } else {
                drawPlaceHolder(g, LOADING_MESSAGE) ; 
                loadImage(imageURL);
            }
        } else {
            drawPlaceHolder(g, NO_IMAGE_SPECIFIED_MESSAGE);
        }
    }
    
    /**
     * This draws a place holder message
     * where an image to be fetched has not been
     * specified or is loading
     * @param g Graphics object
     * @param message place holder text
     */
    protected void drawPlaceHolder(Graphics g, String message) {
        
        final int ARC_RADIUS = 5 ; 
        final int PADDING = 5 ;       
        
        g.setFont(Theme.getDynamicImageMessageFont());
        g.setColor(Theme.getDynamicImageMessageBgColor()) ;
        
        int startX = getWidth() / 2 - (g.getFont().charsWidth(message.toCharArray(), 
                0, message.length()) +
                2 * PADDING) / 2 ;
        g.fillRoundRect(startX, 
                (int) (getHeight() / 2), 
                g.getFont().charsWidth(message.toCharArray(), 0, message.length()) +
                2 * PADDING, 
                g.getFont().getHeight() + 2 *  PADDING,
                ARC_RADIUS,
                ARC_RADIUS);
        
        g.setColor(Theme.getDynamicImageMessageFontColor());
        g.drawString(message, startX + PADDING, 
                (int) (getHeight() / 2) + PADDING,
                Graphics.TOP | Graphics.LEFT);
    }
    
    /**
     * This method is delegated the actual responsibility
     * of fetching an image
     * @param imageURL the image to be fetched
     */ 
    protected void loadImage(final String imageURL) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    displayedImage = createImage(imageURL) ;
                    cache.addImageEntity(new ImageEntity(imageURL, displayedImage));
                    repaint();
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
}
