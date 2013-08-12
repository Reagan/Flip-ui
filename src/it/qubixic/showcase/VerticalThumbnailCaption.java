package it.qubixic.showcase;

import it.qubixic.component.grid.thumbnail.ThumbnailCaption;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Font;

public class VerticalThumbnailCaption extends ThumbnailCaption {
    
    private String part1 = "";
    private String part2 = "";
    private String part3 = "";
    
    private final Font captionFont = Font.getFont(Font.FACE_SYSTEM,
            Font.STYLE_PLAIN, Font.SIZE_SMALL) ;
    private final int captionFontColor = 0x000000;

    public VerticalThumbnailCaption(String part1, String part2,
            String part3) {
        setPart1(part1);
        setPart2(part2);
        setPart3(part3);
    }

    public String getPart1() {
        return part1;
    }

    public void setPart1(String part1) {
        this.part1 = part1;
    }

    public String getPart2() {
        return part2;
    }

    public void setPart2(String part2) {
        this.part2 = part2;
    }

    public String getPart3() {
        return part3;
    }

    public void setPart3(String part3) {
        this.part3 = part3;
    }
    
    public void render(Graphics g, int topX, int topY, int parentWidth, int parentHeight) {
        System.out.println("render called");
        drawString(g, getPart1(), topX + 120, topY + 5) ;
        drawString(g, getPart2(), topX + 120, topY + g.getFont().getHeight() + 2 * 5) ;
        drawString(g, getPart3(), topX + 120, topY + 2 * g.getFont().getHeight()+ 3 * 5) ;
    }    
    
    public void drawString(Graphics g, String string, int x, int y) {
        g.setColor(captionFontColor);
        g.drawString(string, x, y, Graphics.TOP | Graphics.LEFT);
    }
}
