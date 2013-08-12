package it.qubixic.showcase;

import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.component.grid.thumbnail.ThumbnailCaption;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class VerticalThumbnail extends Thumbnail {

    private final int focussedColor = 0xff0000 ;
    
    public VerticalThumbnail(VerticalThumbnailCaption caption, Image image) {
        setImage(image);
        setCaption(caption);
    }

    public void render(Graphics g, Image image, ThumbnailCaption caption,
            int topX, int topY) {

        if (image != null) {
            g.drawImage(image, topX, topY,
                    Graphics.LEFT | Graphics.TOP);
        }

        if (null != caption) {
            caption.render(g, topX, topY, getWidth(), getHeight());
        }

        if (focused) {
            g.setColor(focussedColor);
            g.drawRect(topX, topY, getWidth() - 1, getHeight() - 1);
        }
    }
}
