/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Augusto
 */
public class ProcesoTableModel extends AbstractTableModel{
    
    private String[] columnNames = {"[ ]","Proceso","Instante Entrada", "Tiempo Ejecucion", "Tiempo Restante"}; 
    private ArrayList<Proceso> listProcess;
    private final Class[] columnClass = new Class[] {
        Boolean.class, String.class, Double.class, Double.class, Double.class
    };
     public ProcesoTableModel(ArrayList<Proceso> listProcess) { 
      this.listProcess = listProcess; 
   } 
     
    @Override
    public int getRowCount() {
        int size; 
      if (getListProcess() == null) { 
         size = 0; 
      } 
      else { 
         size = getListProcess().size(); 
      } 
      return size; 
    }

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }
    
    @Override
    public int getColumnCount() {
       return columnNames.length;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Object temp = null; 
      if (col == 0) { 
        temp = new Boolean(getListProcess().get(row).isSeleccionado()); 
      } else if (col == 1) { 
         temp = getListProcess().get(row).getNombre(); 
      }  else if (col == 2) { 
         temp = new Double(getListProcess().get(row).getInstante_entrada()); 
      } else if (col == 3) { 
         temp = new Double(getListProcess().get(row).getDuracion()); 
      } 
      else if (col == 4) { 
         temp = new Double(getListProcess().get(row).getDuracionRestante()); 
      } 
      return temp; 
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Proceso row = getListProcess().get(rowIndex);
       if(0 == columnIndex) {
           row.setSeleccionado((boolean) aValue);
       }
       else if(1 == columnIndex) {
           row.setNombre((String) aValue);
       }
       else if(2 == columnIndex) {
           row.setInstante_entrada((double) aValue);
       }
       else if(3 == columnIndex) {
           row.setDuracion((double) aValue);
       }
       else if(3 == columnIndex) {
           row.setDuracionRestante((double) aValue);
       } 
   }
   
   @Override
public boolean isCellEditable(int rowIndex, int columnIndex)
{
    return true;
}

@Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return columnClass[columnIndex];
    }
    
    public void addRow(Proceso rowData)
    {
        getListProcess().add(rowData);
        fireTableRowsInserted(getListProcess().size() - 1, getListProcess().size() - 1);
    }
    public void removeRow(int index){
        getListProcess().remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public ArrayList<Proceso> getSelectedRows(){
        ArrayList<Proceso> listado = new ArrayList<Proceso>();
        for (Proceso p : this.getListProcess()) {
            if(p.isSeleccionado()) listado.add(p);
        }
        return listado;
    }
    
    public Proceso getProceso(int index){
        return this.getListProcess().get(index);
    }

    public boolean esElMasTemprano(Proceso process) {
        Proceso procesoMinimo = new Proceso();
        double min = 1000;
        int indice = 0;
        for (Proceso p : this.getListProcess()) {
            if (min < p.getInstante_entrada()){
                min = p.getInstante_entrada();
                procesoMinimo = p;
            }else if(min == p.getInstante_entrada()){
                indice = (this.getListProcess().indexOf(p) < this.getListProcess().indexOf(procesoMinimo))?this.getListProcess().indexOf(p) : this.getListProcess().indexOf(procesoMinimo);
            }
        }
        if(process.getNombre().equals(procesoMinimo.getNombre())) return true;
        return false;
    }
    
    public int procesoMenorTiempoEjecucion(){
        double min = Double.MAX_VALUE;
        int i=0, indice=0;
        for (Proceso procesoAux : this.getListProcess()) {
            if(procesoAux.getDuracion()<min){
                min = procesoAux.getDuracion();
                indice=i;
            }
            i++;
        }
        return indice;
        
    }
    
    public int procesoMenorTiempoRestante(){
        double min = Double.MAX_VALUE;
        int i=0, indice=0;
        for (Proceso procesoAux : this.getListProcess()) {
            if(procesoAux.getDuracionRestante()<min){
                min = procesoAux.getDuracion();
                indice=i;
            }
            i++;
        }
        return indice; 
    }

    /**
     * @return the listProcess
     */
    public ArrayList<Proceso> getListProcess() {
        return listProcess;
    }

    /**
     * @param listProcess the listProcess to set
     */
    public void setListProcess(ArrayList<Proceso> listProcess) {
        this.listProcess = listProcess;
    }
}
