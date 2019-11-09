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
    
    private String[] columnNames = {"[ ]","Proceso","Instante Entrada", "Tiempo Ejecucion"}; 
    private ArrayList<Proceso> listProcess;
    private final Class[] columnClass = new Class[] {
        Boolean.class, String.class, Double.class, Double.class
    };
     public ProcesoTableModel(ArrayList<Proceso> listProcess) { 
      this.listProcess = listProcess; 
   } 
     
    @Override
    public int getRowCount() {
        int size; 
      if (listProcess == null) { 
         size = 0; 
      } 
      else { 
         size = listProcess.size(); 
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
        temp = new Boolean(listProcess.get(row).isSeleccionado()); 
      } else if (col == 1) { 
         temp = listProcess.get(row).getNombre(); 
      }  else if (col == 2) { 
         temp = new Double(listProcess.get(row).getInstante_entrada()); 
      } else if (col == 3) { 
         temp = new Double(listProcess.get(row).getDuracion()); 
      } 
      return temp; 
    }
    
    @Override
   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
   {
       Proceso row = listProcess.get(rowIndex);
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
        listProcess.add(rowData);
        fireTableRowsInserted(listProcess.size() - 1, listProcess.size() - 1);
    }
    public void removeRow(int index){
        listProcess.remove(index);
        fireTableRowsDeleted(index, index);
    }
    
    public ArrayList<Proceso> getSelectedRows(){
        ArrayList<Proceso> listado = new ArrayList<Proceso>();
        for (Proceso p : this.listProcess) {
            if(p.isSeleccionado()) listado.add(p);
        }
        return listado;
    }
    
    public Proceso getProceso(int index){
        return this.listProcess.get(index);
    }

    public boolean esElMasTemprano(Proceso process) {
        Proceso procesoMinimo = new Proceso();
        double min = 1000;
        int indice = 0;
        for (Proceso p : this.listProcess) {
            if (min < p.getInstante_entrada()){
                min = p.getInstante_entrada();
                procesoMinimo = p;
            }else if(min == p.getInstante_entrada()){
                indice = (this.listProcess.indexOf(p) < this.listProcess.indexOf(procesoMinimo))?this.listProcess.indexOf(p) : this.listProcess.indexOf(procesoMinimo);
            }
        }
        if(process.getNombre().equals(procesoMinimo.getNombre())) return true;
        return false;
    }
}
