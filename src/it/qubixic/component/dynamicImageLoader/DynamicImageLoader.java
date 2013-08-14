package it.qubixic.component.dynamicImageLoader;

import javax.microedition.lcdui.CustomItem ;
import javax.microedition.lcdui.Graphics;
        
public class DynamicImageLoader extends CustomItem {
    
    private String title = "" ;
    private String imageURL = "" ;    
    private int width  = 120 ;
    private int height = 120 ; 
    
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
        if (validateURL(imageURL)) {
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
        
    }    

    
}
