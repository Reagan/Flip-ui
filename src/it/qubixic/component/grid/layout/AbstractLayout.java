package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import java.util.Vector;

public abstract class AbstractLayout implements Layout {

    protected Vector elements ;
    protected int orientation ;
    protected GridConstraints gridConstraints ;
    protected int componentsLayoutType ;
    private int focussedItem = 0 ;
    private int width = 0 ;
    private int height = 0 ;
    
    /**
     * Creates an initial instance of a layout class to manage
     * the layout of thumbnail elements 
     * @param elements items to be displayed in the grid
     * @param orientation orientation for the elements drawn in the grid
     * @param gridConstraints  spacing between each of the components in
     * the grid
     */
    public AbstractLayout (int width, int height, Vector elements, 
            int orientation, int componentLayoutType, 
            GridConstraints gridConstraints) {
        setElements(elements);
        setOrientation(orientation);
        setComponentsLayoutType(componentLayoutType);
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

    /**
     * @return the focussedItem
     */
    public int getFocussedItem() {
        return focussedItem;
    }

    /**
     * @param focussedItem the focussedItem to set
     */
    public void setFocussedItem(int focussedItem) {
        this.focussedItem = focussedItem;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
