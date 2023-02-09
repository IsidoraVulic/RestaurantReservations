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

    public DBBroker() {

    }

    public void connect() throws ServerException, IOException, SQLException, ClassNotFoundException {
        try {
            Class.forName(Util.getInstance().getDriver());
            String url = Util.getInstance().getURL();
            String user = Util.getInstance().getUser();
            String password = Util.getInstance().getPassword();
            connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(false);

        } catch (IOException ex) {
            ex.printStackTrace();
            throw new ServerException("Greska kod citanja iz properties fajla!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new ServerException("Konekcija na bazu neuspesna!");
        }
    }

    public void disconnect() throws ServerException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new ServerException("Raskidanje konekcije neuspesno!");
        }
    }

    public void commit() throws ServerException {
        try {
            connection.commit();
        } catch (SQLException ex) {
            throw new ServerException("Transakcija nije uspesno potvrdjena");
        }
    }

    public void rollback() throws ServerException {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            throw new ServerException("Transakcija nije uspesno ponistena");
        }
    }

    public User login(User user) throws ServerException {
        try {
            String upit = "SELECT * FROM user u WHERE u.Username = ?";
            PreparedStatement ps = connection.prepareStatement(upit);
            ps.setString(1, user.getUsername());
            ResultSet rs = ps.executeQuery();
            if (rs.next() && rs.getString("Password").equals(user.getPassword())) {
                user.setId(rs.getString("u.UserID"));
                user.setFirstName(rs.getString("u.FirstName"));
                user.setLastName(rs.getString("u.LastName"));
                user.setPassword(rs.getString("u.Password"));
                System.out.println(user.toString());
                return user;
            }
        } catch (SQLException ex) {
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
        String upit = "SELECT * FROM " + o.getTableName() + " WHERE " + o.getID() + "=" + "'" + ID + "'";
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> list = o.getList(rs);
            s.close();
            System.out.println(upit);
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
            System.out.println(upit);
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
            String upit = "SELECT * FROM dining_table WHERE RestaurantID = '" + id + "'";
            System.out.println(upit);
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
            String upit = String.format("INSERT INTO %s (%s) VALUES (%s)", o.getTableName(), o.getColumnNames(), o.getInsertValues());
            System.out.println(upit);
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            System.out.println(upit);
            return o;

        } catch (SQLException ex) {
            throw new ServerException("Greska u SQL upitu.");
        }
    }

    public GenericEntity update(GenericEntity o) throws ServerException {
        try {
            String upit = "UPDATE " + o.getTableName() + " SET " + o.getUpdate() + " WHERE " + o.getID() + "='o.getIDvalue()'";
            Statement s = connection.createStatement();
            s.executeUpdate(upit);
            s.close();
            System.out.println(upit);
            return o;

        } catch (SQLException ex) {
            throw new ServerException("Greska u SQL upitu.");
        }
    }

    public ArrayList<Restaurant> findRestaurants(HashMap<String, String> criteria) throws ServerException, Exception {
        String upit = "SELECT * FROM restaurant r";
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        if (criteria != null) {
            if (criteria.size() > 0) {
                upit += " WHERE ";
            }
            if (criteria.get("name") != null) {
                upit += "r.Name LIKE '%" + criteria.get("name") + "%' AND ";
            }
            if (criteria.get("address") != null) {
                upit += "r.Address LIKE '%" + criteria.get("address") + "%' AND ";
            }

            if (criteria.get("pib") != null) {
                upit += "r.RestaurantID = '" + criteria.get("pib") + "'";
            }
            if (upit.substring(upit.length() - 4, upit.length()).equals("AND ")) {
                upit = upit.substring(0, upit.length() - 4);
            }
        }
        System.out.println(upit);
        try {
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            ArrayList<GenericEntity> odoLista = (ArrayList<GenericEntity>) new Restaurant().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : odoLista) {
                restaurants.add((Restaurant) opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerException("Greska pri kreiranju upita");
        }
        return restaurants;
    }

    public ArrayList<Guest> findGuests(HashMap<String, String> criteria) throws ServerException, Exception {
        String upit = "SELECT * FROM guest g";
        ArrayList<Guest> guests = new ArrayList<>();
        if (criteria != null) {
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
            System.out.println(upit.substring(upit.length() - 4, upit.length()).equals("AND "));
            if (upit.substring(upit.length() - 4, upit.length()).equals("AND ")) {
                upit = upit.substring(0, upit.length() - 4);
            }
        }
        try {
            Statement s = connection.createStatement();
            System.out.println(upit);
            ResultSet rs = s.executeQuery(upit);
            ArrayList<GenericEntity> odoLista = (ArrayList<GenericEntity>) new Guest().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : odoLista) {
                guests.add((Guest) opstiDomenskiObjekat);
                System.out.println(opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerException("Greska pri kreiranju upita");
        }
        return guests;
    }
    
    public ArrayList<Reservation> findReservations(HashMap<String, String> criteria) throws ServerException, Exception {
        String upit = "SELECT * FROM reservation r join guest g on r.GuestID=g.GuestID join dining_table t on r.TableID=t.TableID join restaurant res on res.RestaurantID=t.RestaurantID";
        ArrayList<Reservation> reservations = new ArrayList<>();
        if (criteria != null) {
            if (criteria.size() > 0) {
                upit += " WHERE ";
            }
            if (criteria.get("firstname") != null) {
                upit += "g.FirstName LIKE '%" + criteria.get("firstname") + "%' AND ";
            }
            if (criteria.get("lastname") != null) {
                upit += "g.LastName LIKE '%" + criteria.get("lastname") + "%' AND ";
            }
            if (criteria.get("restaurant") != null) {
                upit += "res.Name= '" + criteria.get("restaurant") + "'";
            }
            System.out.println(upit.substring(upit.length() - 4, upit.length()).equals("AND "));
            if (upit.substring(upit.length() - 4, upit.length()).equals("AND ")) {
                upit = upit.substring(0, upit.length() - 4);
            }
        }
        try {
            Statement s = connection.createStatement();
            System.out.println(upit);
            ResultSet rs = s.executeQuery(upit);
            ArrayList<GenericEntity> odoLista = (ArrayList<GenericEntity>) new Reservation().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : odoLista) {
                reservations.add((Reservation) opstiDomenskiObjekat);
                System.out.println(opstiDomenskiObjekat);
            }
        } catch (SQLException ex) {
            throw new ServerException("Greska pri kreiranju upita");
        }
        return reservations;
    }

   

    public ArrayList<Table> getAllTables(Reservation reservation) throws ServerException, Exception {
        ArrayList<Table> tables = new ArrayList<>();
        try {
            String upit;
            if (reservation != null) {

                upit = "SELECT * FROM dining_table "
                        + "WHERE TableID NOT IN (SELECT r.TableID FROM reservation r JOIN dining_table t ON t.TableID=r.TableID WHERE r.Date='" + java.sql.Date.valueOf(reservation.getDate()) + "' AND"
                        + " ABS(TIMEDIFF('"+java.sql.Time.valueOf(reservation.getTime()) + "', r.Time)) < 10800 AND " 
                        + "t.RestaurantID = '" + reservation.getTable().getPib() + "') AND RestaurantID = '" + reservation.getTable().getPib() + "'";

            } else {
                throw new ServerException("Greska u upitu");
            }
            System.out.println(upit);
            Statement s = connection.createStatement();
            ResultSet rs = s.executeQuery(upit);
            List<GenericEntity> listaObjekata = new Table().getList(rs);
            for (GenericEntity opstiDomenskiObjekat : listaObjekata) {
                Table t = (Table) opstiDomenskiObjekat;
                tables.add(t);
            }
            s.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
