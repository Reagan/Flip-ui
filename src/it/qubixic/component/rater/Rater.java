package it.qubixic.component.rater;

import it.qubixic.component.rater.event.RaterEvent;
import it.qubixic.component.rater.event.RaterListener;
import it.qubixic.component.rater.layout.RaterLayout;
import it.qubixic.component.rater.layout.StarLayout;
import java.util.Vector;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Canvas;

public class Rater extends CustomItem {
    
    private RaterLayout raterLayout = new StarLayout() ;
    private Vector raterListeners = new Vector() ;
    private boolean inTraversal = false; 
    private int lastFocussedItem = 0 ;
    
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
     * Creates a rater component with a custom number of 
     * components and an initial count
     * @param noOfComponents  number of components to be used to display the
     * rating
     * @param count initial count to be displayed on the rater
     */
    public Rater (int noOfComponents, float count) {
        super("") ;
        setNoOfComponents(noOfComponents);
        setCount(count);
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
        return getRaterLayout().getCount();
    }

    /**
     * @param count the count to set
     */
    public void setCount(float count) {
        getRaterLayout().setCount(count) ;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return getRaterLayout().getTitle() ;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        getRaterLayout().setTitle(title) ;
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
        return getRaterLayout().getNoOfComponents();
    }

    /**
     * @param noOfComponents the noOfComponents to set
     */
    public void setNoOfComponents(int noOfComponents) {
        getRaterLayout().setNoOfComponents(noOfComponents) ;
    }
    
    /**
     * Listens for pointer pressed events
     * @param x x location for the pointer pressed action
     * @param y y location for the pointer pressed action
     */
    protected void pointerPressed(int x, int y) {
        int selectedComponent = getRaterLayout().getSelectedComponent(x, y);
        if (-1 != selectedComponent) {
            getRaterLayout().setFocusedElement(selectedComponent);
            repaint();
            generateRaterEvent(selectedComponent);
        }
    }
    
    /**
     * Checks whether the key 5 or center button was pressed
     * and generates a key event
     * @param keyCode 
     */
    protected void keyPressed(int keyCode) {
        if (keyCode == Canvas.KEY_NUM5 || getGameAction(keyCode) == 8) {
            generateRaterEvent(getRaterLayout().getFocusedElement()) ;
        }
    }
    
    /**
     * Generated a rater selected event
     * @param selectedComponent 
     */
    protected void generateRaterEvent(int selectedComponent) {   
        RaterEvent raterEvent = new RaterEvent(this, selectedComponent) ;
        triggerRaterEvent(raterEvent) ;
    }
    
    /**
     * Adds a rater listener
     * @param grater Listener 
     */
    public void addGridListener (RaterListener raterListener) {
        raterListeners.addElement(raterListener);
    }
    
    /**
     * removes a rater listener
     * @param rater Listener 
     */
    public void removeGridListener(RaterListener raterListener) {
        if(raterListeners.contains(raterListener)) {
            raterListeners.removeElement(raterListener);
        }
    }
    
    /**
     * Triggers rater selected events
     * @param rater event 
     */
    protected void triggerRaterEvent (RaterEvent raterEvent) {
        for (int gridListenersCounter = 0; gridListenersCounter < raterListeners.size();
                gridListenersCounter++) {
            ((RaterListener) raterListeners
                    .elementAt(gridListenersCounter))
                    .actionPerformed(raterEvent);
        }
    }
    
    /**
     * Determines and responds to traversal events for the 
     * rater component
     * @param direction traverse direction
     * @param viewportWidth 
     * @param viewportHeight
     * @param visRect_inOut
     * @return whether or not an allowed traverse event occurred
     */
    protected boolean traverse(int direction, int viewportWidth,
            int viewportHeight, int[] visRect_inOut) {
        
        if (direction == Canvas.LEFT) {
            if (!inTraversal) {
                inTraversal = true;
                System.out.println("Z - in traversal... " + inTraversal);
            } else {
                 if (getRaterLayout().getFocusedElement() == -1) {
                     System.out.println("ZZZZ -1");
                    getRaterLayout().setFocusedElement(lastFocussedItem);
                }  else {
                     System.out.println("ZZZZ -2");
                     if (getRaterLayout().getFocusedElement() - 1 >= 0) {
                        getRaterLayout().setFocusedElement(
                                getRaterLayout().getFocusedElement() - 1) ;
                        lastFocussedItem = getRaterLayout().getFocusedElement() - 1 ;
                     } else {
                         inTraversal = false;
                         return false;
                     }
                 }
                 System.out.println("Z - Selected component " + getRaterLayout().getFocusedElement()) ;
                 repaint();                
            }
        } else if (direction == Canvas.RIGHT) {
            if (!inTraversal) {
                inTraversal = true;      
                System.out.println("Y - in traversal... " + inTraversal);
            } else {
                if (getRaterLayout().getFocusedElement() == -1) {
                    System.out.println("YYYY - 1 " + lastFocussedItem);
                    getRaterLayout().setFocusedElement(lastFocussedItem);
                } else {
                    System.out.println("YYYY - 2");
                    if (getRaterLayout().getFocusedElement() + 1 
                            < getRaterLayout().getNoOfComponents()) {
                        getRaterLayout().setFocusedElement(
                                getRaterLayout().getFocusedElement() + 1);
                        lastFocussedItem = getRaterLayout().getFocusedElement() + 1 ;
                    } else {
                        inTraversal = false;
                        return false;
                    }
                }
                 System.out.println("Y - Selected component " + getRaterLayout().getFocusedElement()) ;
                repaint();
            }
        }
        
         if (direction == Canvas.UP ||
                direction == Canvas.DOWN) {
             if (!inTraversal) {
                 inTraversal = true;
                 System.out.println("CCC - in traversal... " + inTraversal);
             }
             return false ;
         }
         
        return true;
    }
    
    /**
     * 
     * Called when a user traverses out of a rater
     */
    protected void traverseOut() {
        lastFocussedItem = 0;
        getRaterLayout().setFocusedElement(-1);
        repaint();
    }
}
