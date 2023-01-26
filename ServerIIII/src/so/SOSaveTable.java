/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import domain.Table;
import exception.ServerException;
import java.util.Date;
import security.HashFunction;

/**
 *
 * @author RYZEN
 */
public class SOSaveTable extends GenericSystemOperation {

    private Table table;

    @Override
    protected void executeOperation() throws ServerException {
        String id = Integer.toString(((int) (new Date().getTime() / 1000))) + HashFunction.napraviHash(table.getPib());
        table.setID(id);
        table = (Table) dbb.save(table);
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

}
