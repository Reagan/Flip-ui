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
            Thumbnail currElement = (Thumbnail) elements.elementAt(i);
            Point elementLocation = getLocationForElement(currElement, i) ;                        
            
            if (elementLocation.getY() + currElement.getHeight() < -g.getTranslateY()) {
                continue ;                
            } else if (elementLocation.getY() > (-g.getTranslateY() + getHeight())) {
                break ;
            }
             
            drawElement(g, currElement, elementLocation, i == getFocussedItem());           
        }
    }
    
    private Point getLocationForElement(Thumbnail currItem, int currentItemIndex) {        
        int yCoord = 0;
        int heightNext = 0;
        int xCoord = 0;
        int thumbnailWidth = currItem.getWidth() ;
        
        if (currentItemIndex % getItemsPerRow(thumbnailWidth) == 0) {
            yCoord = heightNext;
            heightNext += currItem.getHeight()
                    + gridConstraints.getInnerMarginY();
            xCoord = 0 ;
        } else {
            xCoord = (currentItemIndex % getItemsPerRow(thumbnailWidth))
                    * currItem.getWidth()
                    + gridConstraints.getInnerMarginX();
        }
        return new Point(xCoord, yCoord);
    }
    
    private void drawElement(Graphics g, Thumbnail element,  
            Point elementLocation, boolean focused) {
                
        int elementWidth = element.getWidth() ;
        int elementHeight = element.getHeight() ;
        int x = (int) elementLocation.getX() ;
        int y = (int) elementLocation.getY() ;
        
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
    
    public int getItemsPerRow(int thumbnailWidth) {        
        return getWidth() / thumbnailWidth;
    }

    public int getRows(int itemsPerRow) {        
        return (int) Math.ceil(elements.size() / (double) itemsPerRow) ;
    }
}
