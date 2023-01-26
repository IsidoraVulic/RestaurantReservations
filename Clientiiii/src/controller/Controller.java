/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Communication;
import communication.Operation;
import communication.Request;
import communication.Response;
import domain.Guest;
import domain.Reservation;
import domain.Restaurant;
import domain.Table;
import domain.User;
import exception.ServerException;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import view.form.AllGuestsForm;
import view.form.ReservationForm;
import view.form.TableForm;
import view.form.tablemodel.GuestTableModel;
import view.form.tablemodel.RestaurantTableModel;
import view.form.tablemodel.TableTableModel;

/**
 *
 * @author RYZEN
 */
public class Controller {

    public static Controller instance;

    private Controller() {

    }

    public static Controller getInstace() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    protected Object sendRequest(Operation operation, Object param) throws Exception {
        Request request = new Request();
        request.setOperation(operation);
        request.setArgument(param);
        Communication.getInstance().sendRequest(request);
        Response response = Communication.getInstance().readResponse();
        if (response.getException() == null) {
            return response.getResult();
        } else {
            Exception ex = response.getException();
            throw ex;
        }
    }

    public HashMap<String, String> login(String username, String password) throws ServerException, Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        User loggedUser = (User) sendRequest(Operation.LOGIN, user);
        if (loggedUser.getId() != null) {
            HashMap<String, String> loggedUserHash = new HashMap<>();
            loggedUserHash.put("UserID", loggedUser.getIDvalue());
            loggedUserHash.put("FirstName", loggedUser.getFirstName());
            loggedUserHash.put("LastName", loggedUser.getLastName());
            loggedUserHash.put("Username", loggedUser.getUsername());
            return loggedUserHash;
        }
        return null;
    }

    public HashMap<String, String> createObject(String objectName) throws Exception {
        switch (objectName) {
            case "restaurant":
                Restaurant restaurant = (Restaurant) sendRequest(Operation.CREATE_RESTAURANT, null);
                HashMap<String, String> hashMapRestaurant = new HashMap<>();
                hashMapRestaurant.put("pib", restaurant.getID());
                hashMapRestaurant.put("name", restaurant.getName());
                hashMapRestaurant.put("address", restaurant.getAddress());
                return hashMapRestaurant;
            case "guest":
                Guest newGuest = (Guest) sendRequest(Operation.CREATE_GUEST, null);
                HashMap<String, String> hashMapGuest = new HashMap<>();
                hashMapGuest.put("id", newGuest.getIDvalue());
                return hashMapGuest;
            default:
                throw new Exception("Pokušavate da kreirate nepoznat objekta.");
        }
    }

    public boolean saveObject(HashMap<String, String> objectToSave) throws Exception {
        switch (objectToSave.get("type")) {
            case "restaurant":
                Restaurant resturant = new Restaurant();
                resturant.setName(objectToSave.get("name"));
                resturant.setID(objectToSave.get("pib"));
                resturant.setAddress(objectToSave.get("address"));
                if (sendRequest(Operation.SAVE_RESERVATION, resturant) != null) {
                    return true;
                }
                break;
            case "table":
                Table table = new Table();
                table.setPib(objectToSave.get("pib"));
                if (sendRequest(Operation.SAVE_TABLE, table) != null) {
                    return true;
                }
                break;
            default:
                throw new Exception("Pokušavate da zapamtite nepoznat objekta.");
        }
        return false;
    }

    public void findRestaurants(HashMap criteria, TableForm form) throws Exception {
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) sendRequest(Operation.FIND_RESTAURANTS, criteria);
        RestaurantTableModel tm = new RestaurantTableModel();
        tm.setList(restaurants);
        form.getRestaurantTableModel().setList(restaurants);
        form.getTblRestaurants().setModel(tm);
    }

    public HashMap<String, String> findRestaurant(String pib) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setID(pib);
        restaurant = (Restaurant) sendRequest(Operation.FIND_RESTAURANT, restaurant);
        HashMap<String, String> hashMapRestaurant = new HashMap<>();
        hashMapRestaurant.put("pib", restaurant.getIDvalue());
        hashMapRestaurant.put("name", restaurant.getName());
        hashMapRestaurant.put("address", restaurant.getAddress());
        return hashMapRestaurant;
    }

    public boolean findTables(String pib, TableForm form) throws Exception {
        ArrayList<Table> tables = (ArrayList<Table>) sendRequest(Operation.FIND_TABLES, pib);
        if (tables != null) {
            TableTableModel tm = new TableTableModel();
            tm.setList(tables);
            form.setTableTableModel(tm);
            form.getTblTables().setModel(tm);
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteTable(String tableID) throws Exception {
        Table table = new Table();
        table.setId(tableID);
        return (boolean) sendRequest(Operation.DELETE_TABLE, table);
    }

    public boolean addGuest(HashMap<String, String> guest) throws Exception {
        Guest g = new Guest();
        g.setID(guest.get("id"));
        g.setFirstname(guest.get("firstname"));
        g.setLastname(guest.get("lastname"));
        g.setContact(guest.get("contact"));
        g.setEmail(guest.get("email"));
        return (boolean) sendRequest(Operation.SAVE_GUEST, g);
    }

    public void findGuests(HashMap<String, String> criteria, AllGuestsForm form) throws Exception {
        ArrayList<Guest> guests = (ArrayList<Guest>) sendRequest(Operation.FIND_GUESTS, criteria);
        GuestTableModel tm = new GuestTableModel();
        tm.setList(guests);
        form.getTblGuests().setModel(tm);
        form.setGuestTableModel(tm);
    }

    public boolean saveGuest(HashMap<String, String> data) throws Exception {
        Guest guest = new Guest();
        guest.setID(data.get("id"));
        guest.setEmail(data.get("email"));
        guest.setFirstname(data.get("firstname"));
        guest.setLastname(data.get("lastname"));
        guest.setContact(data.get("contact"));
        return (boolean) sendRequest(Operation.SAVE_GUEST, guest);
    }
    
    public HashMap<String, String> findGuest(String guestID) throws Exception {
        HashMap<String, String> hashMapGuest = new HashMap<>();
        Guest guest = new Guest();
        guest.setID(guestID);
        guest = (Guest) sendRequest(Operation.FIND_GUEST, guest);
        hashMapGuest.put("id", guest.getIDvalue());
        hashMapGuest.put("firstname", guest.getFirstname());
        hashMapGuest.put("lastname", guest.getLastname());
        hashMapGuest.put("contact", guest.getContact());
        hashMapGuest.put("email", guest.getEmail());
        return hashMapGuest;
    }
    
    public ArrayList<Restaurant> getAllRestaurants() throws Exception {
        return (ArrayList<Restaurant>) sendRequest(Operation.GET_ALL_RESTAURANTS, null);
    }

    public ArrayList<Table> getAllTables() throws Exception {
        return (ArrayList<Table>) sendRequest(Operation.GET_ALL_TABLES, null);
    }

    public ArrayList<Guest> getAllGuests() throws Exception {
        return (ArrayList<Guest>) sendRequest(Operation.GET_ALL_GUESTS, null);
    }
    
    public boolean createReservation(HashMap<String, String> data) throws Exception {
        Table t = new Table();
        t.setID(data.get("tableID"));
        Guest g = new Guest();
        g.setID(data.get("guestID"));
        User u = new User();
        u.setID(data.get("userID"));
        Reservation r = new Reservation(null, g, t, u, null, null, null);
        return (boolean) sendRequest(Operation.SAVE_RESERVATION, r);
    }
    
//    public void findTablesByGuest(String guestID, Form efv) throws Exception {
//        
//    }
    
    public void quit(User loggedUser) throws Exception {
        sendRequest(Operation.LOGOUT, loggedUser);
    }
    
    public void getAll(ReservationForm form) throws Exception {
        ArrayList<Restaurant> restaurants = Controller.getInstace().getAllRestaurants();
        ArrayList<Table> tables = Controller.getInstace().getAllTables();
        ArrayList<Guest> guests = Controller.getInstace().getAllGuests();

        GuestTableModel gtm = new GuestTableModel();
        gtm.setList(guests);
        form.getTblGuests().setModel(gtm);
        form.setGtm(gtm);

        RestaurantTableModel rtm = new RestaurantTableModel();
        rtm.setList(restaurants);
        form.getTblRestaurants().setModel(rtm);
        form.setRtm(rtm);

        TableTableModel ttm = new TableTableModel();
        ttm.setList(tables);
        form.getTblTables().setModel(ttm);
        form.setTtm(ttm);

    }
    
     public void setTables(String pib, ReservationForm form) throws Exception {
        ArrayList<Table> tables = (ArrayList<Table>) sendRequest(Operation.FIND_TABLES, pib);
        TableTableModel ttm = new TableTableModel();
        ttm.setList(tables);
        form.setTtm(ttm);
        form.getTblTables().setModel(ttm);
    }
     
      public User userHashToUserObject(HashMap<String, String> userHash) {
        User user = new User();
        user.setID(userHash.get("UserID"));
        user.setUsername(userHash.get("Username"));
        user.setFirstName(userHash.get("FirstName"));
        user.setLastName(userHash.get("LastName"));
        return user;
    }
      
      public void connect() throws IOException {
        if (Communication.getInstance().getSocket() == null) {
            Socket socket = new Socket("localhost", 9000);
            Communication.getInstance().setSocket(socket);
            System.out.println("Klijent je povezan na server.");
        }
    }
}
