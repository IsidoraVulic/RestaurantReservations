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

    @Override
    protected void executeOperation() throws ServerException {
        restaurant = new Restaurant();
        restaurant.setName("Unesite naziv restorana");
        restaurant.setAddress("Unesite adresu restorana");
        restaurant.setId("Unesite PIB restorana");
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

}
