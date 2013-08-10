package it.qubixic.component.grid;

import it.qubixic.component.grid.layout.ComponentLayoutType;
import it.qubixic.component.grid.layout.HorizontalLayout;
import it.qubixic.component.grid.layout.VerticalLayout;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;

public class Grid extends CustomItem {

    private String title ;
    private boolean displayTitle = true ; 
    private int listType = ListType.VERTICAL ;
    private int layoutType = ComponentLayoutType.SAME_DIMENSIONS ;
    private GridConstraints gridConstraints = new GridConstraints() ;
        
    private final int MIN_WIDTH = 100 ;
    private final int MIN_HEIGHT = 100 ;
    
    private int width = 100 ;
    private int height = 100 ;
    
    protected Vector elements = new Vector();    
    
    private final String ILLEGAL_LIST_TYPE = "An illegal list type was entered. "
            + "Grid only accepts ListType.VERTICAL and ListType.HORIZONTAL as "
            + "arguments" ;
    private final String ILLEGAL_LAYOUT_TYPE = "An illegal layout type was passed. "
            + "Grid only accepts LayoutType.SAME_DIMENSIONS and LayoutType.CUSTOM_DIMENSIONS "
            + "as arguments." ;
    
    /**
     * Creates a default instance of a grid
     * @param title The title of the displayed grid
     */
    public Grid(String title) {
        super (title) ;
    }
    
    /**
     * Creates an instance of a Grid with a title and type
     * @param title the displayed title for the grid
     * @param listType the type for which the grid can be displayed
     */
    public Grid(String title, int listType) {
        super (title) ;
        setListType(listType);
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
        this.width = width ;
        repaint();
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
        this.height = height ;
        repaint(); 
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
        HorizontalLayout hLayout ;
        VerticalLayout vLayout ;
        
        if (listType == ListType.VERTICAL) {
            
        } else if (listType == ListType.HORIZONTAL) {
            hLayout = new HorizontalLayout(elements, w, h, 
                    layoutType, gridConstraints); 
            hLayout.setWidth(width);
            hLayout.setHeight(height);
            hLayout.drawGrid(g);
        }
    }    
}
