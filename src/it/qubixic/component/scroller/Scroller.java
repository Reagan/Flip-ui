package it.qubixic.component.scroller;

import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;

public class Scroller extends CustomItem {

    private String title = "" ;
    private Viewer viewer ;
    private int height = 120 ; 
    private int width = 120 ;
    
    /**
     * Creates a title-less scroller with a viewer
     * component
     * @param viewer component that displays a view(s)    
     */
    public Scroller (Viewer viewer) {
        super("") ;
        setViewer(viewer);
    }
    
    /**
     * Creates a scroller with a title and an item 
     * viewer
     * @param title title for the scroller
     * @param viewer component that displays a view(s)
     */
    public Scroller (String title, Viewer viewer) {
        super("") ;
        setTitle(title);
        setViewer(viewer);
    }
    
    /**
     * Creates a scroller with a title, viewer, width and height
     * @param title title for the scroller
     * @param viewer component that displays a view(s)
     * @param width width of the scroller
     * @param height height of the scroller
     */
    public Scroller (String title, Viewer viewer, int width, int height) {
        super("") ;
        setTitle(title);
        setViewer(viewer);
        setWidth(width);
        setHeight(height);
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the viewer
     */
    public Viewer getViewer() {
        return viewer;
    }

    /**
     * @param viewer the viewer to set
     */
    public void setViewer(Viewer viewer) {
        this.viewer = viewer;
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
    
    protected int getMinContentWidth() {
        return width ;
    }

    protected int getMinContentHeight() {
        return height ;
    }

    protected int getPrefContentWidth(int height) {
        return width ;
    }

    protected int getPrefContentHeight(int width) {
        return height ;
    }

    protected void paint(Graphics g, int w, int h) {
        viewer.draw(g, w, h) ;
    }
    
    public void changeView (ViewAction viewAction) {
        viewer.changeView(viewAction) ;
    }
}
