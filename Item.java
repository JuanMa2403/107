
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    // instance variables - replace the example below with your own
    private int peso;
    private String descripcion;
    /**
     * Constructor for objects of class Item
     * @param d: descripcion
     * @param p: peso
     */
    public Item(String d, int p)
    {
        // initialise instance variables
        peso = p;
        descripcion=d;
    }

    /**
     *
     * @return  peso 
     */
    public int getPeso()
    {
        // put your code here
        return peso;
    }

    /**
     *
     * @return  descripcion 
     */
    public String getDescripcion(){
        return descripcion;
    }
}
