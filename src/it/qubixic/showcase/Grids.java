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
    private final int HORIZONTAL_GRID_THUMBNAIL_WIDTH = 100 ;
    private final int HORIZONTAL_GRID_THUMBNAIL_HEIGHT = 100 ;
    private final int VERTICAL_GRID_THUMBNAIL_HEIGHT = 100 ;
    
    public Grids(MIDlet midlet) {
        super ("Grid", midlet) ;
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
        
        Grid horizontalGridSameComponentDimensions = new Grid(GRID_HORI_SAME_DIMENSIONS_COMPONENTS, 
                ListType.HORIZONTAL, ComponentLayoutType.SAME_DIMENSIONS) ;
        
        horizontalGridSameComponentDimensions.setWidth(getWidth());        
        horizontalGridSameComponentDimensions
                .append(createThumbnails(HORIZONTAL_GRID_THUMBNAIL_WIDTH, 
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
         Grid verticalGridSameComponentDimensions 
                 = new Grid(GRID_VERTICAL_SAME_DIMENSIONS_COMPONENTS, 
                ListType.VERTICAL, ComponentLayoutType.SAME_DIMENSIONS) ;
        
        verticalGridSameComponentDimensions.setWidth(getWidth());        
        verticalGridSameComponentDimensions
                .append(createThumbnails(getWidth(), 
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
    
    private Vector createThumbnails(int width, int height) {
        Vector thumbnails = new Vector() ;
        final int noOfThumbnails = 6 ;
        ThumbnailCaption thumbnailCaption ;
        Image thumbnailImage ;
            
        for (int thumbnailsCounter = 1 ;thumbnailsCounter < noOfThumbnails ;
                thumbnailsCounter++) {
            thumbnailCaption = new ThumbnailCaption("Thumbnail " + thumbnailsCounter,
                    "Description here...") ;
            thumbnailImage = ImageUtils.loadJarImage("/" 
                    + thumbnailsCounter 
                    + "-100x100.png");
            Thumbnail thumbnail = new Thumbnail(thumbnailCaption, thumbnailImage) ;
            thumbnail.setWidth(width);
            thumbnail.setHeight(height);
            thumbnails.addElement(thumbnail);
        }
        return thumbnails ;
    }    
}