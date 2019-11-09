/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.*;
import java.util.ArrayList;

/**
 *
 * @author sala 23 - pcs 16
 */
public class FIFO extends Algoritmo{
    
    
    public FIFO(String nombre, String caracteristicas, String funcion, String ventaja, ArrayList<Proceso> procesos){
        super(nombre, caracteristicas, funcion, ventaja);
        this.setProcesos(procesos);
    }
    
    public FIFO(String nombre, String caracteristicas, String funcion, String ventaja, Proceso proceso){
        super(nombre, caracteristicas, funcion, ventaja);
        this.setProcesos(new ArrayList<Proceso>());
        this.agregarProceso(proceso);
    }
    
        public boolean agregarProceso(Proceso proceso){
        return this.getProcesos().add(proceso);
    }
    
    public boolean quitarProceso(Proceso proceso){
        return this.getProcesos().remove(proceso);
    }
}
