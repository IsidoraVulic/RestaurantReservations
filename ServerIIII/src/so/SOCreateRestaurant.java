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
public class SOCreateRestaurant extends GenericSystemOperation {

    private Restaurant restaurant;
    private Restaurant params;

    @Override
    protected void executeOperation() throws ServerException {
        restaurant = new Restaurant();
        restaurant.setName(params.getName());
        restaurant.setAddress(params.getAddress());
        restaurant.setId(params.getId());
        dbb.save(restaurant);
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public Restaurant getParams() {
        return params;
    }

    public void setParams(Restaurant params) {
        this.params = params;
    }
    
    

}
