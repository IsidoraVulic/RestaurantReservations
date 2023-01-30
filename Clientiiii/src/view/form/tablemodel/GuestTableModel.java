/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.form.tablemodel;

import domain.Guest;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RYZEN
 */
public class GuestTableModel extends AbstractTableModel {

    private ArrayList<Guest> list = new ArrayList<>();
    String[] columns = {"Šifra gosta", "Ime i prezime", "Telefon"};

    public GuestTableModel() {

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
        Guest g = list.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return g.getIDvalue();
            case 1:
                return g.getFirstname() + " " + g.getLastname();
            case 2:
                return g.getContact();
            default:
                return " ";
        }
    }

    public ArrayList<Guest> getList() {
        return list;
    }

    public void setList(ArrayList<Guest> list) {
        this.list = list;
        this.fireTableDataChanged();
    }
    
     @Override
    public String getColumnName(int i) {
        return columns[i];
    }

}
