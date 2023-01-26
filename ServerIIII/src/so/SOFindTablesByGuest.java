/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Guest;
import domain.Table;
import exception.ServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindTablesByGuest extends GenericSystemOperation {

    private Guest guest;
    private ArrayList<Table> tables;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            tables = dbb.findTakenTables(guest);
        } catch (Exception ex) {
            Logger.getLogger(SOFindTablesByGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

}
