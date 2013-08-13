package it.qubixic.component.rater.layout;

import javax.microedition.lcdui.Graphics;

public interface RaterLayout {
    void render(Graphics g) ;
    int getWidth() ;
    int getHeight() ;
    void setTitle(String title) ;
    void setCount(float count) ;
    void setNoOfComponents(int noOfComponents) ;
}
