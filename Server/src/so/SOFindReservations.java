/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Reservation;
import exception.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindReservations extends GenericSystemOperation {

    private HashMap<String, String> criteria;
    private ArrayList<Reservation> reservations;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            reservations = dbb.findReservations(criteria);
        } catch (Exception ex) {
            Logger.getLogger(SOFindReservations.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(HashMap<String, String> criteria) {
        this.criteria = criteria;
    }

    public ArrayList<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(ArrayList<Reservation> reservations) {
        this.reservations = reservations;
    }

}
