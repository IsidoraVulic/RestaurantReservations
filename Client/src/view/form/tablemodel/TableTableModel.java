/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.tablemodel;

import domain.Table;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RYZEN
 */
public class TableTableModel extends AbstractTableModel {

    private ArrayList<Table> list = new ArrayList<>();
    String[] columns = {"ID", "PIB restorana", "Broj stolica"};

    public TableTableModel(){
        
    }
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Table t = list.get(rowIndex);
        switch(columnIndex){
            case 0: return t.getId();
            case 1: return t.getPib();
            case 2: return t.getChairs();
            default: return "/";
        }
    }

    public ArrayList<Table> getList() {
        return list;
    }

    public void setList(ArrayList<Table> list) {
        this.list = list;
    }
    
     @Override
    public String getColumnName(int i) {
        return columns[i];
    }

}
