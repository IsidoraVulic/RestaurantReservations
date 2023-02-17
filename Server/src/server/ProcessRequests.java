/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import communication.Operation;
import communication.Request;
import communication.Response;
import controller.Controller;
import domain.Guest;
import domain.Reservation;
import domain.Restaurant;
import domain.Table;
import domain.User;
import exception.ServerException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.ServerForm;

/**
 *
 * @author RYZEN
 */
public class ProcessRequests extends Thread {

    private Socket socket;
    private List<ProcessRequests> clients;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    

    public ProcessRequests(Socket socket, List<ProcessRequests> clients) {
        this.socket = socket;
        this.clients = clients;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public List<ProcessRequests> getClients() {
        return clients;
    }

    public void setClients(List<ProcessRequests> clients) {
        this.clients = clients;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public void run() {
        try {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                System.out.println("Waiting...");
                Request request = (Request) in.readUnshared();
                Response response = new Response();
                ArrayList<Restaurant> restaurants;
                Restaurant restaurant;
                Table table;
                Guest guest;
                Reservation reservation;
                LocalDate date;
                LocalTime time;
                boolean uspesno;
                HashMap<String, String> criteria;
                User user;
                String pib;
                try {
                    Operation operation = request.getOperation();
                    switch (operation) {
                        case LOGIN:
                            user = (User) request.getArgument();
                            User ulogovaniAdministrator = Controller.vratiInstancu().login(user);
                            response.setResult(ulogovaniAdministrator);
                        
                            break;
                        case CREATE_RESTAURANT:
                            restaurant = (Restaurant) request.getArgument();
                            restaurant = Controller.vratiInstancu().saveRestaurant(restaurant);
                            response.setResult(restaurant);
                            break;
                        case SAVE_RESTAURANT:
                            restaurant = (Restaurant) request.getArgument();
                            restaurant = Controller.vratiInstancu().saveRestaurant(restaurant);
                            response.setResult(restaurant);
                            break;

                        case EDIT_RESTAURANT:
                            restaurant = (Restaurant) request.getArgument();
                            restaurant = Controller.vratiInstancu().editRestaurant(restaurant);
                            response.setResult(restaurant);
                            break;
                        case FIND_RESTAURANTS:
                            criteria = (HashMap<String, String>) request.getArgument();
                            restaurants = Controller.vratiInstancu().findRestaurants(criteria);
                            response.setResult(restaurants);
                            break;
                        case FIND_RESTAURANT:
                            restaurant = (Restaurant) request.getArgument();
                            response.setResult(Controller.vratiInstancu().findRestaurant(restaurant));
                            break;
                        case SAVE_TABLE:
                            table = (Table) request.getArgument();
                            response.setResult(Controller.vratiInstancu().saveTable(table));
                            break;
                        case FIND_TABLES:
                            pib = (String) request.getArgument();
                            response.setResult(Controller.vratiInstancu().findTables(pib));
                            break;
                        case DELETE_TABLE:
                            table = (Table) request.getArgument();
                            response.setResult(Controller.vratiInstancu().deleteTable(table));
                            break;
                        case CREATE_GUEST:
                            guest = Controller.vratiInstancu().createGuest();
                            response.setResult(guest);
                            break;

                        case FIND_GUESTS:
                            criteria = (HashMap<String, String>) request.getArgument();
                            ArrayList<Guest> rezultatPretrage = Controller.vratiInstancu().findGuests(criteria);
                            response.setResult(rezultatPretrage);
                            break;
                        case FIND_GUEST:
                            guest = (Guest) request.getArgument();
                            Guest completeGuest = Controller.vratiInstancu().findGuest(guest);
                            response.setResult(completeGuest);
                            break;
                        case GET_ALL_RESERVATIONS:
                            criteria = (HashMap<String, String>) request.getArgument();
                            ArrayList<Reservation> rezultat = Controller.vratiInstancu().findReservations(criteria);
                            response.setResult(rezultat);
                            break;
                        case SAVE_GUEST:
                            guest = (Guest) request.getArgument();
                            uspesno = Controller.vratiInstancu().saveGuest(guest);
                            response.setResult(uspesno);
                            break;
                        case GET_ALL_RESTAURANTS:
                            restaurants = Controller.vratiInstancu().getAllRestaurants();
                            response.setResult(restaurants);
                            break;
                        case GET_ALL_TABLES:
                            reservation = (Reservation) request.getArgument();
                            ArrayList<Table> tables = Controller.vratiInstancu().getAllTables(reservation);
                            response.setResult(tables);
                            break;
                        case GET_ALL_GUESTS:
                            ArrayList<Guest> guests = Controller.vratiInstancu().getAllGuests();
                            response.setResult(guests);
                            break;
                        case SAVE_RESERVATION:
                            Reservation r = (Reservation) request.getArgument();
                            response.setResult(Controller.vratiInstancu().saveReservation(r));
                            break;
                        case LOGOUT:
                            user = (User) request.getArgument();
                            Controller.vratiInstancu().logout(user);
                            break;
                    }
                    response.setException(null);
                } catch (ServerException ex) {
                    response.setException(ex);
                    response.setResult(null);
                } catch (SQLException ex) {
                    Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
                }
                out.writeUnshared(response);
            }
        } catch (SocketException ex) {
            try {
                System.out.println("Klijent se iskljucuje ili logout...");
                in.close();
                out.close();
                socket.close();
                clients.remove(this);
            } catch (IOException ex1) {
                Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ProcessRequests.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
