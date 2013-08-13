package it.qubixic.showcase;

import it.qubixic.component.grid.Grid;
import it.qubixic.component.grid.GridConstraints;
import it.qubixic.component.grid.ListType;
import it.qubixic.component.grid.event.GridEvent;
import it.qubixic.component.grid.event.GridListener;
import it.qubixic.component.grid.layout.ComponentLayoutType;
import it.qubixic.component.grid.thumbnail.Thumbnail;
import it.qubixic.component.grid.thumbnail.ThumbnailCaption;
import it.qubixic.showcase.utils.ImageUtils;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.StringItem;
import java.util.Vector;
import javax.microedition.midlet.*;

public class Grids extends BaseForm {

    private final String TITLE_LABEL = "Grids" ;
    private final String TITLE_TEXT = "A set of supported grid kinds" ;
    private final String GRID_HORI_SAME_DIMENSIONS_COMPONENTS
            = "Horizontal Grid, Same component dimensions" ;
    private final String GRID_VERTICAL_SAME_DIMENSIONS_COMPONENTS
            = "Vertical Grid, Same component dimensions" ;
    private final String SUMMARY_LABEL = "Documentation";
    private final String SUMMARY_TEXT = "Documentation on the use of this library is available at"; 
    private final String TESTING_MESSAGE = "Loading..." ;
    
    private final int HORIZONTAL_GRID_THUMBNAIL_WIDTH = 100 ;
    private final int HORIZONTAL_GRID_THUMBNAIL_HEIGHT = 100 ;
    private final int VERTICAL_GRID_THUMBNAIL_HEIGHT = 100 ;
    
    private final int HORIZONTAL_THUMBNAILS = 1 ;
    private final int VERTICAL_THUMBNAILS = 2 ;
    
    private final Grid horizontalGridSameComponentDimensions = new Grid(GRID_HORI_SAME_DIMENSIONS_COMPONENTS, 
                ListType.HORIZONTAL, ComponentLayoutType.SAME_DIMENSIONS) ;
    private  Grid verticalGridSameComponentDimensions 
                 = new Grid(GRID_VERTICAL_SAME_DIMENSIONS_COMPONENTS, 
                ListType.VERTICAL, ComponentLayoutType.SAME_DIMENSIONS) ;
    
    public Grids(MIDlet midlet) {
        super ("", midlet) ;
        appendTitle(TITLE_LABEL, TITLE_TEXT) ;
        appendGrid(createHorizontalGridSameComponentDimensions()) ;
        appendGrid(createVerticalGridSameComponentDimensions()) ;
        appendSummary(SUMMARY_LABEL, SUMMARY_TEXT);
    }
    
    public void appendTitle(String title_label, String title_text) {
        appendStringItem(title_label, title_text);
    }
    
    public void appendSummary(String title_label, String title_text) {
        appendStringItem(title_label, title_text);
    }
     
    public void appendStringItem(String title, String text) {
        append(new StringItem(title, text));
    }
    
    public void appendGrid(Grid grid) {
        append(grid);
    }
    
    private Grid createHorizontalGridSameComponentDimensions() {
        
        horizontalGridSameComponentDimensions.setOverlayMessage(TESTING_MESSAGE);
        horizontalGridSameComponentDimensions.displayOverlayMessage(true);
        
        horizontalGridSameComponentDimensions.setWidth(getWidth());        
        horizontalGridSameComponentDimensions
                .append(createThumbnails(HORIZONTAL_THUMBNAILS,
                HORIZONTAL_GRID_THUMBNAIL_WIDTH, 
                HORIZONTAL_GRID_THUMBNAIL_HEIGHT));
        horizontalGridSameComponentDimensions.setGridConstraints(
                new GridConstraints(0, 0, 0, 0, 5, 5)); 
        
        horizontalGridSameComponentDimensions.addGridListener(new GridListener() {
            public void actionPerformed(GridEvent e) {
                System.out.println(e.getTarget() + " selected");
            }
        });
        return horizontalGridSameComponentDimensions ;  
    }    
    
    private Grid createVerticalGridSameComponentDimensions() {
               
        verticalGridSameComponentDimensions.setWidth(getWidth());        
        verticalGridSameComponentDimensions
                .append(createThumbnails(VERTICAL_THUMBNAILS,
                getWidth(), 
                VERTICAL_GRID_THUMBNAIL_HEIGHT));
        verticalGridSameComponentDimensions.setGridConstraints(
                new GridConstraints(0, 0, 0, 0, 5, 5)); 
        
        verticalGridSameComponentDimensions.addGridListener(new GridListener() {
            public void actionPerformed(GridEvent e) {
                System.out.println(e.getTarget() + " selected");
            }
        });
        return verticalGridSameComponentDimensions ;   
        
    }
    
    private Vector createThumbnails(int type, int width, int height) {
        Vector thumbnails = new Vector() ;
        final int noOfThumbnails = 6 ;        
        Image thumbnailImage ;
        Thumbnail thumbnail = null ;
        
        for (int thumbnailsCounter = 1 ;thumbnailsCounter < noOfThumbnails ;
                thumbnailsCounter++) {
            
            thumbnailImage = ImageUtils.loadJarImage("/" 
                    + thumbnailsCounter 
                    + "-100x100.png");
            if (type == HORIZONTAL_THUMBNAILS) {
                ThumbnailCaption thumbnailCaption 
                        = new ThumbnailCaption("Thumbnail " + thumbnailsCounter,
                    "Description here...") ;
                thumbnail = new HorizontalThumbnail(thumbnailCaption, thumbnailImage) ;
            } else if (type == VERTICAL_THUMBNAILS) {
                VerticalThumbnailCaption vCaption = new VerticalThumbnailCaption("Part 1", 
                        "Part 2", "Part 3") ;
                thumbnail = new VerticalThumbnail(vCaption, thumbnailImage) ;
            }
            
            thumbnail.setWidth(width);
            thumbnail.setHeight(height);
            thumbnails.addElement(thumbnail);
        }
        return thumbnails ;
    }    
    
    public void loadStatusMessage() {
        final int FIVE_SECS = 5000 ;
         new Thread(new Runnable() {
            public void run() {
                try {
                    synchronized(horizontalGridSameComponentDimensions) {
                        horizontalGridSameComponentDimensions.wait(FIVE_SECS);
                        horizontalGridSameComponentDimensions.displayOverlayMessage(false);
                    }                                      
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}