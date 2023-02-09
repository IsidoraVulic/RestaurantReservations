/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.tablemodel;

import domain.Reservation;
import domain.Restaurant;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class ReservationTableModel extends AbstractTableModel {

    private ArrayList<Reservation> list = new ArrayList<>();
    String[] columns = {"ID", "Gost", "Restoran", "Sto", "Datum", "Vreme", "Napomena"};
    
    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        try {
            Reservation r = list.get(rowIndex);
            Restaurant restaurant = Controller.getInstace().resHashToResObject(Controller.getInstace().findRestaurant(r.getTable().getPib()));
            System.out.println(r.getGuest());
            switch(columnIndex){
                case 0: return r.getId();
                case 1: return r.getGuest().toString();
                case 2: return restaurant.getName();
                case 3: return r.getTable().getIDvalue();
                case 4: return r.getDate();
                case 5: return r.getTime();
                case 6: return r.getNote();
                default: return "/";
            }
        } catch (Exception ex) {
            Logger.getLogger(ReservationTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "/";
    }
    
    @Override
    public String getColumnName(int i) {
        return columns[i];
    }
    
    public void setList(ArrayList<Reservation> list) {
        this.list = list;
        this.fireTableDataChanged();
    }
    
    public void addToList(Reservation r){
        this.list.add(r);
        this.fireTableDataChanged();
    }
    
    public ArrayList<Reservation> getList() {
        return list;
    }
    
}
