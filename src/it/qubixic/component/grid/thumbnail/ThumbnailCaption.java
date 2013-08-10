package it.qubixic.component.grid.thumbnail;

import it.qubixic.component.theme.Theme;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

public class ThumbnailCaption implements Caption {
    
    private boolean wrapText = false; 
    private String truncatedText = null;
    private int truncatedTextWidth = -1;
    private Vector wrappedText = null;
    private int wrappedTextWidth = -1;
    
    private int listType ;    
    
    
    private final int DEFAULT_HEIGHT = 30 ;
    protected final int DEFAULT_WIDTH = 30 ;
            
    protected Object[] captionComponents ;
    private int width = DEFAULT_WIDTH ;    
    private int height = DEFAULT_HEIGHT ;
    private Font font = Theme.getThumbnailFont() ;
      
    /**
     * Creates a default instance of a Thumbnail caption
     */
    public ThumbnailCaption() {}
    
    /**
     * Creates a thumbnail caption instance with a name 
     * and description
     * @param title
     * @param description 
     */
    public ThumbnailCaption(String title, String description) {
        captionComponents = new String[2] ;
        captionComponents[0] = title ;
        captionComponents[1] = description ;
    }
    
    /**
     * Creates a number of thumbnail caption string components
     * @param components 
     */
    public ThumbnailCaption(String[] components) {
        if (null != components) {
            captionComponents = components ;
        }
    }
    
    /**
     * Creates a number of thumbnail caption components .
     * The caption components must implement the
     * caption component interface for them to be drawn
     * @param components 
     */
    public ThumbnailCaption (Object[] components) {
        if (null != components) {
            for (int componentsCounter = 0 ; componentsCounter < components.length;
                    componentsCounter++) {
                captionComponents = new CaptionComponent[components.length] ;
                if (components[componentsCounter] instanceof Caption) {
                    captionComponents[componentsCounter] = components[componentsCounter] ;
                }
            }
        }
    }      
    
    /**
     * 
     * @return the width of the caption
     */
    public int getWidth() {
        return width ;
    }
    
    /**
     * sets the width of the caption
     * @param width 
     */
    public void setWidth (int width) {
        this.width = width ;
    }
    
    /**
     * 
     * @return  the height of the caption
     */
    public int getHeight() {        
        return height ;       
    }
    
    /** 
     * sets the height of the caption
     * @param height height of the caption
     */
    public void setHeight (int height) {
        this.height = height ;
    }
    
    /**
     * returns the font used to draw the components for 
     * the thumbnail caption
     * @return 
     */
    public Font getFont() {
        return font ;
    }
    
    /** 
     * sets the font used for the thumbnail caption component
     * @param font
     * @return 
     */
    public void setFont (Font font) {
        this.font = font ;
    }

    /** 
     * returns the width of the thumbnail caption with
     * the thumbnail left and right margins considered
     * @return the width of the thumbnail caption text
     */
    public int getTextWidth() {
        return width - Theme.getTextMarginLeftAndRight(); 
    }

    /**
     * draws the actual thumbnail caption using the graphics object as 
     * passed from the thumbnail component
     * @param g Graphics object as passed from the parent thumbnail
     * @param topX top left x location for the thumbnail
     * @param topY top left y location for the thumbnail
     * @param parentWidth width of the encompassing thumbnail component
     * @param parentHeight height of the encompassing thumbnail component
     */
    public void render(Graphics g, int topX, int topY, int parentWidth, int parentHeight) {
        drawBackground(g, topX, topY, parentWidth, parentHeight) ;
        drawCaptionText(g, topX, topY, parentWidth, parentHeight) ;       
    }
    
    /**
     * Draws the thumbnail caption's background
     * @param g Graphics object that is used to draw the caption
     * @param topX top left X location for the component
     * @param topY top left Y location for the component
     * @param parentWidth width for the parent thumbnail
     * @param parentHeight height for the parent thumbnail
     */
    private void drawBackground(Graphics g, int topX, int topY, int parentWidth, 
            int parentHeight) {
        g.setColor(Theme.getThumbnailBackgroundCaptionColor());
        g.fillRect(topX, topY + parentHeight - height, 
                topX + parentWidth, topY + parentHeight);        
    }
    
    /**
     * Draws the caption text for the thumbnail caption. If a custom 
     * caption component is used, then the Caption Component's render 
     * method is used inn conjunction with its getHeight() method to
     * draw the caption
     * @param g Graphics object that is used to draw the caption
     * @param topX top left X location for the component
     * @param topY top left Y location for the component
     * @param parentWidth width for the parent thumbnail
     * @param parentHeight height for the parent thumbnail
     */
    private void drawCaptionText(Graphics g, int topX, int topY, int parentWidth, 
            int parentHeight) {
        
        int currTopX = topX + 5 ;
        int currTopY = topY + parentHeight - 29; 
        
        g.setFont(Theme.getThumbnailFont());
        g.setColor(Theme.getThumbnailCaptionTitleColor());
                
        for (int captionCounter = 0 ; captionCounter < captionComponents.length; 
                captionCounter++) {
            Object currComponent = captionComponents[captionCounter] ;
            if (currComponent instanceof String)  {
                
                g.drawString((String) currComponent, currTopX, currTopY, 
                        Graphics.LEFT | Graphics.TOP);
                currTopY += font.getHeight() ;
            } else if (currComponent instanceof CaptionComponent) {
                
                ((CaptionComponent) currComponent).render(g, topX, topY, 
                parentWidth, parentHeight) ;
                currTopY += ((CaptionComponent) currComponent).getHeight() ;
            }
        }                
    }
}
