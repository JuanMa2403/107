/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.HashMap;
import java.util.Set;

public class CommandWords
{
    // a constant array that holds all valid command words

    private HashMap<String,Option> validCommands; 
    /*private static final String[] validCommands = {
    "go", "quit", "help","look","eat","back","take","drop","inventario"

    GO, QUIT,HELP ,LOOK ,EAT ,BACK, TAKE, DROP, INVENTARIO,UNKNOWN;  
    };*/

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands=new HashMap<String,Option>();

        validCommands.put("go", Option.GO);
        validCommands.put("quit", Option.QUIT);
        validCommands.put("help", Option.HELP); 
        validCommands.put("look", Option.LOOK);          
        validCommands.put("eat", Option.EAT);
        validCommands.put("back", Option.BACK);
        validCommands.put("take", Option.TAKE);
        validCommands.put("drop", Option.DROP);
        validCommands.put("inventario", Option.INVENTARIO);
       

        // nothing to do at the moment...
    }
    
    /**
     * Print all valid commands to System.out
     */
    public void showAll(){

        System.out.println("Comandos: ");
        Set<String> comandos = validCommands.keySet();
        for(String comando : comandos){
            System.out.print(comando + " " );
        }
        System.out.println();

    }

    /**
     * Return the Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return The Option correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public Option getCommandWord(String commandWord)
    {
        Option opcion=null;
            if(isCommand(commandWord))
            {
                opcion= validCommands.get(commandWord);
            }
            else
            {
                opcion=Option.UNKNOWN;
            }
        
        return  opcion;
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {        
        return validCommands.containsKey(aString);
    }
}
