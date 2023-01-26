/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Guest;
import exception.ServerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindGuest extends GenericSystemOperation {

    private Guest guest;
    private Guest completeGuest;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            completeGuest = (Guest) dbb.getByID(guest, guest.getID());
        } catch (Exception ex) {
            Logger.getLogger(SOFindGuest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Guest getCompleteGuest() {
        return completeGuest;
    }

    public void setCompleteGuest(Guest completeGuest) {
        this.completeGuest = completeGuest;
    }

}
