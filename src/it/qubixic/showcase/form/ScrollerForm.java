package it.qubixic.showcase.form;

import it.qubixic.component.scroller.Scroller;
import it.qubixic.component.scroller.ViewAction;
import it.qubixic.component.scroller.ViewActionType;
import it.qubixic.component.scroller.Viewer;
import it.qubixic.utils.Point;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Command ;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;

public class ScrollerForm extends BaseForm {
    
    private MIDlet midlet ;
    private Scroller scroller ;
    private DisplayViewer viewer ;
    private final String SCROLLER_1_TITLE = "Scroller Title" ;
    private final int SCROLLER_HEIGHT = 140 ;
    private final Command PREVIOUS_COMMAND = new Command("Previous", Command.SCREEN, 1) ;
    private final Command NEXT_COMMAND = new Command("Next", Command.SCREEN, 1) ;
    
    public ScrollerForm(MIDlet midlet) {
        super("Scroller", midlet) ;
        setMidlet(midlet);
        appendScroller(createScroller()) ;        
    }

    private void appendScroller (Scroller scroller) {
        append(scroller);
    }
    
    private Scroller createScroller() {
        viewer = new DisplayViewer() ;
        scroller = new Scroller(SCROLLER_1_TITLE, viewer, getWidth(), 
                SCROLLER_HEIGHT) ;
        scroller.addCommand(PREVIOUS_COMMAND);
        scroller.addCommand(NEXT_COMMAND);
        scroller.setDefaultCommand(NEXT_COMMAND);
        scroller.setItemCommandListener(new ItemCommandListener() {
            public void commandAction(Command c, Item item) {
                if (c == PREVIOUS_COMMAND) {
                    viewer.changeView(new ViewAction(ViewActionType.PREVIOUS));
                } else if (c == NEXT_COMMAND) {
                    viewer.changeView(new ViewAction(ViewActionType.NEXT));
                }
            }
        });
        return scroller ;
    }
    
    public MIDlet getMidlet() {
        return midlet;
    }

    public void setMidlet(MIDlet midlet) {
        this.midlet = midlet;
    }
    
    class DisplayViewer extends Viewer {

        private final int VIEWER_HEIGHT = 120 ;
        private int currentlyDisplayedNo = 1 ;
        private final int FONT_COLOR = 0xffffff ;
        private final int BG_COLOR = 0xcccccc ;
        private final Font UFONT = Font.getFont(Font.FACE_PROPORTIONAL,
                Font.STYLE_PLAIN, Font.SIZE_LARGE);
        private final Point STRING_POSITION =
                new Point(0.4f, 0.5f) ;
        private Graphics g ;
        private int w ; 
        private int h ; 
        private int x ;
        private int y ;
        
        public DisplayViewer() {
        }

        protected int getMinContentWidth() {
            return getWidth();
        }

        protected int getMinContentHeight() {
            return this.VIEWER_HEIGHT ;
        }

        protected int getPrefContentWidth(int height) {
            return getWidth() ;
        }

        protected int getPrefContentHeight(int width) {
            return this.VIEWER_HEIGHT ;
        }

        public void draw(Graphics g, int x, int y, int w, int h) {
            this.g = g ;
            this.w = w ; 
            this.h = h  ;
            this.x = x ;
            this.y = y ; 
            
            g.setColor(BG_COLOR);
            g.fillRect(x, y, getWidth(), getHeight());
            g.setColor(FONT_COLOR);
            g.setFont(UFONT);
            
            g.drawString("PANEL " + currentlyDisplayedNo, 
                    (int) (STRING_POSITION.getX() * getWidth()), 
                    (int) (STRING_POSITION.getY() * VIEWER_HEIGHT), 
                    Graphics.TOP | Graphics.LEFT);
        }

        public void changeView(ViewAction viewAction) {
            if (viewAction.getViewActionType() 
                    == ViewActionType.NEXT) {
                currentlyDisplayedNo++ ;
            } else if (viewAction.getViewActionType() 
                    == ViewActionType.PREVIOUS) {
                currentlyDisplayedNo-- ;
            }
            draw(g, x, y, w, h) ;
        }        
    }
}
