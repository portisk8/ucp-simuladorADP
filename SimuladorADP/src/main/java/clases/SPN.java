/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import com.ucp.simuladoradp.main.CuadroComparacion;
import com.ucp.simuladoradp.main.VentanaSimulacion;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala 23 - pcs 16
 */
public class SPN extends Algoritmo {
    
        
    
    public SPN(String nombre, String caracteristicas, String ventaja, ArrayList<Proceso> procesos, VentanaSimulacion view){
        super(nombre, caracteristicas, ventaja, procesos, view);
    }
    
    public SPN(String nombre, String caracteristicas, String funcion, String ventaja, Proceso proceso){
        super(nombre, caracteristicas, ventaja);
        this.setProcesos(new ArrayList<Proceso>());
        this.agregarProceso(proceso);
    }
    
    public SPN(String nombre, String caracteristicas, String ventaja){
        super(nombre, caracteristicas, ventaja);
        this.setProcesos(new ArrayList<Proceso>());
    }

    public void run() {
            Proceso proceso;//creamos un proceso de la clase proceso
            super.getView().getTimerCpu().setText("0");//seteamos el valor de la vista fifo con valor cero (0)
            
            int timer = 0;//creamos una variable timer con inicializacion cero (0)
            
            while(!super.todosFinalizados()){//mientras haya procesos en la tabla de pendientes por atender
                for (Proceso procesoActual : super.getProcesos()){
                    if(procesoActual.getInstante_entrada()==timer){
                        super.getPendiente().addRow(procesoActual);
                    }
                }
                if(super.getPendiente().getRowCount()>0 || super.getEjecutando().getRowCount()>0){
                    if(super.getEjecutando().getRowCount() > 0){
                        proceso = super.getEjecutando().getProceso(0);
                    }else{
                        proceso = super.getPendiente().getProceso(super.getPendiente().procesoMenorTiempoEjecucion());//carga proceso de la tabla pendiente a la variable proceso
                        super.getPendiente().removeRow(super.getPendiente().procesoMenorTiempoEjecucion());//una vez cargado el proceso se remueva de la tabla pendiente
                        super.getEjecutando().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                    }
                    timer++;//variable timer aumenta en uno
                    proceso.calcularDuracionRestante(1);
                    super.getEjecutando().removeRow(0);
                    super.getEjecutando().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                        super.getView().getTimerCpu().setText(Integer.toString(timer));//setea el valor del componente de la vist fifo para mostrar en la rafaga de cpu el valor del tiempo
                        try {
                            Thread.sleep(1000);//el hilo queda en modo espera por 1 segundo
                        } catch (InterruptedException ex) {
                            Logger.getLogger(VentanaSimulacion.class.getName()).log(Level.SEVERE, null, ex);//en caso de ocurrir un error en la ejecucion se enviara un mensaje de aviso informando del error
                        }
                    if(proceso.getDuracionRestante()<=0){
                        super.getEjecutando().removeRow(0);//sacamos el proceso de la tabla procesos en ejecucion
                        super.getListo().addRow(proceso);//cargamos el proceso en la tabla procesos terminados
                        super.getProcesoByID(proceso.getId()).finalizar();
                    }
            }
                else{
                    timer++;
                    super.getView().getTimerCpu().setText(Integer.toString(timer));//setea el valor del componente de la vist fifo para mostrar en la rafaga de cpu el valor del tiempo
                    try {
                        Thread.sleep(1000);//el hilo queda en modo espera por 1 segundo
                    } catch (InterruptedException ex) {
                        Logger.getLogger(VentanaSimulacion.class.getName()).log(Level.SEVERE, null, ex);//en caso de ocurrir un error en la ejecucion se enviara un mensaje de aviso informando del error
                    }
                }
        }
     }
    
    
    public void run(CuadroComparacion cuadro) {
            String mostrar = null;
            mostrar = super.getNombre()+"\n---------------------------------------------------------------\n";
            Proceso proceso;//creamos un proceso de la clase proceso
            int timer = 0;//creamos una variable timer con inicializacion cero (0)
            
            while(!super.todosFinalizados()){//mientras haya procesos en la tabla de pendientes por atender
                for (Proceso procesoActual : super.getProcesos()){
                    if(procesoActual.getInstante_entrada()==timer){
                        super.getPendiente().addRow(procesoActual);
                    }
                }
                if(super.getPendiente().getRowCount()>0 || super.getEjecutando().getRowCount()>0){
                    if(super.getEjecutando().getRowCount() > 0){
                        proceso = super.getEjecutando().getProceso(0);
                    }else{
                        proceso = super.getPendiente().getProceso(super.getPendiente().procesoMenorTiempoEjecucion());//carga proceso de la tabla pendiente a la variable proceso
                        super.getPendiente().removeRow(super.getPendiente().procesoMenorTiempoEjecucion());//una vez cargado el proceso se remueva de la tabla pendiente
                        super.getEjecutando().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                    }
                    timer++;//variable timer aumenta en uno
                    proceso.calcularDuracionRestante(1);
                    super.getEjecutando().removeRow(0);
                    super.getEjecutando().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                    if(proceso.getDuracionRestante()<=0){
                        proceso.setTiempoSalida(timer);
                        proceso.setTiempoEstancia(proceso.getTiempoSalida()-proceso.getInstante_entrada());
                        proceso.setTiempoNeto(proceso.getTiempoEstancia()/proceso.getDuracion());
                        super.getEjecutando().removeRow(0);//sacamos el proceso de la tabla procesos en ejecucion
                        super.getListo().addRow(proceso);//cargamos el proceso en la tabla procesos terminados
                        super.getProcesoByID(proceso.getId()).finalizar();
                    }
            }
                else{
                    timer++;
                }
        }
            double tiempoNetoMedio=0;
            double tiempoEstanciaMedio=0;
            for (int i=0;i<super.getProcesos().size();i++) {
            mostrar = mostrar + super.getProcesos().get(i).toString();
            tiempoNetoMedio=tiempoNetoMedio+super.getProcesos().get(i).getTiempoNeto();
            tiempoEstanciaMedio=tiempoEstanciaMedio+super.getProcesos().get(i).getTiempoEstancia();
        }
            tiempoNetoMedio=(tiempoNetoMedio/super.getProcesos().size());
            tiempoEstanciaMedio=(tiempoEstanciaMedio/super.getProcesos().size());
            mostrar=mostrar+"Tiempo Estancia Medio(Tr): "+tiempoEstanciaMedio+"\nTr/Ts Medio: "+tiempoNetoMedio;
            cuadro.getSpnArea().setText(mostrar);
            super.resetProcesos();
     }
}