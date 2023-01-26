/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Reservation;
import exception.ServerException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author RYZEN
 */
public class SOSaveReservation extends GenericSystemOperation {

    private Reservation reservation;
    private boolean success = false;

    @Override
    protected void executeOperation() throws ServerException {
        String id = Integer.toString((int) (new Date().getTime() / 1000));
        reservation.setDate(LocalDate.now());
        reservation.setTime(LocalTime.now());
        reservation.setID(id);
        if (dbb.saveReservation(reservation) != null) {
            success = true;
        }
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
