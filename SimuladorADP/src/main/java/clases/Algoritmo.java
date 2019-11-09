
package clases;
import java.util.*;


public class Algoritmo {
    private String nombre;
    private String caracteristicas;
    private String ventaja;
    private ArrayList<Proceso> procesos;
    private ProcesoTableModel pendiente;
    private ProcesoTableModel ejecutando;
    private ProcesoTableModel listo;

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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }


    public String getVentaja() {
        return ventaja;
    }

    public void setVentaja(String ventaja) {
        this.ventaja = ventaja;
    }

    
    public String toString(){
        return "Nombre del algoritmo: "+this.getNombre()+"\n\n"+"Caracteristicas: "+this.getCaracteristicas()+"\n\n"+"Ventajas: "+this.getVentaja()+"\n\n";
    }

    public ArrayList<Proceso> getProcesos() {
        return procesos;
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
    public void setPendiente(ProcesoTableModel pendiente) {
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
    public void setEjecutando(ProcesoTableModel ejecutando) {
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
    public void setListo(ProcesoTableModel listo) {
        this.listo = listo;
    }
}
