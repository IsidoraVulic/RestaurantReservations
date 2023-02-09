/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Restaurant;
import exception.ServerException;

/**
 *
 * @author RYZEN
 */
public class SOSaveRestaurant extends GenericSystemOperation {

    private Restaurant restaurant;

    @Override
    protected void executeOperation() throws ServerException {
        restaurant = (Restaurant) dbb.save(restaurant);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
