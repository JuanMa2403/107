
/**
 * Write a description of class Player here.
 * 
 * @author (juanma)  
 */
import java.util.ArrayList;
public class Player
{
    // instance variables - replace the example below with your own
    private ArrayList<Item> inventario;
    private Room estancia;
    public final int CARGA_MAXIMA;

    private ArrayList<Room> visitadas;
    /**
     * Constructor for objects of class Player
     */
    public Player()    {
        // initialise instance variables
        inventario=new ArrayList<Item>();
        visitadas=new ArrayList<Room>();
        CARGA_MAXIMA=70;
    }
    
    /**
     *Añade objetos al inventario
     * 
     * @param obj
     */
    public void addItem(Item obj)
    {        
        inventario.add(obj);       
    }

    public void borrarItem(Item obj)
    {        
        inventario.remove(obj);       
    }

    public Item getItem(String describe){

        Item obj=null;
        for(int i=0;i<inventario.size();i++)
        {
            if(inventario.get(i)!=null){                
                if(inventario.get(i).getDescripcion().equals(describe))
                {
                    obj=inventario.get(i);
                }            
            }
            else{inventario.remove(i);}
        }

        return obj;
    }

    public void setEstancia(Room estaEn){
        estancia=estaEn;
    }
    private int carga()
    {int carga=0;
        for(int i=0;i<inventario.size();i++)
        {
            carga+=inventario.get(i).getPeso();
        }
        return carga;
        
    }
    
    private void takeObject(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Coger que?");
            return;
        }

        String descripcion = command.getSecondWord();
        if(carga()+estancia.getItem(descripcion).getPeso()<CARGA_MAXIMA){
            addItem(estancia.getItem(descripcion));}
        else{
            System.out.println("No puedo llevar más"+" "+ "Pesa "+ estancia.getItem(descripcion).getPeso());
            System.out.println(estancia.getItem(descripcion).getPeso()+carga()-CARGA_MAXIMA +" MAS de lo puedo llevar");
        }    
        if (estancia.getItem(descripcion) == null) {
            System.out.println("No hay");
        }
        else{ estancia.borraObjeto(estancia.getItem(descripcion)); }

    }

    private void dropObject(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("tirar que?");
            return;
        }

        String descripcion = command.getSecondWord(); 
        estancia.addItem(getItem(descripcion));
        if (getItem(descripcion) == null) {
            System.out.println("No tengo");
        }
        else{borrarItem(getItem(descripcion)); }
        //jugador.borrarItem(jugador.getItem(descripcion));       

       
    }
    private void printLocationInfo(){       
        System.out.print(estancia.getLongDescription() + estancia.getExitString()); 
        estancia.printItems();
        System.out.println();
    }
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = estancia.getExits(direction); 

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            visitadas.add(estancia);
            estancia = nextRoom;
            setEstancia(estancia);
            printLocationInfo();
        }
    }
    
        public boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
       
        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
      
         if (commandWord.equals("go")) {
            goRoom(command);
          
        }
     
        else if (commandWord.equals("look")) {
            printLocationInfo();
        }

        else if (commandWord.equals("eat")) {
            System.out.println("You have eaten now and you are not hungry any more");
        }
        else if (commandWord.equals("back")) {
            
               if(visitadas.size()>0){
                estancia=visitadas.get(visitadas.size()-1);
                printLocationInfo();
                visitadas.remove(visitadas.size()-1);
                
            }            
            
        }
           else if (commandWord.equals("take")) {
            
             takeObject(command);
            
        }
            else if (commandWord.equals("drop")) {
            
             dropObject(command);
            
        }

        

        return wantToQuit;
    }
}
