/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package communication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 *
 * @author RYZEN
 */
public class Communication {
    
    private static Communication instance;
    private static Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;
    
    private Communication(){
        
    }
    
    public static Communication getInstance(){
        if(instance==null)
            instance = new Communication();
        return instance;
    }
    
    public Socket getSocket() {
        return socket;
    }
    
    public void setSocket(Socket socket) throws IOException {
        Communication.socket = socket;
        out = new ObjectOutputStream(Communication.socket.getOutputStream());
        in = new ObjectInputStream(Communication.socket.getInputStream());
    }
    
    public void sendRequest(Request request) throws IOException{
        out.writeUnshared(request);
    }
    
    public Response readResponse()throws IOException, ClassNotFoundException {
        return (Response) in.readUnshared();
    }
}
