package it.qubixic.showcase.commands;

import it.qubixic.showcase.form.GridsForm;
import it.qubixic.showcase.form.DynamicImageLoaderForm;
import it.qubixic.showcase.form.ScrollerForm;
import it.qubixic.showcase.form.RatersForm;
import java.util.Vector;
import javax.microedition.lcdui.Command ;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display; 
import javax.microedition.midlet.*;

public class CommandsService {
    
    private final Command EXIT = new Command("Exit", Command.EXIT, 1) ; 
    private final Command GRID = new Command("Grid", Command.SCREEN, 1);    
    private final Command RATER = new Command("Rater", Command.SCREEN, 1) ;
    private final Command SCROLLER = new Command("Scroller", Command.SCREEN, 1) ;
    private final Command IMAGE_LOADER = new Command("Image Loader", Command.SCREEN, 1) ;
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
        commands.addElement(RATER);
        commands.addElement(SCROLLER);
        commands.addElement(IMAGE_LOADER);
        return commands ;
    }
    
    public void processCommand(Command command, MIDlet midlet) {
        if (command == GRID ) {
            Display.getDisplay(midlet).setCurrent(new GridsForm(midlet));
        } else if (command == RATER) {
            Display.getDisplay(midlet).setCurrent(new RatersForm(midlet));
        } else if (command == SCROLLER) { 
            Display.getDisplay(midlet).setCurrent(new ScrollerForm(midlet));
        } else if (command == IMAGE_LOADER) {
            Display.getDisplay(midlet).setCurrent(new DynamicImageLoaderForm(midlet));
        } else if (command == EXIT) {
            midlet.notifyDestroyed();
        }
    }
}