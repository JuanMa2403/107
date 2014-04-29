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

public class Game 
{
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        //Room outside, theater, pub, lab, office;
        Room turquesa, azul, rojo, granate, verdeClaro,verdeOscuro,rosa, morado, neutro;
        // create the rooms
        /**outside = new Room("outside the main entrance of the university");
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");

        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);*/

        //currentRoom = outside;  // start game outside
        turquesa=new Room("Estoy el entorno azul turquesa, hay dos personas sentadas ¡pero no tienen sillas!");
        azul=new Room("Estoy en el entorno azul, hay dos sillas vacias");
        rojo=new Room("Estoy en el entorno rojo,me duelen los ojos y hay una mesa vacia");
        verdeClaro=new Room("Estoy en el entorno verde claro, ¡hay dos tazas de cafe pegadas al techo !");
        verdeOscuro=new Room("Estoy en el entorno verde oscuro, hay una jarra llena de cafe...¡Suspendida en el aire!");
        rosa=new Room("Este es un entorno rosa y horrible, hay un camarero descalzo");
        morado=new Room("Estoy en el entorno morado, hay un cuadro cortado a la mitad");
        neutro=new Room("Esto parece normal, pero no hay nada aparte de unos zapatos");
        granate=new Room("Estoy en el entorno morado claro, aqui esta la otra mitad del cuadro");

        turquesa.setExits(neutro, null, azul, null,rosa,rojo,null,null);
        azul.setExits(turquesa, null, null, null,granate,morado,null,null);
        rojo.setExits(null, granate, null, neutro,null,verdeClaro,turquesa,null);
        neutro.setExits(verdeClaro,rojo,turquesa,rosa,null,null,null,null);
        verdeClaro.setExits(verdeOscuro, null, neutro, null,null,null,rosa,rojo);
        verdeOscuro.setExits(null, null, verdeClaro, null,null,null,morado,granate);
        rosa.setExits(null, neutro, null, morado,verdeClaro,null,null,turquesa);
        morado.setExits(null, rosa, null, null,verdeOscuro,null,null,azul);
        granate.setExits(null,null , null, rojo,null,verdeOscuro,azul,null);

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
        System.out.println(currentRoom.getDescription());
        System.out.print("Exits: ");
        if(currentRoom.northExit != null) {
            System.out.print("north ");
        }
        if(currentRoom.eastExit != null) {
            System.out.print("east ");
        }
        if(currentRoom.southExit != null) {
            System.out.print("south ");
        }
        if(currentRoom.westExit != null) {
            System.out.print("west ");
        }
        
        if(currentRoom.suresteSale!= null){
            System.out.print("sureste");
        }
        if(currentRoom.noresteSale!= null){
            System.out.print("noreste");
        }
        if(currentRoom.suroesteSale!= null){
            System.out.print("suroeste");
        }
        if(currentRoom.noroesteSale!= null){
            System.out.print("noroeste");
        }
        System.out.println();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido World of Zuul!");
        System.out.println("World of Zuul, persigues a un mago que te ha robado un amuleto");
        System.out.println("para que no consigas atraparlo ha congelado el tiempo");
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
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
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
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.northExit;
        }
        if(direction.equals("east")) {
            nextRoom = currentRoom.eastExit;
        }
        if(direction.equals("sureste")){
            nextRoom=currentRoom.suresteSale;
        }
        if(direction.equals("noreste")){
            nextRoom=currentRoom.noresteSale;
        }
        if(direction.equals("suroeste")){
            nextRoom=currentRoom.suroesteSale;
        }
        if(direction.equals("noroeste")){
            nextRoom=currentRoom.noroesteSale;
        }
        if(direction.equals("south")) {
            nextRoom = currentRoom.southExit;
        }
        if(direction.equals("west")) {
            nextRoom = currentRoom.westExit;
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
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
