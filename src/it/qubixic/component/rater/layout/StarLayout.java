package it.qubixic.component.rater.layout;

import it.qubixic.utils.Point;
import javax.microedition.lcdui.Graphics;

public class StarLayout implements RaterLayout {

    private String title = "" ;
    private int noOfComponents = 2; 
    private int count = 0 ;
    private final int COMPONENT_WIDTH = 40 ;
    private final int COMPONENT_HEIGHT = 40 ;
    private final int BG_COLOR = 0x999999 ;
    private final int COMPONENT_COLOR = 0xffffff ;
    private final int HIGHLIGHT_COLOR = 0xffff00 ;
    private final int ARC_RADIUS = 5 ;
    private int PADDING = 5 ;
    
    public StarLayout() {}
    
    public StarLayout (String title, int noOfComponents, int count) {
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
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * Renders the rater  layout
     * @param g 
     */
    public void render(Graphics g) {
        drawBackground(g);
        drawComponents(g); 
    }
    
    private void drawBackground(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 
                ARC_RADIUS, ARC_RADIUS);
    }
    
    private void drawComponents(Graphics g) {
        int x = ARC_RADIUS ;
        int y = ARC_RADIUS ;        
        
        for (int componentsCounter = 0 ; componentsCounter < getNoOfComponents();
                componentsCounter++) {
            System.out.println("x " + (x + (componentsCounter * (COMPONENT_WIDTH + PADDING))));
            drawStarComponent(g, x + (componentsCounter * (COMPONENT_WIDTH + PADDING)), 
                    y, PADDING) ;          
        }
    }
    
    private void drawStarComponent(Graphics g, int x, int y, int padding)  {
        System.out.println("Starting point " + x + "," + y);
        int effectiveWidth =  (COMPONENT_WIDTH - 2 * padding) ;
        int effectiveHeight = (COMPONENT_HEIGHT - 2 * padding) ; 
        Point center = new Point(x + effectiveWidth / 2, 
                y + effectiveHeight / 2) ;
        System.out.println("center " + center.getX() + " ," + center.getY()) ;
        
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
}
