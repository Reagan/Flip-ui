package it.qubixic.showcase;

import it.qubixic.component.rater.Rater;
import javax.microedition.midlet.*;

public class Raters extends BaseForm {
    
    private final String RATER_TITLE = "Rater Example" ;
    
    public Raters (MIDlet midlet) {
        super("Raters", midlet) ;
        appendStarRater(createStarRater()) ;
        appendStarRater(createStarRaterWithCount());
        appendStarRater(createStarRaterWithTitleAndInitialCounter()) ;
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
        return starRater ;
    }
    
    public Rater createStarRaterWithTitleAndInitialCounter() {
        final int NO_OF_COMPONENTS = 4 ;
        final int COUNT = 78 ;
        Rater starRater = new Rater(RATER_TITLE, COUNT,
                NO_OF_COMPONENTS) ;
        return starRater ;
    }
}
