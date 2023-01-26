/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import domain.Table;
import exception.ServerException;

/**
 *
 * @author RYZEN
 */
public class SODeleteTable extends GenericSystemOperation {

    private Table table;
    private boolean success;

    @Override
    protected void executeOperation() throws ServerException {
        if (dbb.delete(table) != null) {
            success = true;
        }
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
