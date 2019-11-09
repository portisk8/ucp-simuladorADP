/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.io.*;

/**
 *
 * @author sala 23 - pcs 16
 */
public class Simulacion {
    
    private Calendar inicio;
    private Algoritmo algoritmo;
    
    public Simulacion(){
        this.setInicio(new GregorianCalendar());
        this.setAlgoritmo(new Algoritmo());
    }
    
    public Calendar getInicio() {
        return inicio;
    }

    private void setInicio(Calendar p_inicio) {
        this.inicio = p_inicio;
    }
    
    public Algoritmo getAlgoritmo() {
        return algoritmo;
    }

    public void setAlgoritmo(Algoritmo algoritmo) {
        this.algoritmo = algoritmo;
    }
    
    
    public void simular(){
    
    }
    
    public ArrayList leerProcesos(){
        ArrayList procesos = new ArrayList();
        try {
            
            FileInputStream archivoFIS = new FileInputStream("Procesos.dat");
            DataInputStream archivoDIS = new DataInputStream(archivoFIS);
            
            while (archivoDIS.available() > 0) {
                int id = archivoDIS.readInt();
                String nombre = archivoDIS.readUTF();
                double instante_entrada = archivoDIS.readDouble();
                double duracion=  archivoDIS.readDouble();
                Proceso proceso = new Proceso(id, nombre, instante_entrada, duracion);
                proceso.mostrar();
                
            }
            archivoDIS.close();
            
        }catch(FileNotFoundException fnfe){
            System.out.println("Archivo no encontrado");
        }catch(IOException ioe){
            System.out.println("Error al leer archivo");
        }
        return procesos;
    }
    
    public boolean grabarProcesos(ArrayList<Proceso> pProcesos){
        boolean bandera = true;
        try{
            FileOutputStream archiFOS = new FileOutputStream("Procesos.dat", true);
            DataOutputStream archiDOS = new DataOutputStream(archiFOS);
            for(int i=0;i<pProcesos.size();i++){
                archiDOS.writeInt(pProcesos.get(i).getId());
                archiDOS.writeUTF(pProcesos.get(i).getNombre());
                archiDOS.writeDouble(pProcesos.get(i).getInstante_entrada());
                archiDOS.writeDouble(pProcesos.get(i).getDuracion());
                }
            archiDOS.close();
        }
        catch (FileNotFoundException fnfe){
            System.out.println("Archivo no encontrado");
            bandera = false;
        }
        catch (IOException ioe){
            System.out.println("Error al grabar");
            bandera = false;
        }
        
        return bandera;
    }

}