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
    private HashMap<String,Room> mapDesalidas;   
    private String descripcion;
    private int peso;
    

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
        asignaObjeto();
        // objetos=new HashMap<String,Integer>();
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    private void asignaObjeto()

    {       
        if(description.equals(" el entorno azul turquesa ")){ 
            descripcion="personas";
            peso=130;
        }
        else if(description.equals(" el entorno azul ")){
            descripcion="sillas";
            peso=20;
        }
        else  if(description.equals(" el entorno rojo ")){  
            descripcion="mesa";
            peso=30;
        }
        else  if(description.equals(" el entorno verde claro ")){ 
            descripcion="tazas";
            peso=1;
        }
        else  if(description.equals(" el entorno verde oscuro ")){ 
            descripcion="jarra";
            peso=2;
        }
        else  if(description.equals(" el entorno rosa ")){  
            descripcion="camarero";
            peso=70;
        }
        else  if(description.equals(" el entorno morado ")){  
            descripcion="cuadro cortado";
            peso=1;
        }
        else  if(description.equals(" el lugar original ")){ 
            descripcion="zapatos";
            peso=1;
        }
        else  if(description.equals(" el entorno morado claro ")){ 
            descripcion="otra mitad del cuadro";
            peso=1;
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
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {  
        asignaObjeto();
        return "Estoy en " +description+ ".\n" +"Salidas:";
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
