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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
import java.util.HashMap;
import java.util.Set;
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room suresteSale;
    private Room noresteSale;
    private Room suroesteSale;
    private Room noroesteSale;
    private HashMap<String,Room> mapDesalidas;
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
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west,Room NE,Room NO,Room SO,Room SE) 
    {     
        if(north != null)
            mapDesalidas.put("north", north);
        if(east != null)          
            mapDesalidas.put("east", east);
        if(south != null)
            mapDesalidas.put("south", south);
        if(west != null)
            mapDesalidas.put("west", west);
        if(NE != null)
            mapDesalidas.put("noreste", NE);
        if(NO != null)
            mapDesalidas.put("noroeste", NO);
        if(SE != null)
            mapDesalidas.put("sureste", SE);
        if(SO != null)
            mapDesalidas.put("suroeste", SO);

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
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String describe="";
        System.out.println("Estoy en: ");
        System.out.println("Salidas: ");
        
        return "Estoy en " +getDescription()+ ".\n" +"Salidas:";
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
