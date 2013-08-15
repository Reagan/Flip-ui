package it.qubixic.showcase.form;

import it.qubixic.showcase.commands.CommandsService;
import java.util.Vector;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Command ;
import javax.microedition.lcdui.CommandListener ;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.*;

public abstract class BaseForm extends Form  implements CommandListener {
    
    private CommandsService commandsService = new CommandsService(this) ;
    private MIDlet midlet ;
    
    public BaseForm(String title, MIDlet midlet) {
        super(title);
        setMIDlet(midlet) ;
        appendCommands(generateCommands()) ;
        setCommandListener(this);
    }

    protected void setMIDlet (MIDlet midlet) {
        this.midlet = midlet ;
    }
    
    protected void appendCommands (Vector commands) {
        if (null != commands && commands.size() > 0) {
            for (int commandsCounter = 0; commandsCounter < commands.size(); 
                    commandsCounter++) {
                addCommand((Command) commands.elementAt(commandsCounter));
            }
        }
    }
    
    protected Vector generateCommands () {
        return commandsService.generateCommands() ;
    }

    public void commandAction(Command c, Displayable d) {
        commandsService.processCommand(c, midlet);
    }
}
