package it.qubixic.component.grid;

public class GridConstraints {
    
    private int marginTop = 0 ;
    private int marginBottom = 0 ;
    private int marginLeft = 0 ; 
    private int marginRight = 0 ;
    private int innerMarginY = 0 ;
    private int innerMarginX = 0 ;
    
    public GridConstraints() {}
    
    public GridConstraints(int marginTop, int marginBottom, int marginLeft, 
            int marginRight, int innerMarginTop, int innerMarginBottom) {
        setMarginTop(marginTop) ;
        setMarginBottom(marginBottom) ;
        setMarginLeft (marginLeft) ;
        setMarginRight(marginRight) ;
        setInnerMarginY(innerMarginTop) ;
        setInnerMarginX(innerMarginBottom) ;
    }

    public int getMarginTop() {
        return marginTop;
    }

    public void setMarginTop(int marginTop) {
        this.marginTop = marginTop;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    public void setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public int getMarginRight() {
        return marginRight;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public int getInnerMarginY() {
        return innerMarginY;
    }

    public void setInnerMarginY(int innerMarginTop) {
        this.innerMarginY = innerMarginTop;
    }

    public int getInnerMarginX() {
        return innerMarginX;
    }

    public void setInnerMarginX(int innerMarginBottom) {
        this.innerMarginX = innerMarginBottom;
    }
}