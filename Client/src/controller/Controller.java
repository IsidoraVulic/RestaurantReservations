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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import view.form.AllGuestsForm;
import view.form.AllReservationsForm;
import view.form.AllRestaurantsForm;
import view.form.ReservationForm;
import view.form.TableForm;
import view.form.tablemodel.GuestTableModel;
import view.form.tablemodel.ReservationTableModel;
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

    public User login(String username, String password) throws ServerException, Exception {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        Request request = new Request(Operation.LOGIN, user);
        Communication.getInstance().sendRequest(request);
        Response response = (Response) Communication.getInstance().readResponse();
        if (response.getException() == null) {
            return (User) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public HashMap<String, String> createObject(String objectName) throws Exception {
        switch (objectName) {
            case "restaurant":
                Restaurant restaurant = (Restaurant) sendRequest(Operation.CREATE_RESTAURANT, null);
                HashMap<String, String> hashMapRestaurant = new HashMap<>();
                hashMapRestaurant.put("pib", restaurant.getId());
                hashMapRestaurant.put("name", restaurant.getName());
                hashMapRestaurant.put("address", restaurant.getAddress());
                return hashMapRestaurant;
            case "guest":
                Guest newGuest = (Guest) sendRequest(Operation.CREATE_GUEST, null);
                HashMap<String, String> hashMapGuest = new HashMap<>();
                hashMapGuest.put("id", newGuest.getIDvalue());
                return hashMapGuest;
            default:
                throw new Exception("Pokušavate da kreirate nepoznat objekat.");
        }
    }

    public boolean saveObject(HashMap<String, String> objectToSave) throws Exception {
        switch (objectToSave.get("type")) {
            case "restaurant":
                Restaurant resturant = new Restaurant();
                resturant.setName(objectToSave.get("name"));
                resturant.setId(objectToSave.get("pib"));
                resturant.setAddress(objectToSave.get("address"));
                if (sendRequest(Operation.SAVE_RESTAURANT, resturant) != null) {
                    return true;
                }
                break;
            case "table":
                Table table = new Table();
                table.setPib(objectToSave.get("pib"));
                table.setChairs(Integer.parseInt(objectToSave.get("chairs")));
                if (sendRequest(Operation.SAVE_TABLE, table) != null) {
                    return true;
                }
                break;
            default:
                throw new Exception("Pokušavate da zapamtite nepoznat objekat.");
        }
        return false;
    }

    public boolean editRestaurant(HashMap<String, String> objectToEdit) throws Exception {
        Restaurant resturant = new Restaurant();
        resturant.setName(objectToEdit.get("name"));
        resturant.setId(objectToEdit.get("pib"));
        resturant.setAddress(objectToEdit.get("address"));
        if (sendRequest(Operation.EDIT_RESTAURANT, resturant) != null) {
            return true;
        }
        return false;
    }

    public void findRestaurants(HashMap criteria, AllRestaurantsForm form) throws Exception {
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) sendRequest(Operation.FIND_RESTAURANTS, criteria);
        RestaurantTableModel tm = new RestaurantTableModel();
        tm.setList(restaurants);
        form.getRtm().setList(restaurants);
        form.getTblRestaurants().setModel(tm);
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
        restaurant.setId(pib);
        restaurant = (Restaurant) sendRequest(Operation.FIND_RESTAURANT, restaurant);
        HashMap<String, String> hashMapRestaurant = new HashMap<>();
        hashMapRestaurant.put("pib", restaurant.getId());
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
    
    public void findGuests(HashMap<String, String> criteria, ReservationForm form) throws Exception {
        ArrayList<Guest> guests = (ArrayList<Guest>) sendRequest(Operation.FIND_GUESTS, criteria);
        GuestTableModel tm = new GuestTableModel();
        tm.setList(guests);
        form.getTblGuests().setModel(tm);
        form.setGtm(tm);
    }
    
    public void findReservations(HashMap<String, String> criteria, AllReservationsForm form) throws Exception {
        ArrayList<Reservation> reservations = (ArrayList<Reservation>) sendRequest(Operation.GET_ALL_RESERVATIONS, criteria);
        ReservationTableModel rtm = new ReservationTableModel();
        rtm.setList(reservations);
        form.getTblReservations().setModel(rtm);
        form.setRtm(rtm);
    }

    public boolean saveGuest(HashMap<String, String> data) throws Exception {
        Guest guest = new Guest();
        guest.setId(data.get("id"));
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
        hashMapGuest.put("id", guest.getId());
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
        t.setId(data.get("tableID"));
        Guest g = new Guest();
        g.setId(data.get("guestID"));
        User u = new User();
        u.setId(data.get("userID"));
        Reservation r = new Reservation(null, g, t, u, LocalDate.parse(data.get("date")), LocalTime.parse(data.get("time")), data.get("note"));
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
        ArrayList<Guest> guests = Controller.getInstace().getAllGuests();

        GuestTableModel gtm = new GuestTableModel();
        gtm.setList(guests);
        form.getTblGuests().setModel(gtm);
        form.setGtm(gtm);

        RestaurantTableModel rtm = new RestaurantTableModel();
        rtm.setList(restaurants);
        form.getTblRestaurants().setModel(rtm);
        form.setRtm(rtm);

    }

    public void setTables(Reservation reservation, ReservationForm form) throws Exception {

        ArrayList<Table> tables = (ArrayList<Table>) sendRequest(Operation.GET_ALL_TABLES, reservation);

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
    
    public Restaurant resHashToResObject(HashMap<String, String> resHash) {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(resHash.get("pib"));
        restaurant.setName(resHash.get("name"));
        restaurant.setAddress(resHash.get("address"));
        return restaurant;
    }

    public void connect() throws IOException {
        if (Communication.getInstance().getSocket() == null) {
            Socket socket = new Socket("localhost", 9000);
            Communication.getInstance().setSocket(socket);
            System.out.println("Klijent je povezan na server.");
        }
    }
}
