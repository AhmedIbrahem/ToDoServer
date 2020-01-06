package todolistserver.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author dell
 */
public class SocketConnection extends Thread {

    Thread th;
    static ServerSocket serverSocket;
    final static int SOCKET_PORT = 5005;
    public static boolean isServerRunning = false;  
    StreamingListner streamListner=null;

    public SocketConnection() {
        openSocketConnection();
    }

    public void openSocketConnection() {
        try {
            serverSocket = new ServerSocket(SOCKET_PORT);
            if (!isServerRunning) {
                isServerRunning = true;
            }            
            
            th = new Thread(this);
            th.start();
        } catch (IOException ex) {
            isServerRunning = false;
        }
    }

    public void closeSocketConnection() {
        try {
            if (!serverSocket.isClosed()) {
                isServerRunning = false;                
                System.out.println("closed");
                if(streamListner!=null)
                    streamListner.sendMessageToAll("closed");
                serverSocket.close();
                th.stop();
            }
        } catch (IOException ex) {
            Logger.getLogger(SocketConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            while (isServerRunning) {
                
                Socket socket = null;
                System.out.println("opened");
                if(streamListner!=null)
                    streamListner.sendMessageToAll("opened");
                socket = serverSocket.accept();
                streamListner = new StreamingListner(socket); 
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
