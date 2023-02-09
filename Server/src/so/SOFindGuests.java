/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Guest;
import exception.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindGuests extends GenericSystemOperation {

    private HashMap<String, String> criteria;
    private ArrayList<Guest> guests;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            guests = dbb.findGuests(criteria);
        } catch (Exception ex) {
            Logger.getLogger(SOFindGuests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(HashMap<String, String> criteria) {
        this.criteria = criteria;
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }

}
