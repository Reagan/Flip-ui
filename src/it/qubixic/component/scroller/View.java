package it.qubixic.component.scroller;

import javax.microedition.lcdui.Graphics;

public interface View {
    void changeView(ViewAction viewAction) ;
    void draw(Graphics g, int width, int height) ;
}
