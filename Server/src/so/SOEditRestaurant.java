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
public class SOEditRestaurant extends GenericSystemOperation {

    private Restaurant params;
    private Restaurant restaurant;

    @Override
    protected void executeOperation() throws ServerException {
        restaurant = (Restaurant) dbb.edit(params);
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
