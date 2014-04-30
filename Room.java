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
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
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
            northExit = north;
        if(east != null)
            eastExit = east;
        if(south != null)
            southExit = south;
        if(west != null)
            westExit = west;
        if(NE != null)
            noresteSale = NE;
        if(NO != null)
             noroesteSale= NO;
        if(SE != null)
            suresteSale = SE;
        if(SO != null)
            suroesteSale = SO;        
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    public String getExitString()
    {   String lasSalidas=" ";
        
        String[] salidas={" Norte"," Sur"," Este"," Oeste"," Sureste"," Suroeste"," Noreste"," Noroeste"};
        Room[] salidasDeroom={northExit,southExit,eastExit,westExit,suresteSale,suroesteSale,noresteSale,noroesteSale};
       
        for(int i=0;i<salidasDeroom.length;i++){
            if(salidasDeroom[i]!=null){
                lasSalidas=lasSalidas + salidas[i];
            }
        }
        return lasSalidas;
    }
    public Room getExits(String salida)
    {
        Room salidaDeRoom=null;
            
        if(salida.equals("north")){
            salidaDeRoom=northExit;
        }
         if(salida.equals("east")){
            salidaDeRoom=eastExit;
        }
         if(salida.equals("sureste")){
            salidaDeRoom=suresteSale;
        }
         if(salida.equals("noreste")){
            salidaDeRoom=noresteSale;
        }
         if(salida.equals("suroeste")){
            salidaDeRoom=suroesteSale;
        }
         if(salida.equals("noroeste")){
            salidaDeRoom=noroesteSale;
        }
         if(salida.equals("south")){
            salidaDeRoom=southExit;
        }
         if(salida.equals("west")){
            salidaDeRoom=westExit;
        }    
            
        
        return salidaDeRoom;
    }
}
