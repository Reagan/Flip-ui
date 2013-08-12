package it.qubixic.component.grid;

import it.qubixic.component.grid.event.GridEvent;
import it.qubixic.component.grid.event.GridEventType;
import it.qubixic.component.grid.event.GridListener;
import it.qubixic.component.grid.layout.AbstractLayout;
import it.qubixic.component.grid.layout.ComponentLayoutType;
import it.qubixic.component.grid.layout.HorizontalLayout;
import it.qubixic.component.grid.layout.VerticalLayout;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;

public class Grid extends CustomItem {

    private String title;
    private boolean displayTitle = true;
    private int listType = ListType.VERTICAL;
    private int layoutType = ComponentLayoutType.SAME_DIMENSIONS;
    private GridConstraints gridConstraints = new GridConstraints();
    private final int MIN_WIDTH = 100;
    private final int MIN_HEIGHT = 100;
    private int width = 120;
    private int height = 120;
    protected Vector elements = new Vector();
    private final String ILLEGAL_LIST_TYPE = "An illegal list type was entered. "
            + "Grid only accepts ListType.VERTICAL and ListType.HORIZONTAL as "
            + "arguments";
    private final String ILLEGAL_LAYOUT_TYPE = "An illegal layout type was passed. "
            + "Grid only accepts LayoutType.SAME_DIMENSIONS and LayoutType.CUSTOM_DIMENSIONS "
            + "as arguments.";
    private Vector gridListeners = new Vector();
    private int focussedItem = -1;
    private int lastFocussedItem = 0 ;
    private AbstractLayout layout;
    private boolean inTraversal = false;

    /**
     * Creates a default instance of a grid.
     *
     * @param title The title of the displayed grid
     */
    public Grid(String title) {
        super(title);
        initializeGrid();
    }

    /**
     * Creates an instance of a Grid with a title and type
     *
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     */
    public Grid(String title, int listType) {
        super(title);
        setListType(listType);
        initializeGrid();
    }

    /**
     * Creates an instance of a Grid component with a title, type of list and
     * type of layout for the component
     *
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     * @param layoutType the layout used to display each of the components in
     * the grid
     */
    public Grid(String title, int listType, int layoutType) {
        super(title);
        setListType(listType);
        setLayoutType(layoutType);
        initializeGrid();
    }

    /**
     * Creates an instance of a Grid component with a title, type of list and
     * type of layout for the component
     *
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     * @param layoutType the layout used to display each of the components in
     * the grid
     */
    public Grid(String title, int listType, int layoutType, GridConstraints gridConstraints) {
        super(title);
        setListType(listType);
        setLayoutType(layoutType);
        setGridConstraints(gridConstraints);
        initializeGrid();
    }

    protected void initializeGrid() {
        if (listType == ListType.VERTICAL) {
            layout = new VerticalLayout(elements, layoutType, gridConstraints) ;
        } else if (listType == ListType.HORIZONTAL) {
            layout = new HorizontalLayout(elements, layoutType, gridConstraints);
        }
    }

    /**
     * @return the width of the component
     */
    public int getWidth() {
        return width;
    }

    /**
     * sets the width of the component
     *
     * @param width
     */
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
            repaint();
        }
    }

     /**
     * @return the height of the component
     */
    public int getHeight() {
        return height;
    }

    /**
     * sets the height of the component
     *
     * @param height
     */
    public void setHeight(int height) {
        if (width > 0) {
            this.height = height;
            repaint();
        }
    }
    
    /**
     * @return the title for the grid
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title sets the grid title
     */
    public void setTitle(String title) {
        if (null == title || title.trim().length() == 0) {
            this.title = "";
        }
        this.title = title;
    }

    /**
     * checks if the grid title should be displayed or not
     *
     * @return the display title for the grid
     */
    public boolean isDisplayTitle() {
        return displayTitle;
    }

    /**
     * sets whether or not a title is displayed
     *
     * @param displayTitle the displayTitle to set
     */
    public void setDisplayTitle(boolean displayTitle) {
        this.displayTitle = displayTitle;
    }

    /**
     * @return the listType
     */
    public int getListType() {
        return listType;
    }

    /**
     * @param listType the listType to set
     */
    public void setListType(int listType) {
        if (listType != ListType.VERTICAL && listType != ListType.HORIZONTAL) {
            throw new IllegalArgumentException(ILLEGAL_LIST_TYPE);
        }
        this.listType = listType;
    }

    /**
     * @return the layoutType
     */
    public int getLayoutType() {
        return layoutType;
    }

    /**
     * @param layoutType the layoutType to set
     */
    public void setLayoutType(int layoutType) {
        if (layoutType != ComponentLayoutType.SAME_DIMENSIONS
                && layoutType != ComponentLayoutType.CUSTOM_DIMENSIONS) {
            throw new IllegalArgumentException(ILLEGAL_LAYOUT_TYPE);
        }
        this.layoutType = layoutType;
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
     * Appends a new thumbnail component to the grid
     *
     * @param thumbnail
     */
    public void append(Thumbnail thumbnail) {
        elements.addElement(thumbnail);
        repaint();
    }

    /**
     * Appends a set of thumbnails
     *
     * @param Thumbnails to be appended
     */
    public void append(Vector thumbnails) {
        if (null != thumbnails) {
            for (int thumbnailsCounter = 0;
                    thumbnailsCounter < thumbnails.size();
                    thumbnailsCounter++) {
                if (thumbnails.elementAt(thumbnailsCounter) instanceof Thumbnail) {
                    append((Thumbnail) thumbnails.elementAt(thumbnailsCounter));
                }
            }
        }
    }

    /**
     * Sets the current thumbnails with a new set of thumbnails. If the grid
     * contains a set of thumbnails, this method replaces the current set of
     * thumbnails
     *
     * @param thumbnails
     */
    public void set(Vector thumbnails) {
        if (null != thumbnails) {
            clear();
            append(thumbnails);
        }
    }

    /**
     * removes specific thumbnail from the grid
     *
     * @param thumbnail to be removed
     */
    public void remove(Thumbnail thumbnail) {
        if (elements.contains(thumbnail)) {
            elements.removeElement(thumbnail);
            repaint();
        }
    }

    /**
     * removes an index at a specified index
     *
     * @param index of the thumbnail to be removed
     */
    public void remove(int index) {
        if (elements.size() >= index) {
            elements.removeElementAt(index);
            repaint();
        }
    }

    /**
     * Clears all the components contained in the grid
     */
    public void clear() {
        elements.removeAllElements();
        repaint();
    }

    protected int getMinContentWidth() {
        return MIN_WIDTH;
    }

    protected int getMinContentHeight() {
        return MIN_HEIGHT;
    }

    protected int getPrefContentWidth(int height) {
        return width;
    }

    protected int getPrefContentHeight(int width) {       
        layout.setGridConstraints(gridConstraints);
        layout.setWidth(getWidth());
        layout.setHeight(layout.calculateHeight());
        setHeight(layout.calculateHeight());
        return height;        
    }

    protected void paint(Graphics g, int w, int h) { 
        layout.setFocussedItem(focussedItem);
        layout.drawGrid(g);
    }

    /**
     * This method responds to this grid being touched (on touch screen J2ME
     * devices)
     *
     * @param x pointer pressed X event location
     * @param y pointer pressed Y event location
     */
    protected void pointerPressed(int x, int y) {
        for (int i = 0; i < elements.size(); i++) {
            if (((Thumbnail) elements.elementAt(i)).contains(x, y)) {
                setFocusedElementIndex(i);
                repaint();
                generateGridEvent(i);
                break;
            }
        }
    }

    protected void keyPressed(int keyCode) {
        if (keyCode == Canvas.KEY_NUM5 || getGameAction(keyCode) == 8) {
            generateGridEvent(focussedItem) ;
        }
    }
    
    /**
     * This method is used to determine the traversal events created 
     * as the directional keys are selected when the grid is in focus
     * @param direction
     * @param viewportWidth
     * @param viewportHeight
     * @param visRect_inOut
     * @return 
     */
    protected boolean traverse(int direction, int viewportWidth,
            int viewportHeight, int[] visRect_inOut) {
        
        if (direction == Canvas.LEFT) {
            if (!inTraversal) {
                inTraversal = true;
            } else {
                 if (focussedItem == -1) {
                    focussedItem = lastFocussedItem ; 
                }  else {
                     if (focussedItem - 1 >= 0) {
                        focussedItem-- ;
                     } else {
                         inTraversal = false;
                         return false;
                     }
                 }
                 setFocusedElementIndex(focussedItem);
                 repaint();                
            }
        } else if (direction == Canvas.RIGHT) {
            if (!inTraversal) {
                inTraversal = true;                
            } else {
                if (focussedItem == -1) {
                    focussedItem = lastFocussedItem ; 
                } else {
                    if (focussedItem + 1 < elements.size()) {
                        focussedItem++;                        
                    } else {
                        inTraversal = false;
                        return false;
                    }
                }
                setFocusedElementIndex(focussedItem);
                repaint();
            }
        }
        
         if (direction == Canvas.UP ||
                 direction == Canvas.DOWN) {
             return false; 
         }
         
        int h = (focussedItem > 0 ? layout.calculateDisplayLocation(focussedItem) : 0) ;
        visRect_inOut[0] = 0;
        visRect_inOut[1] = h ;
        visRect_inOut[2] = getWidth() ;
        visRect_inOut[3] = layout.getDisplayHeight() ;
        return true;
    }

    /**
     * This method resets the grid and stores the last selected
     * grid component before the user focused out
     */
    protected void traverseOut() {    
        lastFocussedItem = focussedItem;
        focussedItem = -1;
        repaint();
    }
    
    /**
     * This method sets the currently selected item in the grid
     */
    protected void setFocusedElementIndex(int focusedItem) {
        this.focussedItem = focusedItem;
    }

    /**
     * This method generates a grid event
     */
    protected void generateGridEvent(int component) {
        Thumbnail currentElement = (Thumbnail) elements.elementAt(component);
        GridEvent e = new GridEvent(currentElement,
                GridEventType.GRID_CLICK,
                currentElement.getTopX(),
                currentElement.getTopY());
        triggerGridEvent(e) ;
    }
    
    /**
     * Adds a grid listener
     * @param gridListener 
     */
    public void addGridListener (GridListener gridListener) {
        gridListeners.addElement(gridListener);
    }
    
    /**
     * removes a grid listener
     * @param gridListener 
     */
    public void removeGridListener(GridListener gridListener) {
        if(gridListeners.contains(gridListener)) {
            gridListeners.removeElement(gridListener);
        }
    }
    
    /**
     * Triggers grid events
     * @param gridEvent 
     */
    protected void triggerGridEvent (GridEvent gridEvent) {
        for (int gridListenersCounter = 0; gridListenersCounter < gridListeners.size();
                gridListenersCounter++) {
            ((GridListener) gridListeners
                    .elementAt(gridListenersCounter))
                    .actionPerformed(gridEvent);
        }
    }
}
