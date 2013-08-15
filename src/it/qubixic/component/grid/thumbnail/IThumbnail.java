package it.qubixic.component.grid.thumbnail;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public interface IThumbnail {
    String getImageURL();
    ThumbnailCaption getCaption() ;
    boolean isSelected();
    void setSelected(boolean selected);
    int getHeight();
    int getWidth() ;
    void render(Graphics g, String imageURL, ThumbnailCaption caption, int topX, int topY) ;
}
