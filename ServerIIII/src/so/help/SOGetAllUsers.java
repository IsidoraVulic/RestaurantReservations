/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.help;

import domain.User;
import exception.ServerException;
import java.util.ArrayList;
import java.util.List;
import so.GenericSystemOperation;
import domain.GenericEntity;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOGetAllUsers extends GenericSystemOperation {

    private List<User> listaKorisnika = new ArrayList<>();

    @Override
    protected void executeOperation() throws ServerException {
        try {
            List<GenericEntity> lista = dbb.getAll(new User());
            for (GenericEntity opstiDomenskiObjekat : lista) {
                User a = (User) opstiDomenskiObjekat;
                listaKorisnika.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(SOGetAllUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<User> getListaKorisnika() {
        return listaKorisnika;
    }

}
