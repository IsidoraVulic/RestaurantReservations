/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;
import domain.GenericEntity;
import domain.Guest;
import domain.Reservation;
import domain.Restaurant;
import domain.Table;
import domain.User;
import java.sql.Connection;
import exception.ServerException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import security.HashFunction;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author RYZEN
 */
public class DBBroker {
    
    private Connection connection;
    
    public DBBroker(){
        
    }
    
    public void connect() throws ServerException, IOException, SQLException{
    try {
            Class.forName(Util.getInstance().getDriver());
            String url = Util.getInstance().getURL();
            String user = Util.getInstance().getUser();
            String password = Util.getInstance().getPassword();
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

        } catch (IOException ex) {
            throw new ServerException("Greska kod citanja iz properties fajla!");
        } catch (ClassNotFoundException ex) {
            throw new ServerException("Drajver nije pronadjen!");
        } catch (SQLException ex) {
            throw new ServerException("Konekcija na bazu neuspesna!");
        }
    }
    
    public void disconnect() throws ServerException{
         try {
            connection.close();
        } catch (SQLException ex) {
            throw new ServerException("Raskidanje konekcije neuspesno!");
        }
    }
    
    public void commit() throws ServerException{
        try{
            connection.commit();
        }catch (SQLException ex) {
            throw new ServerException("Transakcija nije uspesno potvrdjena");
        }
    }
    
    public void rollback() throws ServerException{
        try{
            connection.rollback();
        }catch (SQLException ex) {
            throw new ServerException("Transakcija nije uspesno ponistena");
        }
    }
    
    public User login(User user) throws ServerException{
        try{
            String upit = "SELECT * FROM user u WHERE u.Username = ?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("Password").equals(HashFunction.napraviHash(user.getPassword()))) {
                user.setID(rs.getString("u.UserID"));
                user.setFirstName(rs.getString("u.FirstName"));
                user.setLastName(rs.getString("u.LastName"));
                user.setPassword(rs.getString("u.Password"));
                return user;
            }
        }catch (SQLException ex) {
            throw new ServerException("Greska pri traženju korisnika");
        }
        return new User();
    }
    
    public List<GenericEntity> getAll(GenericEntity e) throws ServerException, Exception {
        try {
            String upit = "SELECT * FROM " + e.getTableName();
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> list = e.getList(rs);
            s.close();
            return list;
        } catch (SQLException ex) {
            throw new ServerException("Server ne moze da prikaže podatke o " + e.getClass().getName() + ".");
        }
    }
    
    public GenericEntity getByID(GenericEntity o, String ID) throws ServerException, Exception {
        String upit = "SELECT * FROM " + o.getTableName()+ " WHERE " + o.getID()+ "=" + "'" + ID + "'";
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> list = o.getList(rs);
            s.close();
            return list.get(0);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServerException(ex.getMessage());
        }
    }
    
    public GenericEntity edit(GenericEntity o) {
        String upit = String.format("UPDATE %s SET %s WHERE %s = '%s'", o.getTableName(), o.getUpdate(), o.getID(), o.getIDvalue());
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public GenericEntity delete(GenericEntity o) throws ServerException {
        try {
            String upit = String.format("DELETE FROM %s WHERE %s = %s", o.getTableName(), o.getID(), o.getIDvalue());
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServerException(ex.getMessage());
        }
        return o;
    }
    
    public ArrayList<Table> findTables(String id) throws ServerException, Exception {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String upit = "SELECT * FROM table WHERE RestaurantID = '" + id + "'";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> lista = new Table().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : lista) {
                Table t = (Table) opstiDomenskiObjekat;
                tables.add(t);
            }
            s.close();
        } catch (SQLException ex) {
            throw new ServerException("Server ne moze da pronadje stolove");
        }
        return tables;
    }
    
    public GenericEntity save(GenericEntity o) throws ServerException {
        try {
            String upit = String.format("INSERT INTO %s VALUES (%s)", o.getTableName(), o.getInsertValues());
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            return o;
       
        } catch (SQLException ex) {
            throw new ServerException("Greska u SQL upitu.");
        }
    }
    
    public ArrayList<Restaurant> findRestaurants(HashMap<String, String> criteria) throws ServerException, Exception {
        String upit = "SELECT * FROM restaurant r";
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        if (criteria.size() > 0) {
            upit += " WHERE ";
        }
        if (criteria.get("name") != null) {
            upit += "r.Name LIKE '%" + criteria.get("name") + "%' AND ";
        }
        if (criteria.get("address") != null) {
            upit += "r.Address = '" + criteria.get("address") + "'";
        }
       
        if (upit.substring(upit.length() - 2, upit.length()).equals("AND ")) {
            upit = upit.substring(0, upit.length() - 2);
        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            ArrayList<GenericEntity> odoLista = (ArrayList<GenericEntity>) new Restaurant().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : odoLista) {
                restaurants.add((Restaurant) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerException("Greski pri kreiranju upita");
        }
        return restaurants;
    }
    
    public ArrayList<Guest> findGuests(HashMap<String, String> criteria) throws ServerException, Exception {
        String upit = "SELECT * FROM guest g";
        ArrayList<Guest> guests = new ArrayList<>();
        if (criteria.size() > 0) {
            upit += " WHERE ";
        }
        if (criteria.get("firstname") != null) {
            upit += "g.FirstName LIKE '%" + criteria.get("firstname") + "%' AND ";
        }
        if (criteria.get("lastname") != null) {
            upit += "g.LastName LIKE '%" + criteria.get("lastname") + "%' AND ";
        }
        if (criteria.get("id") != null) {
            upit += "g.GuestID= '" + criteria.get("id") + "'";
        }
        if (upit.substring(upit.length() - 3, upit.length()).equals("AND ")) {
            upit = upit.substring(0, upit.length() - 3);
        }
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            ArrayList<GenericEntity> odoLista = (ArrayList<GenericEntity>) new Guest().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : odoLista) {
                guests.add((Guest) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerException("Greski pri kreiranju upita");
        }
        return guests;
    }
    
    public ArrayList<Table> findTakenTables(Guest guest) throws ServerException, Exception {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String upit = "SELECT t.* FROM reservation r JOIN table t ON r.ReservationID = t.ReservationID WHERE r.GuestID = '" + guest.getID()+ "'";
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> listaObjekata = new Table().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : listaObjekata) {
                Table p = (Table) opstiDomenskiObjekat;
                tables.add(p);
            }
            s.close();
        } catch (SQLException ex) {
            throw new ServerException("Server ne moze da pronadje stolove");
        }
        return tables;
    }
    
    public ArrayList<Table> getAllTables(String PIB, LocalDate date, LocalTime time) throws ServerException, Exception {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String upit;
            if (PIB != null) {
               
                upit = "SELECT * FROM table t where t.TableID " +
                        "NOT IN (SELECT TableID from reservation r WHERE r.Date IS '" + java.sql.Date.valueOf(date) + "' OR"
                        + "TIMEDIFF(r.Time, '"+ java.sql.Time.valueOf(time) +"') > 3";
                       
            } else {
                throw new ServerException("Greska u upitu");
            }
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> listaObjekata = new Table().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : listaObjekata) {
                Table t = (Table) opstiDomenskiObjekat;
                tables.add(t);
            }
            s.close();
        } catch (SQLException ex) {
            throw new ServerException("Server ne moze da pronadje stolove");
        }
        return tables;
    }
    
    public GenericEntity saveReservation(Reservation r) throws ServerException {
        try {
            String upit = String.format("INSERT INTO reservation(ReservationID, GuestID, TableID, UserID, Date, Time, Note) VALUES (%s)", r.getInsertValues());
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            return r;
        } catch (SQLException ex) {
            throw new ServerException("Greska u SQL upitu.");
        }
    }


}