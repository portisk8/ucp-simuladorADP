
package clases;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala 23 - pcs 16
 */
public class SRT extends Algoritmo implements Runnable{
    
    
    
    
    public SRT(String nombre, String caracteristicas, String ventaja, ArrayList<Proceso> procesos){
        super(nombre, caracteristicas, ventaja);
        Collections.sort(procesos);
        super.setPendiente(new ProcesoTableModel(procesos));
        super.setEjecutando(new ProcesoTableModel(new ArrayList<Proceso>()));
        super.setListo(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setProcesos(procesos);
        
    }
    
    public SRT(String nombre, String caracteristicas, String funcion, String ventaja, Proceso proceso){
        super(nombre, caracteristicas, ventaja);
        this.setProcesos(new ArrayList<Proceso>());
        this.agregarProceso(proceso);
    }
    
    public boolean agregarProceso(Proceso proceso){
        return this.getProcesos().add(proceso);
    }
    
    public boolean quitarProceso(Proceso proceso){
        return this.getProcesos().remove(proceso);
    }

    public void run() {
           
        }
}