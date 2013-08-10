package it.qubixic.component.grid.event;

public class GridEvent {
   
    private Object target ;
    private int type ;
    private int x ;
    private int y ;
    
    public GridEvent(Object target, int type, int x, int y) {
        this.target = target ;
        this.type = type ;
        this.x = x ;
        this.y = y ; 
    }
    
    public int getType() {
        return type ;
    }
    
    public int getX () {
        return x ;
    }
    
    public int getY() {
        return y ;
    }
    
    public Object getTarget() {
        return target ;
    }
}
