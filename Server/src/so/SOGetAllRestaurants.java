/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.GenericEntity;
import domain.Restaurant;
import exception.ServerException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOGetAllRestaurants extends GenericSystemOperation {

    private ArrayList<Restaurant> restaurants = new ArrayList<>();

    @Override
    protected void executeOperation() throws ServerException {
        try {
            ArrayList<GenericEntity> lista = (ArrayList<GenericEntity>) dbb.getAll(new Restaurant());
            for (GenericEntity opstiDomenskiObjekat : lista) {
                restaurants.add((Restaurant) opstiDomenskiObjekat);
            }
        } catch (Exception ex) {
            Logger.getLogger(SOGetAllRestaurants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
