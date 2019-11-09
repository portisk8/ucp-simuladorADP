/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import com.ucp.simuladoradp.main.FifoView;
import java.util.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sala 23 - pcs 16
 */
public class FIFO extends Algoritmo implements Runnable{
    
    
    private FifoView fifoView;
    
    
    public FIFO(String nombre, String caracteristicas, String ventaja, ArrayList<Proceso> procesos, FifoView fifoView){
        super(nombre, caracteristicas, ventaja);
        this.fifoView = fifoView;
        Collections.sort(procesos);
        super.setPendiente(new ProcesoTableModel(new ArrayList<Proceso>()));
        super.setEjecutando(new ProcesoTableModel(new ArrayList<Proceso>()));
        super.setListo(new ProcesoTableModel(new ArrayList<Proceso>()));
        super.setProcesos(procesos);
        
        
        this.fifoView.getjTableProcesosEspera().setModel(super.getPendiente());
        this.fifoView.getjTableProcesoEnCurso().setModel(super.getEjecutando());
        this.fifoView.getjTableProcesosTerminados().setModel(super.getListo());
    }
    
    public FIFO(String nombre, String caracteristicas, String funcion, String ventaja, Proceso proceso){
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
            Proceso proceso;//creamos un proceso de la clase proceso
            this.fifoView.getTimerCpu().setText("0");//seteamos el valor de la vista fifo con valor cero (0)
            
            int timer = 0;//creamos una variable timer con inicializacion cero (0)
            
            while(!super.todosFinalizados()){//mientras haya procesos en la tabla de pendientes por atender
                for (Proceso procesoActual : super.getProcesos()) {
                    if(procesoActual.getInstante_entrada()==timer){
                        super.getPendiente().addRow(procesoActual);
                    }
                }
                if(super.getPendiente().getRowCount()>0 || super.getEjecutando().getRowCount()>0){
                    if(super.getEjecutando().getRowCount() > 0){
                        proceso = super.getEjecutando().getProceso(0);
                    }else{
                        proceso = super.getPendiente().getProceso(0);//carga proceso de la tabla pendiente a la variable proceso
                        super.getPendiente().removeRow(0);//una vez cargado el proceso se remueva de la tabla pendiente
                        super.getEjecutando().addRow(proceso);//se envia el proceso a la tabla de procesos en ejecucion
                    }
                    timer++;//variable timer aumenta en uno
                    proceso.calcularDuracionRestante(1);
                    //for (int i = 0; i < proceso.getDuracion(); i++){//para i iniciando en 0 hasta i menor al tiempo de rafaga de cpu, i aumenta en 1
                        this.fifoView.getTimerCpu().setText(Integer.toString(timer));//setea el valor del componente de la vist fifo para mostrar en la rafaga de cpu el valor del tiempo
                        try {
                            Thread.sleep(1000);//el hilo queda en modo espera por 1 segundo
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FifoView.class.getName()).log(Level.SEVERE, null, ex);//en caso de ocurrir un error en la ejecucion se enviara un mensaje de aviso informando del error
                        }
                    //}
                    if(proceso.getDuracionRestante()<=0){
                        super.getEjecutando().removeRow(0);//sacamos el proceso de la tabla procesos en ejecucion
                        super.getListo().addRow(proceso);//cargamos el proceso en la tabla procesos terminados
                        super.getProcesoByID(proceso.getId()).finalizar();
                    }
                    
            }
                else{
                    timer++;
                    this.fifoView.getTimerCpu().setText(Integer.toString(timer));//setea el valor del componente de la vist fifo para mostrar en la rafaga de cpu el valor del tiempo
                    try {
                        Thread.sleep(1000);//el hilo queda en modo espera por 1 segundo
                    } catch (InterruptedException ex) {
                        Logger.getLogger(FifoView.class.getName()).log(Level.SEVERE, null, ex);//en caso de ocurrir un error en la ejecucion se enviara un mensaje de aviso informando del error
                    }
                }
        }
     }
}