
package clases;
import com.ucp.simuladoradp.main.VentanaSimulacion;
import java.util.*;


public class Algoritmo extends Thread{
    private String nombre;
    private String caracteristicas;
    private String ventaja;
    private ArrayList<Proceso> procesos;
    private ProcesoTableModel pendiente;
    private ProcesoTableModel ejecutando;
    private ProcesoTableModel listo;
    private VentanaSimulacion view; //Se implementa por variable instancia debido a una necesidad de actualizacion de interfaz para la simulacion.
    
    public Algoritmo(){
        this.setNombre(null);
        this.setCaracteristicas(null);
        this.setVentaja(null);
        this.setProcesos(new ArrayList<Proceso>());
    }
    
    public Algoritmo(String nombre, String caracteristicas, String ventaja){
        this.setNombre(nombre);
        this.setCaracteristicas(caracteristicas);
        this.setVentaja(ventaja);
        this.setProcesos(new ArrayList<Proceso>());
    }
    
    public Algoritmo(String nombre, String caracteristicas, String ventaja, ArrayList<Proceso> procesos, VentanaSimulacion view){
        this.setNombre(nombre);
        this.setCaracteristicas(caracteristicas);
        this.setVentaja(ventaja);
        this.setProcesos(new ArrayList<Proceso>());
        this.setView(view);
        Collections.sort(procesos);
        this.setPendiente(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setEjecutando(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setListo(new ProcesoTableModel(new ArrayList<Proceso>()));
        this.setProcesos(procesos);
        this.getView().getjTableProcesosEspera().setModel(this.getPendiente());
        this.getView().getjTableProcesoEnCurso().setModel(this.getEjecutando());
        this.getView().getjTableProcesosTerminados().setModel(this.getListo());
    }
    
    public String getNombre() {
        return nombre;
    }

    private void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    private void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }


    public String getVentaja() {
        return ventaja;
    }

    private void setVentaja(String ventaja) {
        this.ventaja = ventaja;
    }

    
    public String toString(){
        return "Nombre del algoritmo: "+this.getNombre()+"\n\n"+"Caracteristicas: "+this.getCaracteristicas()+"\n\n"+"Ventajas: "+this.getVentaja()+"\n\n";
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
    }
    
    public boolean todosFinalizados(){
        boolean bandera = true;
        int i=0;
        for (Proceso proceso : this.getProcesos()) {
            if(!proceso.isFinalizado()){
                bandera = false;
            }
        }
        return bandera;
    }

    public void setProcesos(ArrayList<Proceso> procesos) {
        this.procesos = procesos;
    }
    
    public boolean agregarProceso(Proceso proceso){
        return this.getProcesos().add(proceso);
    }
    
    public boolean quitarProceso(Proceso proceso){
        return this.getProcesos().remove(proceso);
    }

    /**
     * @return the pendiente
     */
    public ProcesoTableModel getPendiente() {
        return pendiente;
    }

    /**
     * @param pendiente the pendiente to set
     */
    private void setPendiente(ProcesoTableModel pendiente) {
        this.pendiente = pendiente;
    }

    /**
     * @return the ejecutando
     */
    public ProcesoTableModel getEjecutando() {
        return ejecutando;
    }

    /**
     * @param ejecutando the ejecutando to set
     */
    private void setEjecutando(ProcesoTableModel ejecutando) {
        this.ejecutando = ejecutando;
    }

    /**
     * @return the listo
     */
    public ProcesoTableModel getListo() {
        return listo;
    }

    /**
     * @param listo the listo to set
     */
    private void setListo(ProcesoTableModel listo) {
        this.listo = listo;
    }
    
    public Proceso getProcesoByID(int ID){
        Proceso proceso = null;
        for (Proceso procesoAux : this.getProcesos()) {
            if(procesoAux.getId()==ID){
                proceso = procesoAux;
            }
        }
        return proceso;
    }
    
    public void resetProcesos(){
        for (Proceso proceso : this.getProcesos()) {
            proceso.setDuracionRestante(proceso.getDuracion());
            proceso.setFinalizado(false);
        }
    }

    public VentanaSimulacion getView() {
        return view;
    }

    private void setView(VentanaSimulacion view) {
        this.view = view;
    }

    
}
