package it.qubixic.showcase;

import javax.microedition.lcdui.Form;
import javax.microedition.midlet.*;

public class ShowCase extends MIDlet {
    
   
    private Form grid ;

    public void startApp() {
        loadScreen(Page.SPLASH);        
    }
    
     public void loadScreen(int screen) {                     
        switch(screen) {
            default:    
            case Page.SPLASH :
                break ;
        }
     }
    
    public void pauseApp() {
    
    }
    
    public void destroyApp(boolean unconditional) {
    
    }
}