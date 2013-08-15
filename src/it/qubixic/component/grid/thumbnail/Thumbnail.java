package it.qubixic.component.grid.thumbnail;

import it.qubixic.component.theme.Theme;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class Thumbnail implements IThumbnail {

    private ThumbnailCaption caption ;
    private Image image;    
    private boolean selected = false;    
    private int width = 100 ;
    private int height = 100 ;
    private int topX = 0 ; 
    private int topY = 0 ;
    protected boolean focused = false ; 
    
    public Thumbnail() {}
    
    public Thumbnail(ThumbnailCaption caption, Image image) {        
        setCaption(caption);
        setImage(image);
    }

    public void setImage(Image image) {
        this.image = image ;
    }
    
    public Image getImage() {
        return image;
    }
    
    public boolean getFocussed () {
        return focused ;
    }
    
    public void setFocussed (boolean state) {
        this.focused = state ;
    }   
    
    public ThumbnailCaption getCaption () {
        return caption ;
    }    
    
    public void setCaption (ThumbnailCaption caption) {
        this.caption = caption ;
    }
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getHeight() {
        return height ;
    }
    
    public void setHeight (int height) {
        this.height = height ;
    }
    
    public int getWidth() {
        return width ;
    } 
    
    public void setWidth (int width) {
        this.width = width ;
    }
    
    public void render (Graphics g, Image image, ThumbnailCaption caption, 
            int topX, int topY) {    
        
        if (image != null) {
            g.drawImage(image, topX, topY, 
                    Graphics.TOP | Graphics.LEFT);
        }       
        
        if (null != caption) {
            caption.render(g, topX, topY, width, height);
        }
        
        if (focused) {
            g.setColor(Theme.getThumbnailBackgroundColorFocused());              
            g.drawRect(topX, topY, getWidth() - 1, getHeight() - 1);
        }    
    }
    
    public int getTopX() {
        return topX ;
    }
    
    public int getTopY() {
        return topY ;
    }
    
    public void setDimensions(int topX, int topY) {
        this.topX = topX ; 
        this.topY = topY ;
    }
    
    public boolean contains (int x, int y) {
        if (x >= getTopX() && x <= getTopX() + getWidth()) {
            if (y >= getTopY() && y <= getTopY() + getHeight()) {
                return true ;
            }
        }
        return false; 
    }  
                 
    public int getBackgroundColor () {
        return Theme.getThumbnailBackgroundColor() ;
    }
}
