package it.qubixic.component.grid.layout;

import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.component.theme.Theme;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Graphics;

public abstract class AbstractLayout implements Layout {

    protected Vector elements ;
    protected int orientation ;
    protected GridConstraints gridConstraints ;
    protected int componentsLayoutType ;
    private int focussedItem = 0 ;
    protected int width = 0 ;
    protected int height = 0 ;
    private int displayLocation = 0 ;
    private int displayHeight = 0 ;
    private final Point OVERLAY_MESSAGE_LOCATION = new Point(0.2f, 0.2f) ;
    
    /**
     * Creates an initial instance of a layout class to manage
     * the layout of thumbnail elements 
     * @param elements items to be displayed in the grid
     * @param orientation orientation for the elements drawn in the grid
     * @param gridConstraints  spacing between each of the components in
     * the grid
     */
    public AbstractLayout (Vector elements, 
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
    
    /**
     * 
     * @return the top most location for the displayed
     * region of the graph depending on the currently selected 
     * component
     */
    public int getDisplayLocation() {
        return displayLocation ;
    }
    
    /**
     * This method sets the location 
     * for the top most component displayed by the 
     * layout
     * @param displayLocation 
     */
    public void setDisplayLocation (int displayLocation) {
        this.displayLocation = displayLocation ;
    }
    
    /**
     * This method obtains the encompassing grid viewport
     * height
     * @return 
     */
    public int getDisplayHeight() {
        return displayHeight ;
    }
    
    /**
     * This sets the view port height to be displayed
     * by the encompassing grid
     * @param displayHeight 
     */
    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight ;
    }
    
    /**
     * This method calculates the current top most Y
     * location for the view port
     * @param focussedItem
     * @return 
     */
    public int calculateDisplayLocation(int focussedItem) {
        return ((Thumbnail) elements.elementAt(focussedItem)).getTopY();
    }
    
    /**
     * This method calculates the height of all the 
     * components in the layout
     * @return height of all the grid components
     */
    public abstract int calculateHeight() ;
    
    /**
     * This method is responsible for actually drawing 
     * the overlay message on top of the grid components
     * @param message message to be overlaid on the grid
     */
    public void drawOverlayMessage(Graphics g, String message) {
        final int ARC_RADIUS = 5 ; 
        final int PADDING = 5 ;       
        
        g.setFont(Theme.getOverlayMessageFont());
        g.setColor(Theme.getOverlayMessageBgColor()) ;
        
        int startX = getWidth() / 2 - (g.getFont().charsWidth(message.toCharArray(), 0, message.length()) +
                2 * PADDING) / 2 ;
        g.fillRoundRect(startX, 
                (int) (OVERLAY_MESSAGE_LOCATION.getY() * getHeight()), 
                g.getFont().charsWidth(message.toCharArray(), 0, message.length()) +
                2 * PADDING, 
                g.getFont().getHeight() + 2 *  PADDING,
                ARC_RADIUS,
                ARC_RADIUS);
        
        g.setColor(Theme.getOverlayMessageFontColor());
        g.drawString(message, startX + PADDING, 
                (int) (OVERLAY_MESSAGE_LOCATION.getY() * getHeight()) + PADDING,
                Graphics.TOP | Graphics.LEFT);
        
    }
}
