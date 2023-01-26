/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import db.DBBroker;
import exception.ServerException;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author RYZEN
 */
public abstract class GenericSystemOperation {
    protected DBBroker dbb;
    
    public GenericSystemOperation(){
        this.dbb = new DBBroker();
    }
    
    synchronized public void execute() throws ServerException, IOException, SQLException {
        openConnection();
        try {
            executeOperation();
            commitTransaction();
        } catch (ServerException e) {
            rollbackTransaction();
            throw e;
        } finally {
            closeConnection();
        }
    }

    private void commitTransaction() throws ServerException {
        dbb.commit();
    }

    private void rollbackTransaction() throws ServerException {
        dbb.rollback();
    }

    private void closeConnection() throws ServerException {
        dbb.disconnect();
    }

    private void openConnection() throws ServerException, IOException, SQLException {
        dbb.connect();
    }

    protected abstract void executeOperation() throws ServerException;

}
