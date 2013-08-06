package it.qubixic.component.grid.thumbnail;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public abstract class Thumbnail implements IThumbnail {

    private ThumbnailCaption caption ;
    private Image imagePart;    
    private boolean selected = false;    
    public static final int WIDTH = 100 ;
    public static final int HEIGHT = 100 ;
    public int topX = 0 ; 
    public int topY = 0 ;
    protected boolean focused = false ;
    
    public Thumbnail() {}
    
    public Thumbnail(ThumbnailCaption caption, Image imagePart) {        
        this.caption = caption ;
        this.imagePart = imagePart;
    }

    public void setImage(Image image) {
        this.imagePart = image ;
    }
    
    public Image getImage() {
        return imagePart;
    }
    
    public ThumbnailCaption getCaption () {
        return caption ;
    }    
    
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getHeight() {
        return HEIGHT ;
    }
    
    public int getWidth() {
        return WIDTH ;
    } 
    
    public void render (Graphics g, int topX, int topY) {            
        setDimensions(topX, topY) ;
        if (getImage() != null) {
            g.drawImage(getImage(), topX, topY,
                Graphics.LEFT | Graphics.TOP);
        }       
        
        if (focused) {
            g.setColor(theme.getBackgroundColorFocused());              
            g.drawRect(topX, topY, getWidth() - 1, getHeight() - 1);
        }    
    }
    
    public int getTopX() {
        return topX ;
    }
    
    public int getTopY() {
        return topY ;
    }
    
    public boolean contains (int x, int y) {
        if (x >= getTopX() && x <= getTopX() + getWidth()) {
            if (y >= topY && y <= getTopY() + getHeight()) {
                return true ;
            }
        }
        return false; 
    }
    
    private void setDimensions(int topX, int topY) {
        this.topX = topX ; 
        this.topY = topY ;
    }  
        
    public boolean getFocussed () {
        return focused ;
    }
    
    public void setFocussed (boolean state) {
        this.focused = state ;
    }    
}
