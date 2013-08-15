package it.qubixic.showcase;

import it.qubixic.showcase.form.GridsForm;
import it.qubixic.showcase.form.FlipSplashScreen;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.*;

public class ShowCase extends MIDlet {    
   
    private FlipSplashScreen flipSplashScreen ;
    private GridsForm grids ;

    public ShowCase() {        
        flipSplashScreen = new FlipSplashScreen();
        grids = new GridsForm(this) ;
    }
    
    public void startApp() {
        loadSplashScreen(Page.SPLASH);        
    }
    
     public void loadSplashScreen(int screen) {       
        Display.getDisplay(this).setCurrent(flipSplashScreen);
        final MIDlet midlet = this ;
        new Thread(new Runnable() {
            public void run() {
                while (flipSplashScreen.isLoading()) {
                }
                Display.getDisplay(midlet).setCurrent(grids) ;
            }
        }).start();
     }
    
    public void pauseApp() {
    
    }
    
    public void destroyApp(boolean unconditional) {
    
    }
}
