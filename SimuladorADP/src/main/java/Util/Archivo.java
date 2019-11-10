/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import clases.Proceso;
import clases.ProcesoTableModel;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author porti
 */
public class Archivo {
    
    public Archivo (){}
    
    public static ArrayList<Proceso> getProcesos(){
        JFileChooser chooser = new JFileChooser();
        ArrayList procesos = new ArrayList();
        int result = chooser.showOpenDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            File f = chooser.getSelectedFile();
            
            try {
                FileInputStream archivoFIS = new FileInputStream(f);
                DataInputStream archivoDIS = new DataInputStream(archivoFIS);

                while (archivoDIS.available() > 0) {
                    int id = archivoDIS.readInt();
                    String nombre = archivoDIS.readUTF();
                    double instante_entrada = archivoDIS.readDouble();
                    double duracion=  archivoDIS.readDouble();
                    Proceso proceso = new Proceso(id, nombre, instante_entrada, duracion);
                    proceso.mostrar();
                    procesos.add(proceso);
                }
                archivoDIS.close();

            }catch(FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                System.out.println("Archivo no encontrado");
            }catch(IOException ioe){
                JOptionPane.showMessageDialog(null, "Error al leer archivo");
                System.out.println("Error al leer archivo");
            }
        }
        return procesos;
    }
    
    public static boolean grabarProcesos(ArrayList<Proceso> pProcesos){
        boolean bandera = false;
         JFileChooser chooser = new JFileChooser(new File("c:\\"));
         chooser.setDialogTitle("Save File");
        ArrayList procesos = new ArrayList();
        int result = chooser.showSaveDialog(null);
        if(result == JFileChooser.APPROVE_OPTION){
            try{
                File f = chooser.getSelectedFile();
                FileWriter fw = new FileWriter(f);
                fw.close();
                FileOutputStream archiFOS = new FileOutputStream(f.getPath(), true);
                DataOutputStream archiDOS = new DataOutputStream(archiFOS);
                for(int i=0;i<pProcesos.size();i++){
                    archiDOS.writeInt(pProcesos.get(i).getId());
                    archiDOS.writeUTF(pProcesos.get(i).getNombre());
                    archiDOS.writeDouble(pProcesos.get(i).getInstante_entrada());
                    archiDOS.writeDouble(pProcesos.get(i).getDuracion());
                    }
                archiDOS.close();
                bandera = true;
            }
            catch (FileNotFoundException fnfe){
                JOptionPane.showMessageDialog(null, "Archivo no encontrado");
                System.out.println("Archivo no encontrado");
            }
            catch (IOException ioe){
                JOptionPane.showMessageDialog(null, "Error al grabar");
                System.out.println("Error al grabar");
            }
        }
        return bandera;
    }
}
