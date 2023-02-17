/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.tablemodel;


import domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author RYZEN
 */
public class UserTableModel extends AbstractTableModel{

    public List<User> users = new ArrayList<>();
    String[] columns = {"ID korisnika", "Ime i prezime", "Korisničko ime" , "Online"};
    
    public UserTableModel(List<User> users){
        this.users = users;
    }
    public UserTableModel(){
        
    }
    
    @Override
    public int getRowCount() {
        if(users == null)
            return 0;
        return users.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User u = users.get(rowIndex);
        switch(columnIndex){
            case 0: return u.getIDvalue();
            case 1: return u.getFirstName() + " " + u.getLastName();
            case 2: return u.getUsername();
            case 3: if(u.isOnline())
                return "da";
            else return "ne";
            default: return "/";
        }
    }

    public List<User> getUsers() {
        return users;
    }
    
    public void setList(ArrayList<User> list) {
        this.users = list;
        this.fireTableDataChanged();
    }
    
     @Override
    public String getColumnName(int i) {
        return columns[i];
    }
    
}
