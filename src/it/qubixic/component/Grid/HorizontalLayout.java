package it.qubixic.component.Grid;

import java.util.Vector;

public final class HorizontalLayout extends AbstractLayout {

    public HorizontalLayout (Vector elements, int componentsLayoutType, 
            GridConstraints gridConstraints) {
        super (elements, ListType.HORIZONTAL,
                componentsLayoutType, gridConstraints) ;
    }
    
    public void drawElements() {
        
    }    
}
