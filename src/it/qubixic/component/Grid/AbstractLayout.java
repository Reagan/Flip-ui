package it.qubixic.component.Grid;

import java.util.Vector;

public abstract class AbstractLayout implements Layout {

    private Vector elements ;
    private int orientation ;
    private GridConstraints gridConstraints ;
    private int componentsLayoutType ;
    
    /**
     * Creates an initial instance of a layout class to manage
     * the layout of thumbnail elements 
     * @param elements items to be displayed in the grid
     * @param orientation orientation for the elements drawn in the grid
     * @param gridConstraints  spacing between each of the components in
     * the grid
     */
    public AbstractLayout (Vector elements, int orientation, 
            int componentLayoutType, GridConstraints gridConstraints) {
        setElements(elements);
        setOrientation(orientation);
        setComponentsLayoutType(componentsLayoutType);
        setGridConstraints(gridConstraints);
    }

    /**
     * @return the elements
     */
    public Vector getElements() {
        return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(Vector elements) {
        this.elements = elements;
    }

    /**
     * @return the orientation
     */
    public int getOrientation() {
        return orientation;
    }

    /**
     * @param orientation the orientation to set
     */
    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    /**
     * @return the gridConstraints
     */
    public GridConstraints getGridConstraints() {
        return gridConstraints;
    }

    /**
     * @param gridConstraints the gridConstraints to set
     */
    public void setGridConstraints(GridConstraints gridConstraints) {
        this.gridConstraints = gridConstraints;
    }

    /**
     * @return the componentsLayoutType
     */
    public int getComponentsLayoutType() {
        return componentsLayoutType;
    }

    /**
     * @param componentsLayoutType the componentsLayoutType to set
     */
    public void setComponentsLayoutType(int componentsLayoutType) {
        this.componentsLayoutType = componentsLayoutType;
    }
}
