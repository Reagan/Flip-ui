package it.qubixic.component.grid.layout ;

import javax.microedition.lcdui.Graphics;

public interface Layout {
    void drawElements(Graphics g) ;
    int getItemsPerRow(int thumbnailWidth) ; 
    int getRows(int itemsPerRow); 
}
