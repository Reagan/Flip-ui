package it.qubixic.component.Grid;

import java.util.Vector;

public final class VerticalLayout extends AbstractLayout {
    
    public VerticalLayout (Vector elements, int componentLayoutType, 
            GridConstraints gridConstraints) {
        super(elements, ListType.VERTICAL,
                componentLayoutType, gridConstraints) ;
    }

    public void drawElements() {
        
    }    
}
