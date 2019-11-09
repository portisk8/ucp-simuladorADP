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
public class Proceso {
    
    private int id;
    private String nombre;
    private double instante_entrada;
    private double duracion;

    public Proceso(int id, String nombre){
        setId(id);
        setNombre(nombre);
    }
    
    public Proceso(int id, String nombre, double instante_entrada, double duracion){
        this.setId(id);
        this.setNombre(nombre);
        this.setInstante_entrada(instante_entrada);
        this.setDuracion(duracion);
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getInstante_entrada() {
        return instante_entrada;
    }

    public void setInstante_entrada(double instante_entrada) {
        this.instante_entrada = instante_entrada;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }
    
    public void mostrar() {
        System.out.println(""+this.getId());
        System.out.println(this.getNombre());
        System.out.println(""+this.getInstante_entrada());
        System.out.println(""+this.getDuracion());
    }
}    
        