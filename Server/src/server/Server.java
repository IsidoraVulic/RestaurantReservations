/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RYZEN
 */
public class Server extends Thread{
    
    private ServerSocket serverSocket;
    public static int port = 9000;
    static List<ProcessRequests> clients = new ArrayList<>();

    public Server() {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            System.out.println("Greska. Server socket nije kreiran.");
        }
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                Socket socket = serverSocket.accept();
                ProcessRequests pr = new ProcessRequests(socket, clients);
                pr.start();
                clients.add(pr);
                System.out.println("Klijent je povezan na server!");
            }
        } catch (SocketException ex) {
            System.out.println("Prekinula se konekcija sa serverom");
        } catch (IOException e) {
            System.out.println("Greska prilikom povezivanja klijenta!");
        }
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void stopServer() {
        try {
            serverSocket.close();
            for (ProcessRequests client : clients) {
                client.getSocket().close();
            }
        } catch (IOException ioe) {
           ioe.getMessage();
        }
    }
}
