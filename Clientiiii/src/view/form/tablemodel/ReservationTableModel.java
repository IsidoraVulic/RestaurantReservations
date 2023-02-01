/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.tablemodel;

import domain.Reservation;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

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
        Reservation r = list.get(rowIndex);
        switch(columnIndex){
            case 0: return r.getId();
            case 1: return r.getGuest().toString();
            case 2: return r.getTable();
            default: return "/";
        }
    }
    
}
