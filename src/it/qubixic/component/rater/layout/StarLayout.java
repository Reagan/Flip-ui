package it.qubixic.component.rater.layout;

import it.qubixic.utils.Point;
import javax.microedition.lcdui.Graphics;

public class StarLayout implements RaterLayout {

    private String title = "" ;
    private int noOfComponents = 5 ; 
    private float count = 0 ;
    private final int COMPONENT_WIDTH = 40 ;
    private final int COMPONENT_HEIGHT = 40 ;
    private final int BG_COLOR = 0x999999 ;
    private final int COMPONENT_COLOR = 0xffffff ;
    private final int HIGHLIGHT_COLOR = 0xffff00 ;
    private final int ARC_RADIUS = 5 ;
    private int PADDING = 5 ;
    
    /**
     * Creates a default instance of a star layout
     */
    public StarLayout() {}
    
    /**
     * Creates an instance of a star layout 
     * with a title, specified number of components 
     * and an initial count
     * @param title title for the star component
     * @param noOfComponents number of components
     * @param count initial count for the rater
     */
    public StarLayout (String title, int noOfComponents, float count) {
        setTitle(title);
        setNoOfComponents(noOfComponents);
        setCount(count);
    }
    
    /**
     * @return the width of the component
     */
    public int getWidth() {
        return COMPONENT_WIDTH * noOfComponents + 
                (PADDING * noOfComponents);
    }
    
    /**
     * @return the height of he component
     */
    public int getHeight() {
        return COMPONENT_HEIGHT ;
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
     * @return the no Of components
     */
    public int getNoOfComponents() {
        return noOfComponents;
    }

    /**
     * @param noOfComponents the noOfComponents to set
     */
    public void setNoOfComponents(int noOfComponents) {
        this.noOfComponents = noOfComponents;
    }

    /**
     * @return the count
     */
    public float getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(float count) {
        this.count = count;
    }
    
    /**
     * Renders the rater  layout
     * @param g 
     */
    public void render(Graphics g) {
        int topX = 0 ;
        int topY = 0 ;
        
        drawBackground(g, topX, topY);
        
        topX += PADDING ;
        topY += PADDING ;
        
        if (title != "" && title != null)  {            
            drawTitle(g, getTitle(), topX, topY) ;
            topY += g.getFont().getHeight() + PADDING ;                    
        }
        
        drawComponents(g, topX, topY); 
        drawHighlight(g, count);
    }
    
    private void drawBackground(Graphics g, int topX, int topY) {
        g.setColor(BG_COLOR);
        g.fillRoundRect(topX, topY, getWidth(), getHeight(), 
                ARC_RADIUS, ARC_RADIUS);
    }
    
    private void drawTitle (Graphics g, String title, int topX, int topY) {
        
    }
    
    private void drawComponents(Graphics g, int topX, int topY) {
        int x = topX ;
        int y = topY ;        
        
        for (int componentsCounter = 0 ; componentsCounter < getNoOfComponents();
                componentsCounter++) {
            drawStarComponent(g, x + (componentsCounter * (COMPONENT_WIDTH + PADDING)), 
                    y, PADDING) ;          
        }
    }
    
    private void drawStarComponent(Graphics g, int x, int y, int padding)  {
        int effectiveWidth =  (COMPONENT_WIDTH - 2 * padding) ;
        int effectiveHeight = (COMPONENT_HEIGHT - 2 * padding) ; 
        Point center = new Point(x + effectiveWidth / 2, 
                y + effectiveHeight / 2) ;
        
        g.setColor(COMPONENT_COLOR);
        
        g.fillTriangle(x, (y + effectiveHeight / 3), 
                 (x + effectiveWidth / 3), (y + effectiveHeight / 3), 
                 (int) center.getX(), (int) center.getY());
        
        g.fillTriangle(x, (y + effectiveHeight / 3), 
                 (int) center.getX(), (int) center.getY(),
                 (x + effectiveWidth / 4), (y + effectiveHeight / 2));        
        
        g.fillTriangle((x + effectiveWidth / 5), effectiveHeight, 
                 (x + effectiveWidth / 4), (y + effectiveHeight / 2),
                 (int) center.getX(), (int) center.getY());
        
        g.fillTriangle((x + effectiveWidth / 5), effectiveHeight, 
                (int) center.getX(), (int) center.getY(),
                 (x + effectiveWidth / 2), (y + effectiveHeight / 4 * 3));
        
        g.fillTriangle((x + effectiveWidth / 2), (y + effectiveHeight / 4 * 3), 
                (int) center.getX(), (int) center.getY(),
                 (x + effectiveWidth / 5 * 4), effectiveHeight);
        
        g.fillTriangle((x + effectiveWidth / 5 * 4), effectiveHeight, 
                (int) center.getX(), (int) center.getY(),
                 (x + effectiveWidth / 4 * 3), (y + effectiveHeight / 2));
        
        g.fillTriangle((x + effectiveWidth / 4 * 3), (y + effectiveHeight / 2), 
                (int) center.getX(), (int) center.getY(),
                 x + effectiveWidth, (y + effectiveHeight / 3));
        
        g.fillTriangle(x + effectiveWidth, (y + effectiveHeight / 3), 
                (int) center.getX(), (int) center.getY(),
                (x + effectiveWidth / 3 * 2), (y + effectiveHeight / 3));
        
        g.fillTriangle((x + effectiveWidth / 3 * 2), (y + effectiveHeight / 3), 
                (int) center.getX(), (int) center.getY(),
                (x + effectiveWidth / 2), y);
        
        g.fillTriangle((x + effectiveWidth / 2), y, 
                (int) center.getX(), (int) center.getY(),
                (x + effectiveWidth / 3), (y + effectiveHeight / 3));
    }
    
    private void drawHighlight(Graphics g, float count) {
        int effectiveWidth =  (COMPONENT_WIDTH - 2 * PADDING) ;
        int effectiveHeight = (COMPONENT_HEIGHT - 2 * PADDING) ; 
    }
}
