/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Restaurant;
import exception.ServerException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindRestaurant extends GenericSystemOperation {

    private Restaurant params;
    private Restaurant restaurant;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            restaurant = (Restaurant) dbb.getByID(params, params.getID());
        } catch (Exception ex) {
            Logger.getLogger(SOFindRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Restaurant getParams() {
        return params;
    }

    public void setParams(Restaurant params) {
        this.params = params;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
