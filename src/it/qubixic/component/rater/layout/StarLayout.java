package it.qubixic.component.rater.layout;

import it.qubixic.utils.Point;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;

public class StarLayout implements RaterLayout {

    private String title = "" ;
    private int noOfComponents = 5 ; 
    private float count = 0 ;
    private int componentWidth = 40 ;
    private int componentHeight = 35 ;
    private final int BG_COLOR = 0x999999 ;
    private final int COMPONENT_COLOR = 0xffffff ;
    private final int HIGHLIGHT_COLOR = 0xffff00 ;
    private final int TITLE_COLOR = 0xffffff ;
    private final Font TITLE_FONT = Font.getFont(Font.FACE_SYSTEM,
            Font.SIZE_MEDIUM, Font.STYLE_PLAIN) ;
    private final int SELECTED_COLOR = 0xff0000 ;
    private final int ARC_RADIUS = 5 ;
    private int PADDING = 5 ;
    private final int BUFFER = 10 ;
    private final String COUNT_ERROR_MESSAGE = 
            "The count value must be between 0 and 100";
    private int selectedComponent = -1 ;
    
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
        return componentWidth * noOfComponents + 
                (PADDING * noOfComponents);
    }
    
    /**
     * @return the height of he component
     */
    public int getHeight() {

        if (!title.equals("") && title != null) {
            componentHeight = 40 + BUFFER ;
        }
        return componentHeight;
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
     * This is the value of the count that is indicated 
     * by the rater. The value must be between 0 and
     * 100
     * @param count the count to set
     */
    public void setCount(float count) {
        if (count < 0 || count > 100) {
            throw new IllegalArgumentException(COUNT_ERROR_MESSAGE) ;
        }
        this.count = count;
    }
    
    /**
     * Renders the rater layout
     * @param g Graphics object
     */
    public void render(Graphics g) {
        int topX = 0 ;
        int topY = 0 ;
        
        drawBackground(g, topX, topY);
        
        topX += PADDING ;
        topY += PADDING ;
        
        if (title != "" && title != null)  {            
            drawTitle(g, getTitle(), topX, topY) ;
            topY += BUFFER + PADDING ;                    
        }
        
        drawComponents(g, topX, topY); 
        drawHighlightedComponents(g, topX, topY, count);
    }
    
    /**
     * Draws the background to the graphics object
     * @param g Graphics object
     * @param  topX top most X position of the component
     * @param topY top most Y position of the component
     */
    private void drawBackground(Graphics g, int topX, int topY) {
        g.setColor(BG_COLOR);
        g.fillRoundRect(topX, topY, getWidth(), getHeight(), 
                ARC_RADIUS, ARC_RADIUS);
    }
    
    /**
     * Draws the title of the component if a title exists
     * @param g Graphics object
     * @param title the title of the component
     * @param  topX top most X position of the component
     * @param topY top most Y position of the component
     */
    private void drawTitle (Graphics g, String title, int topX, int topY) {
        g.setColor(TITLE_COLOR);
        g.setFont(TITLE_FONT);
        g.drawString(title, topX, topY, Graphics.TOP | Graphics.LEFT);
    }
    
    /**
     * Draws all the components specified to represent the 
     * maximum count
     * @param g Graphics object
     * @param  topX top most X position of the component
     * @param topY top most Y position of the component
     */
    private void drawComponents(Graphics g, int topX, int topY) {
        int x = topX ;
        int y = topY ;        
        
        for (int componentsCounter = 0 ; componentsCounter < getNoOfComponents();
                componentsCounter++) {
            drawStarComponent(g, x + (componentsCounter * (componentWidth + PADDING)), 
                    y, PADDING, COMPONENT_COLOR, 1, componentsCounter == selectedComponent) ;          
        }
    }
    
    /**
     * Draws one star component. Each of the star components
     * is made up of 10 triangles that are represented in 
     * this method
     * @param g Graphics object
     * @param x top x position of the component
     * @param Y top Y position of the component
     * @param padding the padding around the star component
     * @param color the color of the star component
     * @param fractionOfStarDrawn fraction of the star to be drawn
     */
    private void drawStarComponent(Graphics g, int x, int y, int padding, 
            int color, float fractionOfStarDrawn, boolean selected)  {               
        
        if (selected) {
            fractionOfStarDrawn = 1 ; 
            color = SELECTED_COLOR ;
        }
        
        int cHeight = (!title.equals("") && title != null) ? 
                componentHeight - 15 : componentHeight ;
        int effectiveWidth =  (componentWidth - 2 * padding) ;
        int effectiveHeight = (cHeight - 2 * padding) ; 
        Point center = new Point(x + effectiveWidth / 2, 
                y + effectiveHeight / 2) ;
        
        int markedXLocation = x + (int) (effectiveWidth * fractionOfStarDrawn) ;
        
        g.setColor(color);
        
        int x11 = x; 
        int y11 = (y + effectiveHeight / 3); 
        int x12 = (x + effectiveWidth / 3); 
        int y12 = (y + effectiveHeight / 3) ; 
        int x13 = (int) center.getX() ;
        int y13 = (int) center.getY() ;
        drawTriangle(g, markedXLocation, x11, y11, x12, y12, x13, y13) ;
                        
        int x21 = x ; 
        int y21 = (y + effectiveHeight / 3);
        int x22 = (int) center.getX(); 
        int y22 = (int) center.getY() ;
        int x23 = (x + effectiveWidth / 4); 
        int y23 = (y + effectiveHeight / 2) ;
        drawTriangle(g, markedXLocation, x21, y21, x22, y22, x23, y23);
        
        int x31 = (x + effectiveWidth / 5);  
        int y31 = y + effectiveHeight ; 
        int x32 = (x + effectiveWidth / 4); 
        int y32 = (y + effectiveHeight / 2) ;
        int x33 = (int) center.getX(); 
        int y33 = (int) center.getY() ;
        drawTriangle(g, markedXLocation, x31, y31, x32, y32, x33, y33);
        
        int x41 = (x + effectiveWidth / 5) ;
        int y41 = y + effectiveHeight ;
        int x42 = (int) center.getX() ;
        int y42 = (int) center.getY() ;
        int x43 = (x + effectiveWidth / 2) ;
        int y43 = (y + effectiveHeight / 4 * 3) ;
        drawTriangle(g, markedXLocation, x41, y41, x42, y42, x43, y43);
        
        int x51 = (x + effectiveWidth / 2) ; 
        int y51 = (y + effectiveHeight / 4 * 3) ;
        int x52 = (int) center.getX() ; 
        int y52 = (int) center.getY(); 
        int x53 = (x + effectiveWidth / 5 * 4) ; 
        int y53 = y + effectiveHeight ;
        drawTriangle(g, markedXLocation, x51, y51, x52, y52, x53, y53);
        
        int x61 = (x + effectiveWidth / 5 * 4) ; 
        int y61 = y + effectiveHeight ;
        int x62 = (int) center.getX(); 
        int y62 = (int) center.getY();
        int x63 = (x + effectiveWidth / 4 * 3);
        int y63 = (y + effectiveHeight / 2) ;
        drawTriangle(g, markedXLocation, x61, y61, x62, y62, x63, y63);
        
        int x71 = (x + effectiveWidth / 4 * 3); 
        int y71 = (y + effectiveHeight / 2) ;
        int x72 = (int) center.getX(); 
        int y72 = (int) center.getY(); 
        int x73 = x + effectiveWidth ;
        int y73 = (y + effectiveHeight / 3) ;
        drawTriangle(g, markedXLocation, x71, y71, x72, y72, x73, y73);
        
        int x81 = x + effectiveWidth; 
        int y81 = (y + effectiveHeight / 3);  
        int x82 = (int) center.getX();
        int y82 = (int) center.getY();
        int x83 = (x + effectiveWidth / 3 * 2);
        int y83 = (y + effectiveHeight / 3) ;
        drawTriangle(g, markedXLocation, x81, y81, x82, y82, x83, y83);
        
        int x91 = (x + effectiveWidth / 3 * 2); 
        int y91 = (y + effectiveHeight / 3); 
        int x92 = (int) center.getX();
        int y92 = (int) center.getY();
        int x93 = (x + effectiveWidth / 2); 
        int y93 = y ;
        drawTriangle(g, markedXLocation, x91, y91, x92, y92, x93, y93);
        
        int x101 = (x + effectiveWidth / 2);
        int y101 = y;
        int x102 = (int) center.getX();
        int y102 = (int) center.getY() ;
        int x103 = (x + effectiveWidth / 3); 
        int y103 = (y + effectiveHeight / 3) ;
        drawTriangle(g, markedXLocation, x101, y101, x102, y102, x103, y103);
    }
    
    /**
     * Draws each of the triangles comprising the star component
     * @param g Graphics object
     * @param farthestXLocation farthest X location of the star component.
     * This determines whether the triangle will be drawn or not
     * @param x1 x location for first vertice
     * @param y1 y location for first vertice
     * @param x2 x location for second vertice
     * @param y2 y location for second vertice
     * @param x3 x location for third vertice
     * @param y3 y location for third vertice
     */
    private void drawTriangle (Graphics g, int farthestXLocation, 
            int x1, int y1, int x2, int y2, int x3, int y3) {
        if (x1 > farthestXLocation && x2 > farthestXLocation &&
                x3 > farthestXLocation) {
            g.setColor(COMPONENT_COLOR);
        } 
        g.fillTriangle(x1, y1, x2, y2, x3, y3);
    }
    
    /**
     * Draws the highlighted sections of the star components 
     * to reflect the current count of the rater
     * @param g Graphics object
     * @param topX top most X position for component
     * @param topY top most Y position for component
     * @param count current count for the component
     */
    private void drawHighlightedComponents(Graphics g, int topX, int topY, float count) {
        
        int x = topX ;
        int y = topY ;
        
        float percentageCountForEachComponent = getCountForEachComponent(getNoOfComponents()) ;
        float noOfComponentsUsed = count / percentageCountForEachComponent ;
        float componentStartPercentage = 0f ; 
        float componentEndPercentage ;
                            
        for (int componentsCounter = 0 ; componentsCounter < getNoOfComponents();
                componentsCounter++) {
            
            componentEndPercentage = componentStartPercentage + percentageCountForEachComponent ;
            
            if (count >= componentStartPercentage && count < componentEndPercentage) {
                float fractionOfStarHighlighted = (float) (noOfComponentsUsed - componentsCounter) ;
                drawHighlightedStar(g, x + (componentsCounter * (componentWidth + PADDING)), 
                    y, fractionOfStarHighlighted, componentsCounter == selectedComponent) ;                
                break ;
            }
            
            drawHighlightedStar(g, x + (componentsCounter * (componentWidth + PADDING)), 
                    y, 1, componentsCounter == selectedComponent) ;
            componentStartPercentage = componentEndPercentage ;
            
        }
    }
    
    /**
     * Gets the actual count assigned to a component
     * @param noOfComponents number of components
     * @return count assigned to each component
     */
    private float getCountForEachComponent(int noOfComponents) {
        return (float) ((float) 100 / noOfComponents) ;
    }
    
    /**
     * Draws a highlighted star that shows the current
     * count as assigned to the component
     * @param g Graphics object
     * @param x x location for the star component
     * @param y y location for the star component
     * @param fractionOfStarHighlighted  fraction of the star to be highlighted
     */
    private void drawHighlightedStar(Graphics g, int x, int y, 
            float fractionOfStarHighlighted, boolean selected) {
        drawStarComponent(g, x, y, PADDING, HIGHLIGHT_COLOR, 
                fractionOfStarHighlighted, selected) ;
    }
    
    /**
     * @param x x location for which the component should contain
     * @param y y location for which the component should contain
     * @return the component containing the location (x,y)
     */
    public int getSelectedComponent(int x, int y) {
        int topX = PADDING ;
        int topY = PADDING ;        
        
        for (int componentsCounter = 0 ; componentsCounter < noOfComponents;
                componentsCounter++) {
            if (x >= topX && 
                    topX <= (topX + (componentsCounter * (componentWidth + PADDING)))) {
                if (y >= topY &&
                        y <= (topY + componentHeight + PADDING)) {
                    return componentsCounter ;
                }
            }          
        }
        return -1 ;
    }
    
    /**
     * Sets the currently selected component
     * @param selectedComponent 
     */
    public void setFocusedElement(int selectedComponent) {
        if (selectedComponent >= 0 
                && selectedComponent < noOfComponents) {
            this.selectedComponent = selectedComponent;
        }
    }
    
    /**
     * @return the selected component
     */
    public int getFocusedElement() {
        return this.selectedComponent ;
    }
}
