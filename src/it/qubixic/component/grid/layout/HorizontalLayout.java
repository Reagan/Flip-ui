package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.ListType;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class HorizontalLayout extends AbstractLayout {

    public HorizontalLayout (Vector elements, 
            int width, int height, int componentsLayoutType, 
            GridConstraints gridConstraints) {
        super (width, height, elements, ListType.HORIZONTAL,
                componentsLayoutType, gridConstraints) ;
    }
    
    public void drawElements(Graphics g) {
        if (ComponentLayoutType.SAME_DIMENSIONS 
                == componentsLayoutType) {
            drawHorizontalGridSameComponentDimensions(g) ;
        } else if (ComponentLayoutType.CUSTOM_DIMENSIONS 
                == componentsLayoutType) { 
            drawHorizontalGridCustomDimensions(g) ;
        }
    }    
    
    private void drawHorizontalGridSameComponentDimensions(Graphics g) {                     
        for (int i = 0, size = elements.size(); i < size; i++) {            
            Point elementLocation = getElementForLocation(i) ;            
            Thumbnail currElement = (Thumbnail) elements.elementAt(i);
            
            if (elementLocation.getY() + currElement.getHeight() < -g.getTranslateY()) {
                continue ;                
            } else if (elementLocation.getY() > (-g.getTranslateY() + getHeight())) {
                break ;
            }
             
            drawElement(currElement, g, (int) elementLocation.getX(), 
                    (int) elementLocation.getY(), i == getFocussedItem());           
        }
    }
    
    private Point getElementForLocation(int currentItem) {
        
        int yCoord = 0;
        int heightNext = 0;
        int xCoord = 0;

        if (currentItem % getItemsPerRow() == 0) {
            yCoord = heightNext;
            heightNext += ((Thumbnail) elements.elementAt(currentItem)).getHeight()
                    + gridConstraints.getInnerMarginY();
            xCoord = (currentItem % getItemsPerRow()) * ((Thumbnail) elements.elementAt(0)).getWidth();
        } else {
            xCoord = (currentItem % getItemsPerRow()) * ((Thumbnail) elements.elementAt(0)).getWidth()
                    + gridConstraints.getInnerMarginX();
        }
        return new Point(xCoord, yCoord);
    }
    
    private void drawElement(Thumbnail element, Graphics g, 
            int x, int y, boolean focused) {
                
        int elementWidth = element.getWidth() ;
        int elementHeight = element.getHeight() ;
        
        g.setClip(x, y, elementWidth, elementHeight);        
        g.setColor(element.getBackgroundColor());
        g.fillRect(x, y, elementWidth, elementHeight);   
        
        if (focused) {
            element.setFocussed(true) ;
        }
        element.render(g, x, y);                                    
    }  
    
    private void drawHorizontalGridCustomDimensions(Graphics g) {
        
    }
    
    public int getItemsPerRow() {
        
        return getWidth() / Thumbnail.WIDTH ;
    }

    public int getRows(int itemsPerRow) {        
        return (int) Math.ceil( elements.size() / (double) itemsPerRow) ;
    }
}
