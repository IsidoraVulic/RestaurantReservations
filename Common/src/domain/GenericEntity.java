/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author RYZEN
 */
public interface GenericEntity extends Serializable {

    String getTableName(); //ime tabele

    String getInsertValues(); //vrati parametre

    String getColumnNames();
    
    String getID();
    
    String getIDvalue();
    
    List<GenericEntity> getList(ResultSet rs) throws Exception;
 
    String getUpdate();

    void setID(String id);
}
