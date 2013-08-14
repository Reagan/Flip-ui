package it.qubixic.showcase;

import it.qubixic.component.rater.Rater;
import it.qubixic.component.rater.event.RaterEvent;
import it.qubixic.component.rater.event.RaterListener;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.*;

public class RatersForm extends BaseForm {
    
    private final String TITLE_LABEL = "Raters" ;
    private final String TITLE_TEXT = "A set of supported rater component kinds" ;    
    private final String SUMMARY_LABEL = "Documentation";
    private final String SUMMARY_TEXT = "Documentation on the use of this library is available at"; 
    
    private final String RATER_TITLE = "Rater Example" ;
    
    public RatersForm (MIDlet midlet) {
        super("Raters", midlet) ;
        appendTitle(TITLE_LABEL, TITLE_TEXT) ;
        appendStarRater(createStarRater()) ;
        appendStarRater(createStarRaterWithCount());
        appendStarRater(createStarRaterWithTitleAndInitialCounter()) ;
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
    
    public void appendStarRater (Rater starRater) {
        append(starRater) ;
    }
    
    public Rater createStarRater() {        
        Rater starRater = new Rater() ;
        return starRater ;
    }
    
    public Rater createStarRaterWithCount() {
        final int NO_OF_COMPONENTS = 2 ;
        final float COUNT = 42 ;
        Rater starRater = new Rater(NO_OF_COMPONENTS, COUNT) ;
        starRater.addRaterListener(new RaterListener() {
            public void actionPerformed(RaterEvent e) {
                System.out.println("Component " + e.getSelectedValue() + " selected");
            }
        });
        return starRater ;
    }
    
    public Rater createStarRaterWithTitleAndInitialCounter() {
        final int NO_OF_COMPONENTS = 4 ;
        final int COUNT = 60 ;
        Rater starRater = new Rater(RATER_TITLE, COUNT,
                NO_OF_COMPONENTS) ;
        starRater.addRaterListener(new RaterListener() {
            public void actionPerformed(RaterEvent e) {
                System.out.println("Component " + e.getSelectedValue() + " selected");
            }
        });
        return starRater ;
    }
}
