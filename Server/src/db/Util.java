/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author RYZEN
 */
public class Util {

    private Properties properties;
    private static Util instance;

    private Util() throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("db.config");
        properties = new Properties();
        properties.load(fis);
    }

    public static Util getInstance() throws IOException {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public String getDriver() {
        return properties.getProperty("driver");
    }

    public String getURL() {
        return properties.getProperty("url");
    }

    public String getUser() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }
}
