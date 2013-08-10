package it.qubixic.component.grid.thumbnail;

import javax.microedition.lcdui.Graphics;

public interface CaptionComponent {
    void render(Graphics g, int topX, int topY, int parentWidth, int parentHeight) ;
    int getHeight() ;
}
