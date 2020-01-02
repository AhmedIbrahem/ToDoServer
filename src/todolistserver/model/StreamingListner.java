package todolistserver.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * @author dell
 */
public class StreamingListner extends Thread {

    Thread th;
    Boolean running = false;
    DataInputStream dataInputStream;
    PrintStream printStream;
    static ArrayList<StreamingListner> clientsVector = new ArrayList<StreamingListner>();

    public StreamingListner(Socket socketPort) {
        try {
            dataInputStream = new DataInputStream(socketPort.getInputStream());
            printStream = new PrintStream(socketPort.getOutputStream());
            running = true;
            clientsVector.add(this);
            th = new Thread(this);
            th.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void run() {
        String str = "";
        while (SocketConnection.isServerRunning) {
            try {
                str = dataInputStream.readLine();
                System.out.println(SocketConnection.isServerRunning);
                if (str != null) {           
                    
                }
            } catch (SocketException ex) {
                SocketConnection.isServerRunning = false;
            } catch (IOException ex) {
                SocketConnection.isServerRunning = false;
            }
        }
        th.stop();
    }

    public void sendMessage(String message) {
        printStream.print("message");
    }

    void sendMessageToAll(String msg) {
        if (clientsVector != null) {
            for (StreamingListner ch : clientsVector) {
                ch.printStream.println(msg);
            }
        }
    }
}
