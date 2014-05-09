/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
public class Room 
{
    private String description;  
    private HashMap<String,Room> mapDesalidas; 
   
    private  ArrayList<Item> items;

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        mapDesalidas=new HashMap<String,Room>();
        items=new ArrayList<Item>();
        // objetos=new HashMap<String,Integer>();
    }
    /** Añade un Item a la Room
     * @param descripcion
     * @param peso
       */
    public void addItem(String descripcion,int peso)
    {
        items.add(new Item(descripcion,peso));
    }
       public void addItem(Item item)
    {
        items.add(item);
    }
      /**
     * imprime los items.
     */
    public void printItems(){
        System.out.println("\nObjetos: ");
        
        for(int i=0;i<items.size();i++)
        {
            System.out.println(items.get(i).getDescripcion()+" "+ items.get(i).getPeso()+" kilos");
        }
    }

    public void setExit(String clave,Room estancia)
    {
        mapDesalidas.put(clave, estancia);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
       
        return description;
    }

    /**
     * 
     * @return A LongDescription of the room, including exits.
     */
    public String getLongDescription()
    {  
        
        return "Estoy en " +description+ ".\n" +"Salidas:";
    }
    
    public Item getItem(String describe)
    {
        Item obj=null;
        for(int i=0;i<items.size();i++)
       {    if(items.get(i)!=null){            
            if(items.get(i).getDescripcion().equals(describe))
            {
                obj=items.get(i);
            }            
        }
        else{items.remove(i);}
    }
        
        return obj;
    }
    
    public void borraObjeto(Item item)
    {
        items.remove(item);
    }

    public String getExitString()
    {   String lasSalidas=" ";
        Set <String> salidas=mapDesalidas.keySet();

        for(String sal:salidas){
            lasSalidas+=sal+" ";
        }
        return lasSalidas;
    }

    public Room getExits(String salida)
    {
        return mapDesalidas.get(salida);
    }
}
