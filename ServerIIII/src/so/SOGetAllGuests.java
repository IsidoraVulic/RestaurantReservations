/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.GenericEntity;
import domain.Guest;
import exception.ServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOGetAllGuests extends GenericSystemOperation{

    private ArrayList<Guest> guests = new ArrayList<>();
    
    @Override
    protected void executeOperation() throws ServerException {
        try {
            ArrayList<GenericEntity> lista = (ArrayList<GenericEntity>) dbb.getAll(new Guest());
            for (GenericEntity opstiDomenskiObjekat : lista) {
                guests.add((Guest) opstiDomenskiObjekat);
            }
        } catch (Exception ex) {
            Logger.getLogger(SOGetAllGuests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Guest> getGuests() {
        return guests;
    }

    public void setGuests(ArrayList<Guest> guests) {
        this.guests = guests;
    }
    
    
}
