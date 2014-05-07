/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.ArrayList;
public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private ArrayList<Room> visitadas;
    private int indiceRoom;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
        visitadas=new ArrayList<Room>();       
        
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {

        Room turquesa, azul, rojo, granate, verdeClaro,verdeOscuro,rosa, morado, neutro;
        // create the rooms       
        /*------------------------------------------------------------------------------------------------------------------¬
        turquesa=new Room("Estoy el entorno azul turquesa, hay dos personas sentadas ¡pero no tienen sillas!");             |
        azul=new Room("Estoy en el entorno azul, hay dos sillas vacias");                                                   |
        rojo=new Room("Estoy en el entorno rojo,me duelen los ojos y hay una mesa vacia");                                  |
        verdeClaro=new Room("Estoy en el entorno verde claro, ¡hay dos tazas de cafe pegadas al techo !");                  |
        verdeOscuro=new Room("Estoy en el entorno verde oscuro, hay una jarra llena de cafe...¡Suspendida en el aire!");    |
        rosa=new Room("Este es un entorno rosa y horrible, hay un camarero descalzo");                                      |
        morado=new Room("Estoy en el entorno morado, hay un cuadro cortado a la mitad");                                    |
        neutro=new Room(" parece normal, pero no hay nada aparte de unos zapatos");                                         |
        granate=new Room("Estoy en el entorno morado claro, aqui esta la otra mitad del cuadro");                           |
        ------------------------------------------------------------------------------------------------------------------- 
         */                         

        turquesa=new Room(" el entorno azul turquesa ");
        azul=new Room(" el entorno azul ");
        rojo=new Room(" el entorno rojo ");
        verdeClaro=new Room(" el entorno verde claro ");
        verdeOscuro=new Room(" el entorno verde oscuro ");
        rosa=new Room(" el entorno rosa ");
        morado=new Room(" el entorno morado ");
        neutro=new Room(" el lugar original ");
        granate=new Room(" el entorno morado claro ");

        neutro.setExit("north", verdeClaro);
        neutro.setExit("south", turquesa);
        neutro.setExit("west", rosa);
        neutro.setExit("east", rojo);

        turquesa.setExit("north", neutro);
        turquesa.setExit("south", azul);
        turquesa.setExit("noroeste", rosa);
        turquesa.setExit("noreste", rojo);

        rojo.setExit("west", neutro);
        rojo.setExit("east", granate);
        rojo.setExit("noroeste", verdeClaro);
        rojo.setExit("suroeste", turquesa);

        rosa.setExit("west", morado);
        rosa.setExit("east", neutro);
        rosa.setExit("noreste", verdeClaro);
        rosa.setExit("sureste", turquesa);

        verdeClaro.setExit("north", verdeOscuro);
        verdeClaro.setExit("south", neutro);
        verdeClaro.setExit("sureste", rojo);
        verdeClaro.setExit("suroeste", rosa);

        verdeOscuro.setExit("south", verdeClaro);
        verdeOscuro.setExit("sureste", granate);
        verdeOscuro.setExit("suroeste", morado);

        granate.setExit("west", rojo);
        granate.setExit("suroeste", azul);
        granate.setExit("noroeste", verdeOscuro);

        azul.setExit("north", turquesa);
        azul.setExit("noroeste", morado);
        azul.setExit("noreste", granate);

        morado.setExit("east", rosa);
        morado.setExit("noreste", verdeOscuro);
        morado.setExit("sureste", azul);

        morado.setExit("teleport_azul",rojo);

        
        // Objetos a las habitaciones

        neutro.addItem("zapatos", 2);
        neutro.addItem("colillas", 0);
        neutro.addItem("pelota", 1);

        currentRoom = neutro;
      
    }
    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();

            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    private void printLocationInfo(){       
        System.out.print(currentRoom.getLongDescription() + currentRoom.getExitString()); 
        currentRoom.printItems();
        System.out.println();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido World of Zuul!");
        System.out.println("World of Zuul, persigo a un mago que me ha robado un amuleto");
        System.out.println("para que no consiga atraparlo ha congelado el tiempo");
        System.out.println("Al hacerlo la realidad se fragmento y debo reunir los objetos ");
        System.out.println("que encuentre para recomponer la situacion a su forma previa,  ");
        System.out.println("así el tiempo volvera a desbloquearse");
        System.out.println();
        System.out.println("Type 'help' if you need help.");
        System.out.println();
       
        printLocationInfo();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
       
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
            parser.solucionA().showAll();
            parser.solucionB();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
          
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            printLocationInfo();
        }

        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("back")) {
            
               if(visitadas.size()>0){
                currentRoom=visitadas.get(visitadas.size()-1);
                printLocationInfo();
                visitadas.remove(visitadas.size()-1);
                
            }
             
            
        }

        

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help");

        parser.solucionA().showAll();//Solucion A Ac0116        
        parser.solucionB();//Solucion B Ac0116
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();
   
        // Try to leave current room.
        Room nextRoom = currentRoom.getExits(direction); 
       
        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitadas.add(currentRoom);
            currentRoom = nextRoom;        
            printLocationInfo();
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
