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

/**
 *
 * @author RYZEN
 */
public class Guest implements Serializable, GenericEntity {

    private String id;
    private String firstname;
    private String lastname;
    private String contact;
    private String email;

    public Guest() {
    }

    public Guest(String id, String firstname, String lastname, String contact, String email) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.contact = contact;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.firstname);
        hash = 89 * hash + Objects.hashCode(this.lastname);
        hash = 89 * hash + Objects.hashCode(this.contact);
        hash = 89 * hash + Objects.hashCode(this.email);
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
        final Guest other = (Guest) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.firstname, other.firstname)) {
            return false;
        }
        if (!Objects.equals(this.lastname, other.lastname)) {
            return false;
        }
        if (!Objects.equals(this.contact, other.contact)) {
            return false;
        }
        return Objects.equals(this.email, other.email);
    }

    @Override
    public String toString() {
        return firstname + " " + lastname;
    }

    @Override
    public String getTableName() {
        return "guest";
    }

    @Override
    public String getInsertValues() {
        return String.format("'%s', '%s', '%s', '%s', '%s'", id, firstname, lastname, contact, email);
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Guest guest = new Guest();
            guest.setId(rs.getString("GuestID"));
            guest.setContact(rs.getString("Contact"));
            guest.setFirstname(rs.getString("FirstName"));
            guest.setLastname(rs.getString("LastName"));
            guest.setEmail(rs.getString("Email"));

            list.add(guest);
        }

        return list;
    }

    @Override
    public String getID() {
        return "GuestID";
    }

    @Override
    public String getIDvalue() {
        return id;
    }

    @Override
    public String getUpdate() {
        return String.format("FirstName='%s',LastName='%s',Contact='%s', Email='%s'", firstname, lastname, contact, email);
    }

    @Override
    public void setID(String id) {
        this.id = id;
    }

    @Override
    public String getColumnNames() {
        return String.format("GuestID, FirstName, LastName, Contact, Email");
    }

}
