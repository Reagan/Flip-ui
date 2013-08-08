package it.qubixic.showcase;

import it.qubixic.component.splashscreen.SplashScreen;
import it.qubixic.showcase.utils.ImageUtils;
import it.qubixic.utils.Point;
import java.util.Vector;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class FlipSplashScreen extends SplashScreen {
    
    private final int BG_COLOR = 0x014B89 ;
    private Vector loadingMessages = new Vector() ;
    
    private final Image breakDanceFtUpImg = ImageUtils.loadJarImage("/breakDanceFtUp.png");
    private final Point breakDanceFtUpImgPosition = new Point(0.1f, 0.3f) ;
    
    private final Image flipUITextImg = ImageUtils.loadJarImage("/flipUIText.png") ;
    private final Point flipUITextImgPosition = new Point(0.4f, 0.8f); 
    
    private int loadingCircleColor = 0xff9900 ;
    private int loadingCircleStroke = Graphics.SOLID ;
    private Point loadingCirclePosition = new Point(0.5f, 0.5f) ;
    private int loadingCircleWidth = 66 ;
    private int loadingCircleHeight = 66 ;
    
    private final int loadingMessagesColor = 0xffffff ;
    private final Point loadingMessagePosition = new Point(0.5f, 0.5f) ;
    private final Font loadingMessagesFont = Font.getFont(Font.FACE_SYSTEM, 
            Font.STYLE_PLAIN, Font.SIZE_SMALL) ;
   
    public FlipSplashScreen() {
        initializeLoadingMessages() ;        
   }
    
    private void initializeLoadingMessages() {
        loadingMessages.addElement("Initializing");
        loadingMessages.addElement("Starting");
    }
    
    protected void drawBackground(Graphics g) {
        g.setColor(BG_COLOR);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.drawImage(breakDanceFtUpImg, (int) breakDanceFtUpImgPosition.getX() * getWidth(), 
                (int) breakDanceFtUpImgPosition.getY() * getHeight(), 
                Graphics.TOP | Graphics.LEFT);
       
        g.drawImage(flipUITextImg, (int) flipUITextImgPosition.getX() * getWidth(), 
                (int) flipUITextImgPosition.getY() * getHeight(), 
                Graphics.TOP | Graphics.LEFT);               
    }
    
    protected void drawLoadingSequence (Graphics g, int counter) {
        g.setColor(loadingCircleColor);
        g.setStrokeStyle(loadingCircleStroke);
        g.drawArc((int) loadingCirclePosition.getX(), 
                (int) loadingCirclePosition.getY(), 
                loadingCircleWidth, loadingCircleHeight, 
                0, counter);
    }
    
    protected void drawLoadingMessage (Graphics g, String message) {        
        g.setColor(loadingMessagesColor);
        g.setFont(loadingMessagesFont);
        g.drawString(message, (int) loadingMessagePosition.getX() * getWidth(), 
                (int) loadingMessagePosition.getY() * getHeight(), 
                Graphics.TOP | Graphics.LEFT);
    }
}
