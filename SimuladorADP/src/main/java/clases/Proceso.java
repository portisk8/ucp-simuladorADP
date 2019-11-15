/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author sala 23 - pcs 16
 */
public class Proceso implements Comparable<Proceso>{
    
    private int id;
    private String nombre;
    private double instante_entrada;
    private double duracion;
    private double duracionRestante;
    private double tiempoSalida;
    private double tiempoEstancia;
    private double tiempoNeto;
    private boolean seleccionado;
    private boolean finalizado;

    
    
    public Proceso(int id, String nombre){
        this.setId(id);
        this.setNombre(nombre);
        this.setInstante_entrada(0);
        this.setDuracion(0);
        this.setSeleccionado(false);
        this.setFinalizado(false);
        this.setDuracionRestante(0);
    }
    
    public Proceso(int id, String nombre, double instante_entrada, double duracion){
        this.setId(id);
        this.setNombre(nombre);
        this.setInstante_entrada(instante_entrada);
        this.setDuracion(duracion);
        this.setSeleccionado(false);
        this.setFinalizado(false);
        this.setDuracionRestante(duracion);
    }
    
    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    protected void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getInstante_entrada() {
        return instante_entrada;
    }

    protected void setInstante_entrada(double instante_entrada) {
        this.instante_entrada = instante_entrada;
    }

    public double getDuracion() {
        return duracion;
    }

    protected void setDuracion(double duracion) {
        this.duracion = duracion;
        this.setDuracionRestante(duracion);
    }

    /**
     * @return the seleccionado
     */
    public boolean isSeleccionado() {
        return seleccionado;
    }

    /**
     * @param seleccionado the seleccionado to set
     */
    protected void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public int compareTo(Proceso proceso) {
        if(this.getInstante_entrada()<proceso.getInstante_entrada()){
            return -1;
        }
        else if(this.getInstante_entrada()>proceso.getInstante_entrada()){
            return 1;
        }
        else{
            return 0;
        }
    }
    
    protected void finalizar(){
        this.setFinalizado(true);
    }
    /**
     * @return the finalizado
     */
    public boolean isFinalizado() {
        return finalizado;
    }

    /**
     * @param finalizado the finalizado to set
     */
    protected void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    /**
     * @return the duracionRestante
     */
    public double getDuracionRestante() {
        return duracionRestante;
    }

    /**
     * @param duracionRestante the duracionRestante to set
     */
    protected void setDuracionRestante(double duracionRestante) {
        this.duracionRestante = duracionRestante;
    }
    
    public void calcularDuracionRestante(int quanto){
        this.setDuracionRestante(this.getDuracionRestante()- quanto);
    }

    /**
     * @return the tiempoSalida
     */
    public double getTiempoSalida() {
        return tiempoSalida;
    }

    /**
     * @param tiempoSalida the tiempoSalida to set
     */
    protected void setTiempoSalida(double tiempoSalida) {
        this.tiempoSalida = tiempoSalida;
    }

    /**
     * @return the tiempoEstancia
     */
    public double getTiempoEstancia() {
        return tiempoEstancia;
    }

    /**
     * @param tiempoEstancia the tiempoEstancia to set
     */
    protected void setTiempoEstancia(double tiempoEstancia) {
        this.tiempoEstancia = tiempoEstancia;
    }
    
    public String toString(){
        return "Proceso: "+this.getNombre()+" - I.E: "+this.getInstante_entrada()+" - Duracion(Ts): "+this.getDuracion()+"\nTiempo de Salida: "+this.getTiempoSalida()+"\nTiempo Estancia(Tr): "+this.getTiempoEstancia()+"\nTr/Ts: "+this.getTiempoNeto()+"\n\n";
    }

    /**
     * @return the tiempoNeto
     */
    public double getTiempoNeto() {
        return tiempoNeto;
    }

    /**
     * @param tiempoNeto the tiempoNeto to set
     */
    protected void setTiempoNeto(double tiempoNeto) {
        this.tiempoNeto = tiempoNeto;
    }
    
    public void resetear(){
        this.setDuracionRestante(this.getDuracion());
        this.setFinalizado(false);
    }
}
        