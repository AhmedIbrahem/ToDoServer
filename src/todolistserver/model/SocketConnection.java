/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dell
 */
public class SocketConnection {

    static ServerSocket serverSocket;
    final static int SOCKET_PORT = 5005;
    public static boolean isServerRunning = false;

    public SocketConnection() {
        isServerRunning = true;
        openSocketConnection();
    }

    public static void openSocketConnection() {
        try {
            serverSocket = new ServerSocket(SOCKET_PORT);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        while (isServerRunning) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            new StreamingListner(socket);
        }
    }

    public static void closeSocketConnection() {
        if (!serverSocket.isClosed()) {
            isServerRunning = false;
            try {
                serverSocket.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
