/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Table;
import exception.ServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindTables extends GenericSystemOperation {

    private String PIB;
    private ArrayList<Table> tables;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            tables = dbb.findTables(PIB);
        } catch (Exception ex) {
            Logger.getLogger(SOFindTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

}
