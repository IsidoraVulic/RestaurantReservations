/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so.help;

import controller.Controller;
import domain.User;
import exception.ServerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import so.GenericSystemOperation;

/**
 *
 * @author RYZEN
 */
public class SOLogout extends GenericSystemOperation {

    private User user;

    @Override
    protected void executeOperation() throws ServerException {
        try {
            List<User> lista = Controller.vratiInstancu().getListaKorisnika();
            for (User adminIzListe : lista) {
                if (adminIzListe.getUsername().equals(user.getUsername())) {
                    adminIzListe.setOnline(false);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SOLogout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(SOLogout.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SOLogout.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

}
