package it.qubixic.showcase;

import it.qubixic.component.rater.Rater;
import javax.microedition.midlet.*;

public class Raters extends BaseForm {
    
    private final String STAR_RATER_TITLE = "Star Rater Example" ;
    
    public Raters (MIDlet midlet) {
        super("Raters", midlet) ;
        appendStarRater(createStarRater()) ;
    }
    
    public void appendStarRater (Rater starRater) {
        append(starRater) ;
    }
    
    public Rater createStarRater() {
        final int STAR_RATER_INITIAL_COUNT = 2 ;
        Rater starRater = new Rater(STAR_RATER_TITLE, STAR_RATER_INITIAL_COUNT) ;
        return starRater ;
    }
}
