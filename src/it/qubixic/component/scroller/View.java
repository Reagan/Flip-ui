package it.qubixic.component.scroller;

import javax.microedition.lcdui.Graphics;

public interface View {
    void changeView(ViewAction viewAction) ;
    void draw(Graphics g, int topX, int topY, int width, int height) ;
}
