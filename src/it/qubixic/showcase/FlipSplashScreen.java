package it.qubixic.showcase;

import it.qubixic.component.splashscreen.SplashScreen;
import it.qubixic.showcase.utils.ImageUtils;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import java.util.Timer ;
import java.util.TimerTask ;

public class FlipSplashScreen extends SplashScreen {
    
    private final int BG_COLOR = 0x014B89 ;
    private Vector loadingMessages = new Vector() ;
    
    private final Image breakDanceFtUpImg = ImageUtils.loadJarImage("/breakDanceFtUp.png");
    private final Point breakDanceFtUpImgPosition = new Point(0.1f, 0.1f) ;
    
    private final Image flipUITextImg = ImageUtils.loadJarImage("/flipUIText.png") ;
    private final Point flipUITextImgPosition = new Point(0.13f, 0.7f); 
    
    private int loadingCircleColor = 0xff9900 ;
    private int loadingCircleStroke = Graphics.SOLID ;
    private Point loadingCirclePosition = new Point(0.34f, 0.41f) ;
    private int loadingCircleWidth = 66 ;
    private int loadingCircleHeight = 66 ;
    
    private final int loadingMessagesColor = 0xffffff ;
    private final Point loadingMessagePosition = new Point(0.4f, 0.5f) ;
    private final Font loadingMessagesFont = Font.getFont(Font.FACE_SYSTEM, 
            Font.STYLE_PLAIN, Font.SIZE_SMALL) ;
    
    private boolean isLoading = false ;
   
    public FlipSplashScreen() {
        initializeLoadingMessages() ;   
        startLoadingSequence() ;
        setLoading(true);
   }
    
    private void initializeLoadingMessages() {
        loadingMessages.addElement("Initializing");
        loadingMessages.addElement("Loading");
        loadingMessages.addElement("Finalizing"); 
        loadingMessages.addElement("Starting");
    }
    
    protected void drawBackground(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(breakDanceFtUpImg, (int) (breakDanceFtUpImgPosition.getX() * getWidth()), 
                (int) (breakDanceFtUpImgPosition.getY() * getHeight()), 
                Graphics.TOP | Graphics.LEFT);       
        g.drawImage(flipUITextImg, (int) (flipUITextImgPosition.getX() * getWidth()), 
                (int) (flipUITextImgPosition.getY() * getHeight()), 
                Graphics.TOP | Graphics.LEFT);               
    }
    
    protected void drawLoadingSequence (Graphics g, int counter) {
        g.setColor(loadingCircleColor);
        g.setStrokeStyle(loadingCircleStroke);        
        g.drawArc((int) (loadingCirclePosition.getX() * getWidth()), 
                (int) (loadingCirclePosition.getY() * getHeight()), 
                loadingCircleWidth, loadingCircleHeight, 
                0, counter);
    }
    
    protected void drawLoadingMessage (Graphics g, String message) {        
        g.setColor(loadingMessagesColor);
        g.setFont(loadingMessagesFont);
        g.drawString(message, (int) (loadingMessagePosition.getX() * getWidth()), 
                (int) (loadingMessagePosition.getY() * getHeight()), 
                Graphics.TOP | Graphics.LEFT);
    }
    
    protected void startLoadingSequence() {      
       int initialDelay = 10 ;
       int subsequentDelay = 10 ;
       
       new Timer().schedule(new TimerTask() {           
           int loadingCounter = 0 ;
           int messagesCounter = 0 ;
           String currentMessage = "" ;
           final int MAX_LOADING_COUNTER = 360 ;
           
           public void run() {
               if(loadingCounter < MAX_LOADING_COUNTER){
                   if (loadingCounter % 90 == 0) {
                       currentMessage = (String) (loadingMessages.elementAt(messagesCounter));                        
                        messagesCounter++ ;
                   }                   
                   update(currentMessage, loadingCounter);                                      
                   loadingCounter++ ;
                   
               } else {
                   this.cancel();
                   isLoading = false;
               }
           }
       }, initialDelay, subsequentDelay);
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
    }
}
