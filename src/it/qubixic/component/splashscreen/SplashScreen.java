 package it.qubixic.component.splashscreen;

import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

 /**
  * This class is used to create a splash screen. It accepts a 
  * set of loading messages or allows custom drawing during the 
  * loading sequence of the midlet
  * @author reagan mbitiru <reaganmbitiru@gmail.com>
  */
public class SplashScreen extends Canvas implements Runnable {

    private boolean drawFullScreen = true ;
    private boolean drawStatusBar = false ;
    private long refreshPeriod = 50L ;
    private String loadingMessage = "Loading" ;
    private int currentState = 0 ; 
    private int counter = 0 ;
    
    private final int DEFAULT_BACKGROUND_COLOR = 0xffffff ;
    private final String DEFAULT_LOADING_MESSAGE = "Starting..." ;
    private final Point DEFAULT_LOADING_MESSAGE_POSITION = new Point(0.1f, 0.2f) ;
    private final Font DEFAULT_LOADING_MESSAGE_FONT = Font.getFont(Font.FACE_SYSTEM, 
            Font.STYLE_BOLD, Font.SIZE_MEDIUM) ;
    private final int DEFAULT_LOADING_MESSAGE_COLOR = 0xcccccc ;
    
    /**
     * creates a default instance of a splash screen
     */
    public SplashScreen () {}
    
    /**
     * Creates a SplashScreen instance. Can be extended to create a 
     * custom background and loading sequence
     * @param drawFullScreen indicates whether the splash screen should cover 
     * the whole screen or not
     * @param drawStatusBar indicates whether the splash screen should hide or 
     * display the phone's status bar
     */
    public SplashScreen (boolean drawFullScreen, boolean drawStatusBar) {
        setFullScreen (drawFullScreen) ;
        setStatusBarVisible (drawStatusBar) ;
    }
    
    /**
     * gets the period after which the screen is repainted
     * @return refresh period
     */
    public long getRefreshPeriod () {
        return refreshPeriod ;
    }
    
    /**
     * sets the period after which the screen is repainted
     * @param refreshPeriod 
     */
    public void setRefreshPeriod (long refreshPeriod) {
        this.refreshPeriod = refreshPeriod ;
    }
    
    /**
     * checks whether the splash screen should cover the 
     * entire screen of the device
     * @return whether the splash screen should cover the
     * whole screen or not
     */
    public boolean drawFullScreen () {
        return drawFullScreen ;
    }
    
    /**
     * sets whether or not the splash screen should cover the 
     * entire screen of the device. If this is false, the device
     * loads the splash screen using the screen real estate used to 
     * load other applications
     * @param state whether the splash screen should cover the whole 
     * screen or not
     */
    public void setFullScreen(boolean state) {
        this.drawFullScreen = state ;
    }
    
    /**
     * checks whether or not the status bar should be displayed
     * as the splash screen is displayed
     * @return whether the status bar is displayed
     */
    public boolean statusBarVisible () {
        return drawStatusBar ;
    }
    
    /**
     * sets whether or not the status bar should be displayed
     * @param state whether or not the status bar is to be displayed
     */
    public void setStatusBarVisible (boolean state) {
        this.drawStatusBar = state ;
    }
    
    /**
     * draws the splash screen. drawBackground() and 
     * drawLoadingSequence() should be overidden as they are 
     * implemented in this paint()
     * @param g 
     */
    protected void paint(Graphics g) {      
        drawBackground(g) ;
        drawLoadingSequence(g, counter) ;
        drawLoadingMessage(g, loadingMessage) ;
    }    
    
    /**
     * Draws the background of the splash screen. It is 
     * anticipated that the background does not change
     * during the splash screen loading sequence. This method 
     * should be overidden to draw the desired background
     * @param g Graphics object
     */
    protected void drawBackground(Graphics g) {
        g.setColor(DEFAULT_BACKGROUND_COLOR);
        g.setFont(DEFAULT_LOADING_MESSAGE_FONT);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    /**
     * This method is used to draw the content that 
     * changes during the loading sequence. It should be overidden
     * to provide the desired loading sequence and accepts a set 
     * of loading messages or variables that capture a particular
     * set of the loading sequence
     * @param g Graphics object
     * @param counter This is a custom counter used to 
     * draw the loading sequence for the splash screen
     */        
    protected void drawLoadingSequence(Graphics g, int counter) {
        
    }

    /**
     * This method is used to draw a loading message onto
     * the splash screen and should be overridden to draw the 
     * custom loading message
     * @param g Graphics object
     * @param message  the message to be drawn
     */
    protected void drawLoadingMessage (Graphics g, String message)  {
        g.setColor(DEFAULT_LOADING_MESSAGE_COLOR);
        g.drawString(DEFAULT_LOADING_MESSAGE, 
                (int) DEFAULT_LOADING_MESSAGE_POSITION.getX(), 
                (int) DEFAULT_LOADING_MESSAGE_POSITION.getY(), 
                Graphics.TOP | Graphics.LEFT);
    }
    
    /**
     * This method is called to increment the current 
     * state of the loading sequence. The changes should be captured
     * by drawLoadingSequence() and a revised loading sequence implemented 
     * to reflect a change in the loading sequence      
     */
    protected void update(String loadingMessage, int counter) {
        this.counter = counter ;
        this.loadingMessage = loadingMessage ; 
        currentState++ ;
    }
    
    /**
     * This method continously updates the splash screen. 
     */
    public void run() {
        while (true) {
            repaint();           
            synchronized (this) {
                try {
                    wait (refreshPeriod) ;
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
