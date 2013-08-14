package it.qubixic.component.scroller;

public class ViewAction {
    
    private int viewActionType ; 
    private final String VIEW_ACTION_ERROR = "ViewAction only "
            + "supports ViewActionType.PREVIOUS (1) and "
            + "ViewActionType.NEXT(2)" ;
    
    /**
     * Creates a new view action with a 
     * default action
     * @param type type of action
     */
    public ViewAction (int viewActionType) {
        if (viewActionType != ViewActionType.PREVIOUS &&
                viewActionType != ViewActionType.NEXT) {
            throw new IllegalArgumentException(VIEW_ACTION_ERROR);
        }
        setViewActionType(viewActionType);
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
}
