/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Guest;
import exception.ServerException;
import java.util.Date;

/**
 *
 * @author RYZEN
 */
public class SOCreateGuest extends GenericSystemOperation {

    private Guest guest;

    @Override
    protected void executeOperation() throws ServerException {
        String id = Integer.toString(((int) (new Date().getTime()/1000)));
        guest = new Guest();
        guest.setId(id);
        dbb.save(guest);
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

}
