package it.qubixic.component.grid;

import it.qubixic.component.grid.event.GridEvent;
import it.qubixic.component.grid.event.GridEventType;
import it.qubixic.component.grid.event.GridListener;
import it.qubixic.component.grid.layout.ComponentLayoutType;
import it.qubixic.component.grid.layout.HorizontalLayout;
import it.qubixic.component.grid.layout.VerticalLayout;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;

public class Grid extends CustomItem {

    private String title ;
    private boolean displayTitle = true ; 
    private int listType = ListType.VERTICAL ;
    private int layoutType = ComponentLayoutType.SAME_DIMENSIONS ;
    private GridConstraints gridConstraints = new GridConstraints() ;
        
    private final int MIN_WIDTH = 100 ;
    private final int MIN_HEIGHT = 100 ;
    
    private final int DEFAULT_WIDTH = 120 ;
    
    private int width = 1000000 ;
    private int height = 1000000 ;
    
    protected Vector elements = new Vector();    
    
    private final String ILLEGAL_LIST_TYPE = "An illegal list type was entered. "
            + "Grid only accepts ListType.VERTICAL and ListType.HORIZONTAL as "
            + "arguments" ;
    private final String ILLEGAL_LAYOUT_TYPE = "An illegal layout type was passed. "
            + "Grid only accepts LayoutType.SAME_DIMENSIONS and LayoutType.CUSTOM_DIMENSIONS "
            + "as arguments." ;
    
    private GridListener gridListener ;
    private  int focussedItem = 0 ;
    
    private HorizontalLayout hLayout ;
    private VerticalLayout vLayout ;
    
    private boolean inTraversal = false; 
    
    /**
     * Creates a default instance of a grid.
     * @param title The title of the displayed grid
     */
    public Grid(String title) {
        super (title) ;
        initializeGrid() ;
    }
    
    /**
     * Creates an instance of a Grid with a title and type
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     */
    public Grid(String title, int listType) {
        super (title) ;
        setListType(listType);
        initializeGrid() ;
    }
    
    /**
     * Creates an instance of a Grid component with a title,
     * type of list and type of layout for the component
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     * @param layoutType the layout used to display each of the components
     * in the grid
     */
    public Grid(String title, int listType, int layoutType) {
        super(title) ;
        setListType(listType);
        setLayoutType(layoutType);
        initializeGrid() ;
    }
    
    /**
     * Creates an instance of a Grid component with a title,
     * type of list and type of layout for the component
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     * @param layoutType the layout used to display each of the components
     * in the grid
     */
    public Grid(String title, int listType, int layoutType, GridConstraints gridConstraints) {
        super(title) ;
        setListType(listType);
        setLayoutType(layoutType);
        setGridConstraints(gridConstraints);
        initializeGrid() ;
    }
    
    protected void initializeGrid() {
        if (listType == ListType.VERTICAL) {
            
        } else if (listType == ListType.HORIZONTAL) {
            hLayout = new HorizontalLayout(elements, layoutType, gridConstraints); 
        }          
    }
    
    /**   
     * @return the width of the component
     */
    public int getWidth() {
        return width ;
    }
    
    /**
     * sets the width of the component
     * @param width 
     */
    public void setWidth (int width) {
        if(width > 0 ) {
            this.width = width ;
            repaint();
        }
    }

    /**
     * @return the height of the grid
     */
    public int getHeight () {
        return height ;
    }        
    
    /**
     * sets the height of the grid
     * @param height 
     */
    public void setHeight (int height) {
        if(height > 0) {
            this.height = height ;
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
            this.title = "" ;
        }
        this.title = title;
    }

    /**
     * checks if the grid title should be displayed
     * or not
     * @return the display title for the grid
     */
    public boolean isDisplayTitle() {
        return displayTitle;
    }

    /**
     * sets whether or not a title is displayed
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
            throw new IllegalArgumentException(ILLEGAL_LIST_TYPE) ;
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
     * @param thumbnail 
     */
    public void append(Thumbnail thumbnail) {        
        elements.addElement(thumbnail);
        repaint();      
    }
    
    /**
     * Appends a set of thumbnails 
     * @param Thumbnails to be appended
     */
    public void append(Vector thumbnails) {
        if (null != thumbnails) {
            for (int thumbnailsCounter = 0 ; 
                    thumbnailsCounter < thumbnails.size();
                    thumbnailsCounter++) {
                if(thumbnails.elementAt(thumbnailsCounter) instanceof Thumbnail) {
                    append((Thumbnail) thumbnails.elementAt(thumbnailsCounter)) ;
                }
            }
        }
    }
    
    /**
     * Sets the current thumbnails with a new set of thumbnails. If
     * the grid contains a set of thumbnails, this method replaces 
     * the current set of thumbnails 
     * @param thumbnails 
     */
    public void set(Vector thumbnails) {
        if (null != thumbnails) {
            clear();
            append(thumbnails) ;
        }
    }
    
    /**
     * removes specific thumbnail from the grid
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
     * @param index of the thumbnail to be removed
     */
    public void remove (int index) {
        if (elements.size() >= index) {
            elements.removeElementAt(index);
            repaint();
        }
    }
    
    /**
     * Clears all the components contained in the grid
     */
    public void clear () {
        elements.removeAllElements();        
        repaint();
    }        
    
    protected int getMinContentWidth() {
        return MIN_WIDTH ;
    }

    protected int getMinContentHeight() {
        return MIN_HEIGHT ;
    }

    protected int getPrefContentWidth(int height) {
        return getWidth() ; 
    }

    protected int getPrefContentHeight(int width) {
        return getHeight() ;
    }

    protected void paint(Graphics g, int w, int h) {    
        System.out.println("P called");
        if (listType == ListType.VERTICAL) {
            
        } else if (listType == ListType.HORIZONTAL) {
            System.out.println("Paint called " + focussedItem);
            hLayout.setGridConstraints(gridConstraints);
            hLayout.setFocussedItem(focussedItem);
            hLayout.setWidth((width == -1) ? DEFAULT_WIDTH : getWidth());
            hLayout.setHeight((height == -1) ? hLayout.calculateHeight() : getHeight());
            hLayout.drawGrid(g);
        }
    }    
    
    /**
     * This method responds to this grid being touched (on touch screen J2ME devices)
     * @param x pointer pressed X event location
     * @param y  pointer pressed Y event location
     */
    protected void pointerPressed(int x, int y) {  
        for (int i = 0 ; i < elements.size(); i++) {
            if (((Thumbnail) elements.elementAt(i)).contains(x,y)) {  
                setFocusedElementIndex(i);
                repaint();
                GridEvent e = new GridEvent((Thumbnail) elements.elementAt(i),
                        GridEventType.GRID_CLICK, x, y) ;
                gridListener.actionPerformed(e);
                break ;
            }
        }
    }     
    
    /**
     * This method is called when a user traverses out of the 
     * grid and repaints its state
     */
    /*
    protected void traverseOut() {
        setFocusedElementIndex(0) ;
        inTraversal = false ;
        repaint();
    }
    */

    protected boolean traverse (int direction, int viewportWidth,
           int viewportHeight, int[] visRect_inOut) {
        System.out.println("traverse called " + direction
                + " focussedItem " + focussedItem);
        
        if (direction == Canvas.UP || direction == Canvas.LEFT) {
           repaint();
        } else if (direction == Canvas.DOWN || direction == Canvas.RIGHT) {
            if (!inTraversal) {
                inTraversal = true;
            } else {
                if (focussedItem < elements.size()) {     
                    System.out.println("Y");
                    focussedItem++;
                    setFocusedElementIndex(focussedItem);
                    repaint();
                } else {
                    inTraversal = false ;
                    return false; 
                }
            }
        } else if (direction == CustomItem.NONE) {
                Thumbnail currentElement = (Thumbnail) elements.elementAt(focussedItem) ;
                GridEvent e  = new GridEvent(currentElement,
                        GridEventType.GRID_CLICK, 
                        currentElement.getTopX(), 
                        currentElement.getTopY()) ;
                gridListener.actionPerformed(e);
        }
        
        return true; 
    }
      
    /**
     * This method sets the currently selected item 
     * in the grid
     */
    protected void setFocusedElementIndex(int focusedItem) {
        this.focussedItem = focusedItem ;
    }
}
