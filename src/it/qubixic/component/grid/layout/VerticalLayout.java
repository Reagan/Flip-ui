package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.ListType;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class VerticalLayout extends AbstractLayout {
    
    private int componentWidth = 200 ;
    private int componentHeight = 100 ;
    private final int BUFFER = 40 ; 
    
    public VerticalLayout (Vector elements, int componentLayoutType, 
            GridConstraints gridConstraints) {
        super(elements, ListType.VERTICAL,
                componentLayoutType, gridConstraints) ;
    }

    public void drawGrid(Graphics g) {
        if (ComponentLayoutType.SAME_DIMENSIONS 
                == componentsLayoutType) {
            drawVerticalGridSameComponentDimensions(g) ;
        } else if (ComponentLayoutType.CUSTOM_DIMENSIONS 
                == componentsLayoutType) { 
            drawVerticalGridCustomComponentDimensions(g) ;
        }        
    }    
    
    /**
     * Draws the grid with a set of vertically aligned 
     * components with varying dimensions
     * @param g 
     */
    public void drawVerticalGridCustomComponentDimensions(Graphics g) {
        
    }
    
    /**
     * Draws the grid with a set of vertically aligned 
     * components all of the same dimensions
     * @param g Graphics object
     */
    public void drawVerticalGridSameComponentDimensions(Graphics g) {
        Thumbnail currElement = null ;
        Thumbnail nextElement = null ;
        
        Point currPoint = new Point(gridConstraints.getInnerMarginX() 
                + gridConstraints.getMarginLeft(),
                gridConstraints.getMarginTop()
                + gridConstraints.getInnerMarginY()) ;
        
        setDisplayHeight(g.getClipHeight());
        
        for (int i = 0, size = elements.size(); i < size; i++) {
            currElement = (Thumbnail) elements.elementAt(i);
            Point elementLocation = currPoint ;
            
            if (i < size - 1) {
                nextElement = (Thumbnail) elements.elementAt(i + 1);
                currPoint = getRelativeLocationForElement(nextElement, currElement, currPoint);               
            }
            
            if (currElement.getTopY() + currElement.getHeight() 
                    + gridConstraints.getMarginBottom()
                    + gridConstraints.getInnerMarginY()
                    < -g.getTranslateY()) {
                continue ;                
            } else if (currElement.getTopY() - gridConstraints.getInnerMarginY()
                    - gridConstraints.getMarginTop() - BUFFER
                    > (-g.getTranslateY() + g.getClipHeight())) {     
                break ;
            }
             
            drawElement(g, currElement, elementLocation, i == getFocussedItem());                                   
        }
    }
    
     private void drawElement(Graphics g, Thumbnail element,  
            Point elementLocation, boolean focused) {
        int elementWidth = element.getWidth() ;
        int elementHeight = element.getHeight() ;
        int x = (int) elementLocation.getX() ;
        int y = (int) elementLocation.getY() ;
               
        g.setColor(element.getBackgroundColor());
        g.fillRect(x, y, elementWidth, elementHeight);   
        
        if (focused) {
            element.setFocussed(true) ; 
        } else {
            element.setFocussed(false);
        }
        
        element.render(g, element.getImage(), element.getCaption(), x, y);                                    
    }                

    
    private Point getRelativeLocationForElement(Thumbnail nextElement, 
             Thumbnail currItem, 
            Point previousItemLocation) {      
        int yCoord = 0;
        int xCoord = gridConstraints.getInnerMarginX() +
                gridConstraints.getMarginLeft();
        int componentWidth = 0 ;
        int componentHeight = 0 ; 
        
        if (ComponentLayoutType.SAME_DIMENSIONS 
                == componentsLayoutType) {
           componentWidth = getWidth() - gridConstraints.getInnerMarginX()
                   - gridConstraints.getMarginRight();
           componentHeight = this.componentHeight ;
        } else if (ComponentLayoutType.CUSTOM_DIMENSIONS 
                == componentsLayoutType) { 
            componentWidth = currItem.getWidth() ;
            componentHeight = currItem.getHeight() ;
        }
        
        yCoord = (int) previousItemLocation.getY() +
                currItem.getHeight() + 
                gridConstraints.getMarginBottom() +
                gridConstraints.getInnerMarginY() +
                gridConstraints.getMarginTop() ;               
        
        return new Point(xCoord, yCoord);
    }
    
    /**
     * This method calculates the height of all the 
     * components in the layout
     * @return height of all the grid components
     */
    public int calculateHeight() {
        int gridHeight = 0;                                              
        gridHeight += 2 * gridConstraints.getInnerMarginY()
                + (gridConstraints.getMarginTop() + gridConstraints.getMarginBottom()) 
                * elements.size(); 
        
        for (int elementsCounter =0 ; elementsCounter < elements.size();
                elementsCounter++) {
            gridHeight += ((Thumbnail) elements.elementAt(elementsCounter)).getHeight() ;
        }
        return gridHeight;
    }
}

