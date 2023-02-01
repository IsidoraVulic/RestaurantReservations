/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author RYZEN
 */
public class Reservation implements Serializable, GenericEntity {

    private String id;

    private Guest guest;
    private Table table;
    private User user;

    private LocalDate date;
    private LocalTime time;
    private String note;

    public Reservation() {
    }

    public Reservation(String id, Guest guest, Table table, User user, LocalDate date, LocalTime time, String note) {
        this.id = id;
       
        this.guest = guest;
        this.table = table;
        this.user = user;
        this.date = date;
        this.time = time;
        this.note = note;
    }

    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.guest);
        hash = 53 * hash + Objects.hashCode(this.table);
        hash = 53 * hash + Objects.hashCode(this.user);
        hash = 53 * hash + Objects.hashCode(this.date);
        hash = 53 * hash + Objects.hashCode(this.time);
        hash = 53 * hash + Objects.hashCode(this.note);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Reservation other = (Reservation) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.note, other.note)) {
            return false;
        }
      
        if (!Objects.equals(this.guest, other.guest)) {
            return false;
        }
        if (!Objects.equals(this.table, other.table)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return Objects.equals(this.time, other.time);
    }

    @Override
    public String toString() {
        return "Reservation{" + "id=" + id + ", guest=" + guest + ", table=" + table + ", user=" + user + ", date=" + date + ", time=" + time + ", note=" + note + '}';
    }

    @Override
    public String getTableName() {
        return "reservation";
    }

    @Override
    public String getInsertValues() {
        return String.format("'%s', '%s', '%s', '%s', '%s', '%s', '%s'", id, guest.getId(), table.getId(), user.getId(), Date.valueOf(date.toString()), time.toString(), note);
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Reservation reservation = new Reservation();
            reservation.setId(rs.getString("ReservationID"));
            reservation.setDate(LocalDate.parse(rs.getDate("Date").toString()));
            reservation.setTime(LocalTime.parse(rs.getTime("Time").toString()));
            reservation.setNote(rs.getString("Note"));

            Guest guest = new Guest();
            guest.setId(rs.getString("GuestID"));
            reservation.setGuest(guest);

            Table table = new Table();
            table.setID(rs.getString("TableID"));
            reservation.setTable(table);

            User user = new User();
            user.setID(rs.getString("UserID"));
            reservation.setUser(user);
            

            list.add(reservation);

        }
        return list;
    }

    @Override
    public String getID() {
        return "ReservationID";
    }

    @Override
    public String getIDvalue() {
        return id;
    }

    @Override
    public String getUpdate() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public String getColumnNames() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
