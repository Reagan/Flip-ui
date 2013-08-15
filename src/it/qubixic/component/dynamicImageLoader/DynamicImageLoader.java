package it.qubixic.component.dynamicImageLoader;

import it.qubixic.component.theme.Theme;
import it.qubixic.showcase.utils.StringUtils;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
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
    
    private final int BG_COLOR = 0x999999 ;
    
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
    
    protected void drawBackground(Graphics g, int w, int h) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, width, height);
    }
    
    protected void drawImage(Graphics g, int w, int h) {
        if (cache.contains(imageURL)) {
            displayedImage = cache.get(imageURL).getImage() ;
            g.drawImage(displayedImage, 0, 0, Graphics.TOP | Graphics.LEFT);
        } else {
            drawPlaceHolder(g, LOADING_MESSAGE) ;
            loadImage(imageURL) ;                     
        }
    }
    
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
        
        g.setColor(Theme.getOverlayMessageFontColor());
        g.drawString(message, startX + PADDING, 
                (int) (getHeight() / 2) + PADDING,
                Graphics.TOP | Graphics.LEFT);
    }
    
    protected void loadImage(final String imageURL) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    displayedImage = createImage(imageURL) ;
                    repaint();
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (OutOfMemoryError e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    
    private Image createImage(String name) throws IOException {
        if (name.startsWith("/")) {
            // Load as a resource with Image.createImage
            return Image.createImage(name);
        } else if (name.startsWith("http:") || name.startsWith("https:")) {
            // Load from a ContentConnection
            HttpConnection c = null;
	    DataInputStream is = null;
            try {
	        c = (HttpConnection)Connector.open(name);
		int status = c.getResponseCode();
		if (status != 200) {
		    throw new IOException("HTTP Response Code = " + status);
		}

                int len = (int)c.getLength();
                String type = c.getType();
		if (!type.equals("image/png")) {
		    throw new IOException("Expecting image, received " + type);
		}

                if (len > 0) {
		    is = c.openDataInputStream();
		    byte[] data = new byte[len];
		    is.readFully(data);
                    return Image.createImage(data, 0, len);
	        } else {
		    throw new IOException("Content length is missing");
	        }
	    } finally {
	        if (is != null)
                    is.close();
                if (c != null)
                    c.close();
            }
        } else {
            throw new IOException("Unsupported media");
        }
    }
}
