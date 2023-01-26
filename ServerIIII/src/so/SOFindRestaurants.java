/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Restaurant;
import exception.ServerException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOFindRestaurants extends GenericSystemOperation {

    private HashMap<String, String> criteria;
    private ArrayList<Restaurant> restaurants;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            restaurants = dbb.findRestaurants(criteria);
        } catch (Exception ex) {
            Logger.getLogger(SOFindRestaurants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<String, String> getCriteria() {
        return criteria;
    }

    public void setCriteria(HashMap<String, String> criteria) {
        this.criteria = criteria;
    }

    public ArrayList<Restaurant> getRestaurants() {
        return restaurants;
    }

    public void setRestaurants(ArrayList<Restaurant> restaurants) {
        this.restaurants = restaurants;
    }

}
