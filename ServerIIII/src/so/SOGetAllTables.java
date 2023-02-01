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
            System.out.println(reservation.toString() + "SO");
           this.tables = dbb.getAllTables(reservation);
            System.out.println(tables.get(0) + "SO");
        } catch (Exception ex) {
            ex.printStackTrace();
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
