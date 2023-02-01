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
public class Table implements Serializable, GenericEntity {

    private String id;
    private int chairs;
    private String pib;

    public Table() {
    }

    public Table(String id, int chairs, String pib) {
        this.id = id;
        this.chairs = chairs;
        this.pib = pib;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChairs() {
        return chairs;
    }

    public void setChairs(int chairs) {
        this.chairs = chairs;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    @Override
    public String toString() {
        return "Table{" + "id=" + id + ", chairs=" + chairs + ", pib=" + pib + '}';
    }

    @Override
    public String getTableName() {
        return "dining_table";
    }

    @Override
    public String getInsertValues() {
        return String.format("'%s', '%s', '%s'", id, pib, chairs);
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Table table = new Table();

            table.setId(rs.getString("TableID"));
            table.setChairs(rs.getInt("Chairs"));

            Restaurant r = new Restaurant();
            r.setId(rs.getString("RestaurantID"));
            table.setPib(r.getId());

            list.add(table);
        }

        return list;
    }

    @Override
    public String getID() {
        return "TableID";
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getColumnNames() {
        return String.format("TableID, RestaurantID, Chairs");
    }
}
