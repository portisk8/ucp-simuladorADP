
/**
 * Write a description of class Proceso here.
 * 
 * @author (Juan F González) 
 * @version (0.1)
 */
public class Proceso{
    //Atributos
    private int id;
    private String nombre;
    private int prioridad;
    private double instante_entrada;
    private double duracion;

    //Constructor for objects of class Proceso
    public Proceso(int p_id, String name, int prio, double ent, double length){
        // initialise instance variables
        setid(p_id);
        setname(name);
        setprioridad(prio);
        setinstanteEntrada(ent);
        setduracion(length);
    }
    //setters
    private void setid(int p_id){
        this.id = p_id;
    }
    private void setname(String name){
        this.nombre = name;
    }
    private void setprioridad(int prio){
        this.prioridad = prio;
    }
    private void setinstanteEntrada(double ent){
        this.instante_entrada = ent;
    }
    private void setduracion(double length){
        this.duracion = length;
    }
    //getters
}
