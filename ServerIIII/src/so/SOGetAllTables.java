/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Table;
import exception.ServerException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOGetAllTables extends GenericSystemOperation {

    private ArrayList<Table> tables = new ArrayList<>();
    private String PIB;
    private LocalDate date;
    private LocalTime time;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            tables = dbb.getAllTables(PIB, date, time);
        } catch (Exception ex) {
            Logger.getLogger(SOGetAllTables.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Table> getTables() {
        return tables;
    }

    public void setTables(ArrayList<Table> tables) {
        this.tables = tables;
    }

    public String getPIB() {
        return PIB;
    }

    public void setPIB(String PIB) {
        this.PIB = PIB;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

}
