/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.tablemodel;

import domain.Restaurant;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RYZEN
 */
public class RestaurantTableModel extends AbstractTableModel {

    private ArrayList<Restaurant> list = new ArrayList<>();
    String[] columns = {"PIB", "Naziv", "Adresa"};

    public RestaurantTableModel(){
        
    }
    
    @Override
    public int getRowCount() {
        if(list==null)
            return 0;
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Restaurant r = list.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getId();
            case 1: return r.getName();
            case 2: return r.getAddress();
            default: return "/";
        }
    }

    @Override
    public String getColumnName(int i) {
        return columns[i];
    }

    public void setList(ArrayList<Restaurant> list) {
        this.list = list;
        this.fireTableDataChanged();
    }
    
    public void addToList(Restaurant r){
        this.list.add(r);
        this.fireTableDataChanged();
    }
    
    public ArrayList<Restaurant> getList() {
        return list;
    }
}
