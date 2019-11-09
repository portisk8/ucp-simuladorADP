/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import java.util.*;
/**
 *
 * @author sala 23 - pcs 16
 */
public class SPN extends Algoritmo{
        
    
    public SPN(String nombre, String caracteristicas, String funcion, String ventaja, ArrayList<Proceso> procesos){
        super(nombre, caracteristicas, funcion, ventaja);
        this.setProcesos(procesos);
    }
    
    public SPN(String nombre, String caracteristicas, String funcion, String ventaja, Proceso proceso){
        super(nombre, caracteristicas, funcion, ventaja);
        this.setProcesos(new ArrayList<Proceso>());
        this.agregarProceso(proceso);
    }

}
