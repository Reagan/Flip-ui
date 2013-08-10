package it.qubixic.showcase;

import java.util.Vector;
import javax.microedition.lcdui.Command ;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display; 
import javax.microedition.midlet.*;

public class CommandsService {
    
    private final Command EXIT = new Command("Exit", Command.EXIT, 1) ; 
    private final Command GRID = new Command("Grid", Command.SCREEN, 1);    
    private Form form ; 
    
    public CommandsService(Form form) {
        setForm(form) ;
    }
    
    public Form getForm() {
        return this.form ;
    }
    
    public void setForm(Form form) {
        this.form = form ;
    }
    
    public Vector generateCommands() {
        Vector commands = new Vector() ;
        commands.addElement(EXIT);
        commands.addElement(GRID);
        return commands ;
    }
    
    public void processCommand(Command command, MIDlet midlet, Form form) {
        if (command == GRID) {
            Display.getDisplay(midlet).setCurrent(form);
        } else if (command == EXIT) {
            midlet.notifyDestroyed();
        }
    }
}
