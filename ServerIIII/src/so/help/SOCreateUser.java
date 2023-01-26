/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.help;

import domain.GenericEntity;
import exception.ServerException;
import so.GenericSystemOperation;

/**
 *
 * @author RYZEN
 */
public class SOCreateUser extends GenericSystemOperation {

    private GenericEntity user;

    @Override
    protected void executeOperation() throws ServerException {
        dbb.save(user);
    }

    public GenericEntity getUser() {
        return user;
    }

    public void setUser(GenericEntity user) {
        this.user = user;
    }

}
