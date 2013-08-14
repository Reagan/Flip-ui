package it.qubixic.component.scroller;

public abstract class ViewAction {
    
    private int viewActionType ; 
    private String imageURL ;
    
    /**
     * Creates a new view action with a 
     * default action
     * @param type type of action
     */
    public ViewAction (int viewActionType) {
        setViewActionType(viewActionType);
    }
    
    /**
     * Creates a new view action to load a remote 
     * image
     * @param type type of action
     * @param imageURL remote image to be loaded
     */
    public ViewAction (int viewActionType, String imageURL) {
        if (viewActionType == ViewActionType.DISPLAY_IMAGE) {
            setViewActionType(viewActionType);
            setImageURL(imageURL);
        }
    }

    /**
     * @return the viewActionType
     */
    public int getViewActionType() {
        return viewActionType;
    }

    /**
     * @param viewActionType the viewActionType to set
     */
    public void setViewActionType(int viewActionType) {
        this.viewActionType = viewActionType;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
