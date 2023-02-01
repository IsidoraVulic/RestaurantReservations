/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import controller.Controller;
import domain.User;
import exception.ServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RYZEN
 */
public class SOLogin extends GenericSystemOperation {

    private User params;
    private User user;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            List<User> listaAdministratora = Controller.vratiInstancu().getListaKorisnika();
            user = dbb.login(params);
            if (user.getUsername() != null) {
                for (User adminIzListe : listaAdministratora) {
                    if (adminIzListe.equals(user)) {
                        if (adminIzListe.isOnline()) {
                            throw new ServerException("Administrator je već ulogovan");
                        } else {
                            int indeks = listaAdministratora.indexOf(adminIzListe);
                            Controller.vratiInstancu().getListaKorisnika().get(indeks).setOnline(true);
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SOLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SOLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SOLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public User getParams() {
        return params;
    }

    public void setParams(User params) {
        this.params = params;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
