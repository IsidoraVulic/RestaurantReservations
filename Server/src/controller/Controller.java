/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domain.Guest;
import domain.Reservation;
import domain.Restaurant;
import domain.Table;
import domain.User;
import exception.ServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import so.SOCreateGuest;
import so.SOCreateRestaurant;
import so.SODeleteTable;
import so.SOEditRestaurant;
import so.SOFindGuest;
import so.SOFindGuests;
import so.SOFindReservations;
import so.SOFindRestaurant;
import so.SOFindRestaurants;
import so.SOFindTables;
import so.SOGetAllGuests;
import so.SOGetAllRestaurants;
import so.SOGetAllTables;
import so.SOLogin;
import so.SOSaveGuest;
import so.SOSaveReservation;
import so.SOSaveRestaurant;
import so.SOSaveTable;
import so.help.SOCreateUser;
import so.help.SOGetAllUsers;
import so.help.SOLogout;

/**
 *
 * @author RYZEN
 */
public class Controller {
    private static Controller instanca;
    private List<User> listaKorisnika;

    private Controller() {
    }

    public static Controller vratiInstancu() {
        if (instanca == null) {
            instanca = new Controller();
        }
        return instanca;
    }

    public User login(User administrator) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOLogin sopa = new SOLogin();
        sopa.setParams(administrator);
        sopa.execute();
        return sopa.getUser();
    }

    public Restaurant createRestaurant() throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOCreateRestaurant soknk = new SOCreateRestaurant();
        soknk.execute();
        return soknk.getRestaurant();
    }

    public Restaurant saveRestaurant(Restaurant restaurant) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOCreateRestaurant sozk = new SOCreateRestaurant();
        sozk.setParams(restaurant);
        sozk.execute();
        return sozk.getRestaurant();
    }

    public ArrayList<Restaurant> findRestaurants(HashMap<String, String> kriterijum) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindRestaurants sonk = new SOFindRestaurants();
        sonk.setCriteria(kriterijum);
        sonk.execute();
        return sonk.getRestaurants();
    }

    public Restaurant findRestaurant(Restaurant restaurant) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindRestaurant sonk = new SOFindRestaurant();
        sonk.setParams(restaurant);
        sonk.execute();
        return sonk.getRestaurant();
    }
    
    public Restaurant editRestaurant(Restaurant restaurant) throws ServerException, IOException, SQLException, ClassNotFoundException{
        SOEditRestaurant soer = new SOEditRestaurant();
        soer.setParams(restaurant);
        soer.execute();
        return soer.getRestaurant();
    }

    public Table saveTable(Table table) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOSaveTable sozp = new SOSaveTable();
        sozp.setTable(table);
        sozp.execute();
        return sozp.getTable();
    }

    public ArrayList<Table> findTables(String pib) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindTables sonp = new SOFindTables();
        sonp.setPIB(pib);
        sonp.execute();
        return sonp.getTables();
    }

    public boolean deleteTable(Table table) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SODeleteTable soip = new SODeleteTable();
        soip.setTable(table);
        soip.execute();
        return soip.isSuccess();
    }

    public Guest createGuest() throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOCreateGuest sokc = new SOCreateGuest();
        sokc.execute();
        return sokc.getGuest();
    }
    
    public ArrayList<Reservation> findReservations(HashMap<String, String> kriterijum) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindReservations findReservations = new SOFindReservations();
        findReservations.setCriteria(kriterijum);
        findReservations.execute();
        return findReservations.getReservations();
    }



    public ArrayList<Guest> findGuests(HashMap<String, String> kriterijum) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindGuests sonc = new SOFindGuests();
        sonc.setCriteria(kriterijum);
        sonc.execute();
        return sonc.getGuests();
    }

    public Guest findGuest(Guest guest) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOFindGuest sonc = new SOFindGuest();
        sonc.setGuest(guest);
        sonc.execute();
        return sonc.getCompleteGuest();
    }

    public boolean saveGuest(Guest guest) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOSaveGuest sozc = new SOSaveGuest();
        sozc.setGuest(guest);
        sozc.execute();
        return sozc.isSuccess();
    }

    public ArrayList<Restaurant> getAllRestaurants() throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOGetAllRestaurants soulk = new SOGetAllRestaurants();
        soulk.execute();
        return soulk.getRestaurants();
    }

    public ArrayList<Table> getAllTables(Reservation reservation) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOGetAllTables sogat = new SOGetAllTables();
        sogat.setReservation(reservation);
        sogat.execute();
        return sogat.getTables();
    }

    public ArrayList<Guest> getAllGuests() throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOGetAllGuests soulc = new SOGetAllGuests();
        soulc.execute();
        return soulc.getGuests();
    }

    public boolean saveReservation(Reservation r) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOSaveReservation sozi = new SOSaveReservation();
        sozi.setParams(r);
        sozi.execute();
        return sozi.isSuccess();
    }



    public void logout(User user) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOLogout sois = new SOLogout();
        sois.setUser(user);
        sois.execute();
    }

    // pomoćne
    
    public List<User> getListaKorisnika() throws ServerException, IOException, SQLException, ClassNotFoundException {
        if (listaKorisnika == null) {
            listaKorisnika = getAllUsers();
        }
        return listaKorisnika;
    }

    private List<User> getAllUsers() throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOGetAllUsers souk = new SOGetAllUsers();
        souk.execute();
        return souk.getListaKorisnika();
    }

    public void createUser(User a) throws ServerException, IOException, SQLException, ClassNotFoundException {
        SOCreateUser ka = new SOCreateUser();
        ka.setUser(a);
        ka.execute();
        System.out.println("Kreiran je novi administrator");
    }
}
