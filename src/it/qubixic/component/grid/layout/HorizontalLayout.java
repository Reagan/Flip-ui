package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.ListType;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class HorizontalLayout extends AbstractLayout {
    
    private int componentWidth = 100 ;
    private int componentHeight = 100 ;
    
    public HorizontalLayout (Vector elements, 
            int width, int height, int componentsLayoutType, 
            GridConstraints gridConstraints) {
        super (width, height, elements, ListType.HORIZONTAL,
                componentsLayoutType, gridConstraints) ;
    }
    
    /**
     * sets custom dimensions to the components contained
     * within the grid
     * @param componentWidth
     * @param componentHeight 
     */
    public void setComponentDimensions (int componentWidth, 
            int componentHeight) {
       if (ComponentLayoutType.SAME_DIMENSIONS 
                == componentsLayoutType) {
           setComponentWidth (componentWidth) ;
           setComponentHeight(componentHeight);
        } 
    }   
    
    /**
     * This method actually implements the drawing algorithm 
     * to draw the horizontal grid
     * @param g  Graphics object used to draw the grid
     */
    public void drawGrid(Graphics g) {  
        Point currPoint = new Point(gridConstraints.getInnerMarginX() 
                + gridConstraints.getMarginLeft(),
                gridConstraints.getMarginTop()
                + gridConstraints.getInnerMarginY()) ;
        
        for (int i = 0, size = elements.size(); i < size; i++) {
            Thumbnail currElement = (Thumbnail) elements.elementAt(i);
            Point elementLocation = currPoint ;
            
            if (elementLocation.getY() + currElement.getHeight() < -g.getTranslateY()) {
                continue ;                
            } else if (elementLocation.getY() > (-g.getTranslateY() + getHeight())) {
                break ;
            }
             
            drawElement(g, currElement, elementLocation, i == getFocussedItem());  
            
            if (i < size - 1 ) {
                Thumbnail nextElement = (Thumbnail) elements.elementAt(i + 1);
                currPoint = getRelativeLocationForElement(nextElement, currElement, currPoint)  ;
            }
            
        }
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

    private Point getRelativeLocationForElement(Thumbnail nextElement, 
             Thumbnail currItem, 
            Point previousItemLocation) {      
        int yCoord = 0;
        int xCoord = 0;
        int componentWidth = 0 ;
        int componentHeight = 0 ; 
        
        if (ComponentLayoutType.SAME_DIMENSIONS 
                == componentsLayoutType) {
           componentWidth = this.componentWidth ;
           componentHeight = this.componentHeight ;
        } else if (ComponentLayoutType.CUSTOM_DIMENSIONS 
                == componentsLayoutType) { 
            componentWidth = currItem.getWidth() ;
            componentHeight = currItem.getHeight() ;
        }
        
        if (previousItemLocation.getX() 
               + 2 * gridConstraints.getInnerMarginX()
               + gridConstraints.getMarginRight()
               + currItem.getWidth() 
               + nextElement.getWidth() <= getWidth()) {
            xCoord = (int) previousItemLocation.getX() 
               + currItem.getWidth()
               + gridConstraints.getInnerMarginX() 
               + gridConstraints.getMarginRight() ;
            yCoord = (int) previousItemLocation.getY() ;
        } else {
            xCoord = gridConstraints.getMarginLeft()
                    + gridConstraints.getInnerMarginX(); 
            yCoord = (int) previousItemLocation.getY() 
                    + currItem.getHeight()
                    + gridConstraints.getInnerMarginY()
                    + gridConstraints.getMarginBottom() ;
        }
        
        return new Point(xCoord, yCoord);
    }
     
    /**
     * @return the componentWidth
     */
    public int getComponentWidth() {
        return componentWidth;
    }

    /**
     * @param componentWidth the componentWidth to set
     */
    public void setComponentWidth(int componentWidth) {
        this.componentWidth = componentWidth;
    }

    /**
     * @return the componentHeight
     */
    public int getComponentHeight() {
        return componentHeight;
    }

    /**
     * @param componentHeight the componentHeight to set
     */
    public void setComponentHeight(int componentHeight) {
        this.componentHeight = componentHeight;
    }    
    
    /**
     * This method calculates the height of the component
     * @return height of all the grid components
     */
    public int calculateHeight() {
        int gridHeight = 0;
        if (ComponentLayoutType.SAME_DIMENSIONS
                == componentsLayoutType) {                        
            int noOfComponentsPerRow = calculateNoOfSameDimensionsComponentsPerRow() ;
            System.out.println("Number of components per row " + noOfComponentsPerRow);
            gridHeight = (int) (Math.ceil(elements.size() / (double) noOfComponentsPerRow)
                           * componentHeight);
            System.out.println("elements.size() / noOfComponents " +  elements.size() / noOfComponentsPerRow );
        } else if (ComponentLayoutType.CUSTOM_DIMENSIONS
                == componentsLayoutType) {
           
        }
        return gridHeight;
    }
    
    private int calculateNoOfSameDimensionsComponentsPerRow() {
        int effectiveWidth = getWidth() - 2 * gridConstraints.getInnerMarginX() ;
        return (int) (effectiveWidth / 
                (this.componentWidth + gridConstraints.getMarginLeft()
                 + gridConstraints.getMarginRight())) ;
    }
}
