package it.qubixic.component.scroller;

import it.qubixic.component.theme.Theme;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;

public class Scroller extends CustomItem {

    private String title = "" ;
    private Viewer viewer ;
    private int height = 120 ; 
    private int width = 120 ;
    private int topX = 0 ;
    private int topY = 0 ;
    
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
        if(!title.equals("")) {
            height = 144 ;
        }
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
        drawTitle(g, topX, topY) ;
        viewer.draw(g, topX, topY, w, h) ;
        this.topY = 0 ;
    }
    
    /**
     * This method draws the actual scroller
     * @param g Graphics object
     * @param topX top X position of scroller
     * @param topY top Y position of scroller
     */
    protected void drawTitle(Graphics g, int topX, int topY) {
        if(!title.equals("")) {
            g.setFont(Theme.getScrollerTitleFont());
            g.setColor(Theme.getScrollerTitleColor()) ;
            g.drawString(title, topX, topY, Graphics.TOP | Graphics.LEFT);
            this.topY = g.getFont().getHeight() ;            
        }
    }
    
    /**
     * Initiates a change in the current view of
     * the scroller
     * @param viewAction the kind of action that the scroller should initiate.
     * This could be an action to view the next item or to view a 
     * previous item
     */
    public void changeView (ViewAction viewAction) {
        viewer.changeView(viewAction) ;
        repaint();
    }
}
