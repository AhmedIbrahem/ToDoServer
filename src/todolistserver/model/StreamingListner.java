/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model;

import com.google.gson.Gson;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class StreamingListner  extends  Thread{    

    Boolean running = false;
    DataInputStream dataInputStream;
    PrintStream printStream;    

    public StreamingListner(Socket socketPort) {
        try {
            dataInputStream = new DataInputStream(socketPort.getInputStream());
            printStream = new PrintStream(socketPort.getOutputStream());            
            running = true;
            start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        String str = "";
        while (running) {

            try {

                str = dataInputStream.readLine();
                if (str != null) {
                    System.out.println("Str = " + str);                   
                }              
            } catch (IOException ex) {
                running = false;
            }

        }
        stop();
    }
  
}


