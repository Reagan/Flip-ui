package it.qubixic.component.rater.event;

import it.qubixic.component.rater.Rater;

public class RaterEvent {
    
    private Rater rater ;
    private int selectedValue ;
    
    /**
     * Creates a rater selected event
     * @param rater
     * @param selectedValue 
     */
    public RaterEvent (Rater rater, int selectedValue) {
        setRater(rater);
        setSelectedValue(selectedValue);
    }

    /**
     * @return the rater
     */
    public Rater getRater() {
        return rater;
    }

    /**
     * @param rater the rater to set
     */
    public void setRater(Rater rater) {
        this.rater = rater;
    }

    /**
     * @return the selectedValue
     */
    public int getSelectedValue() {
        return selectedValue;
    }

    /**
     * @param selectedValue the selectedValue to set
     */
    public void setSelectedValue(int selectedValue) {
        this.selectedValue = selectedValue;
    }
}
