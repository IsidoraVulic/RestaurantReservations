/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Guest;
import exception.ServerException;

/**
 *
 * @author RYZEN
 */
public class SOSaveGuest extends GenericSystemOperation {

    private Guest guest;
    private boolean success;

    @Override
    protected void executeOperation() throws ServerException {
        guest = (Guest) dbb.edit(guest);
        if (guest != null) {
            success = true;
        }
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
