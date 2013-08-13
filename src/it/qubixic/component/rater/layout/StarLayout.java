package it.qubixic.component.rater.layout;

import it.qubixic.utils.Point;
import javax.microedition.lcdui.Graphics;

public class StarLayout implements RaterLayout {

    private String title = "" ;
    private int noOfComponents = 5; 
    private int count = 0 ;
    private final int COMPONENT_WIDTH = 20 ;
    private final int COMPONENT_HEIGHT = 20 ;
    private final int BG_COLOR = 0x999999 ;
    private final int COMPONENT_COLOR = 0xffffff ;
    private final int HIGHLIGHT_COLOR = 0xffff00 ;
    private final int ARC_RADIUS = 5 ;
    
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
        return COMPONENT_WIDTH * noOfComponents ;
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
            drawStarComponent(g, x, y) ;
            x += COMPONENT_WIDTH ;            
        }
    }
    
    private void drawStarComponent(Graphics g, int x, int y)  {
        Point center = new Point((x + COMPONENT_WIDTH) / 2, 
                (y + COMPONENT_HEIGHT) / 2) ;
        g.setColor(COMPONENT_COLOR);
        g.fillTriangle(x, (y + COMPONENT_HEIGHT / 3), 
                 (x + COMPONENT_WIDTH / 3), (y + COMPONENT_HEIGHT / 3), 
                 (int) center.getX(), (int) center.getY());
    }
}
