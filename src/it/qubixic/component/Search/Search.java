package it.qubixic.component.Search;

import java.util.Vector;
import javax.microedition.lcdui.TextField;

/**
 * This component allows a user to enter a search term. Changes 
 * in the content of this search term can be obtained by implementing 
 * a state change listener in the containing form
 * @author reagan mbitiru <reaganmbitiru@gmail.com>
 */
public class Search extends TextField {
    
    private String searchFieldLabel = "Search" ;
    private String searchFieldText = "" ;
    private int searchFieldMaxSize = 256 ;
    private int searchFieldConstraints = TextField.ANY ;
    
    /**
     * Creates a default instance of a search component
     */
    public Search() {
        super("Search", "", 256, TextField.ANY);
    }     
    
    /**
     * Creates an instance of the search field text
     * with the search field label and text
     * @param searchFieldLabel
     * @param searchFieldText 
     */
    public Search(String searchFieldLabel, String searchFieldText) {
       super (searchFieldLabel, searchFieldText, 256, TextField.ANY);
    }

    /**
     * Creates an instance of a search field label and the type of 
     * text that the search field can contain
     * @param searchFieldLabel
     * @param searchFieldConstraints 
     */
    public Search(String searchFieldLabel, int searchFieldConstraints) {
        super(searchFieldLabel, "", 256, searchFieldConstraints) ;
    }
    
    /**
     * This method is triggered to begin the actual search
     * and should be overidden with a correct implementation 
     * of the search algorithm. If not overidden, this method 
     * otherwise returns an empty vector
     */
    protected Vector search() {
        return new Vector() ;
    }
    
    /**
     * @return the searchFieldLabel
     */
    public String getSearchFieldLabel() {
        return searchFieldLabel;
    }

    /**
     * @param searchFieldLabel the searchFieldLabel to set
     */
    public void setSearchFieldLabel(String searchFieldLabel) {
        this.searchFieldLabel = searchFieldLabel;
    }

    /**
     * @return the searchFieldText
     */
    public String getSearchFieldText() {
        return searchFieldText;
    }

    /**
     * @param searchFieldText the searchFieldText to set
     */
    public void setSearchFieldText(String searchFieldText) {
        this.searchFieldText = searchFieldText;
    }

    /**
     * @return the searchFieldMaxSize
     */
    public int getSearchFieldMaxSize() {
        return searchFieldMaxSize;
    }

    /**
     * @param searchFieldMaxSize the searchFieldMaxSize to set
     */
    public void setSearchFieldMaxSize(int searchFieldMaxSize) {
        this.searchFieldMaxSize = searchFieldMaxSize;
    }

    /**
     * @return the searchFieldConstraints
     */
    public int getSearchFieldConstraints() {
        return searchFieldConstraints;
    }

    /**
     * @param searchFieldConstraints the searchFieldConstraints to set
     */
    public void setSearchFieldConstraints(int searchFieldConstraints) {
        this.searchFieldConstraints = searchFieldConstraints;
    }        
}
