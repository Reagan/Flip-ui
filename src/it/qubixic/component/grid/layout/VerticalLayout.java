package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.ListType;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public final class VerticalLayout extends AbstractLayout {
    
    public VerticalLayout (int width, int height,
            Vector elements, int componentLayoutType, 
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
            drawVerticalGridCustomDimensions(g) ;
        }        
    }    
    
    public void drawVerticalGridSameComponentDimensions(Graphics g) {
        
    }
    
    public void drawVerticalGridCustomDimensions(Graphics g) {
        
    }
}
