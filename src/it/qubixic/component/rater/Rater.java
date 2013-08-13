package it.qubixic.component.rater;

import it.qubixic.component.rater.layout.RaterLayout;
import it.qubixic.component.rater.layout.StarLayout;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;

public class Rater extends CustomItem {
    
    private float count = 0 ;
    private String title = "" ;
    private RaterLayout raterLayout = new StarLayout() ;
    private int noOfComponents = 5 ;
    
    /**
     * Creates a default rater
     */
    public Rater() {
        super("");
    }
    
    /**
     * Creates a rater with an initial
     * counter
     * @param count initial value for the rater
     */
    public Rater (float count) {
        super("");
        setCount(count);
    }
     
    /**
     * Creates a rater with a custom number of 
     * components
     * @param noOfComponents number of components
     */
    public Rater (int noOfComponents) {
        super("") ;
        setNoOfComponents(noOfComponents);
    }
    
    /**
     * Creates a rater with a default title
     * and count
     * @param title title for the rater
     * @param count initial value for the rater
     */
    public Rater (String title, int noOfComponents) {
        super("");
        setTitle(title);
        setNoOfComponents(noOfComponents);
    }  
    
    /**
     * Creates a rater with a default title
     * and count
     * @param title title for the rater
     * @param count initial value for the rater
     */
    public Rater (String title, float count) {
        super("");
        setTitle(title);
        setCount(count);
    }   

    /**
     * Creates a rater with a title, count and number of 
     * components used to display the count
     * @param title
     * @param count
     * @param noOfComponents 
     */
    public Rater(String title, float count, int noOfComponents) {
        super("") ;
        setTitle(title);
        setCount(count);
        setNoOfComponents(noOfComponents);
    }
    
    /**
     * Creates a rater with a title, initial count and a custom 
     * rater layout
     * @param title
     * @param count
     * @param raterLayout 
     */
    public Rater (String title, float count, RaterLayout raterLayout) {
        super("");
        setTitle(title);
        setCount(count);
        setRaterLayout(raterLayout);
    }
    
    /**
     * Creates an initial rater with a title, count, no of 
     * components and a custom rater layout
     * @param title title for the rater
     * @param count initial count for the rater
     * @param noOfComponents no of components
     * @param raterLayout custom rater layout for the layout
     */
    public Rater (String title, float count, int noOfComponents, 
            RaterLayout raterLayout) {
        super("");
        setTitle(title);
        setCount(count);
        setRaterLayout(raterLayout);
        setNoOfComponents(noOfComponents);
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
    
    protected int getMinContentWidth() {
        return raterLayout.getWidth(); 
    }

    protected int getMinContentHeight() {
        return raterLayout.getHeight() ;
    }

    protected int getPrefContentWidth(int height) {
        return raterLayout.getWidth() ;
    }

    protected int getPrefContentHeight(int width) {
        return raterLayout.getHeight() ;
    }

    protected void paint(Graphics g, int w, int h) {
        getRaterLayout().setTitle(getTitle()) ;
        getRaterLayout().setCount(getCount()) ;
        getRaterLayout().setNoOfComponents(getNoOfComponents()) ;
        getRaterLayout().render(g);        
    }

    /**
     * @return the raterLayout
     */
    public RaterLayout getRaterLayout() {
        return raterLayout;
    }

    /**
     * @param raterLayout the raterLayout to set
     */
    public void setRaterLayout(RaterLayout raterLayout) {
        this.raterLayout = raterLayout;
    }

    /**
     * @return the noOfComponents
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
}
