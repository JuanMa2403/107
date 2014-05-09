
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
    public int cargamaxima;
    /**
     * Constructor for objects of class Player
     */
    public Player(Room estaEn)    {
        // initialise instance variables
        inventario=new ArrayList<Item>();
        estancia=estaEn;
        cargamaxima=50;
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
}
