/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Reservation;
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
    private Reservation reservation;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            tables = dbb.getAllTables(reservation);
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

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

   
}
