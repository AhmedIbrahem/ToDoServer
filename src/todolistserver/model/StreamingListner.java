package todolistserver.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolistserver.controller.Controller;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class StreamingListner extends Thread {

    Thread th;
    boolean isRunning = true;
    DataInputStream dataInputStream;
    PrintStream printStream;

    private int userID;

    public int getUserId() {
        return userID;
    }
    static ArrayList<StreamingListner> clientsVector = new ArrayList<StreamingListner>();

    public StreamingListner(Socket socketPort) {
        try {
            dataInputStream = new DataInputStream(socketPort.getInputStream());
            printStream = new PrintStream(socketPort.getOutputStream());
            clientsVector.add(this);
            printStream.println("opened");
            th = new Thread(this);
            th.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void getUserID(String jsonValue) {

//        try {
            RequestEntity<UserEntity> user = GsonParser.parseFromJson(jsonValue);
            if (user.getEntity().size()!=0) {
                userID = user.getEntity().get(0).getId();
            }
//        } catch (InstantiationException ex) {
//            ex.printStackTrace();
//        } catch (IllegalAccessException ex) {
//            ex.printStackTrace();
//        }

    }

    public void run() {
        String str = "";
        while (SocketConnection.isServerRunning&&isRunning) {
            try {
                str = dataInputStream.readLine();
                if (str != null && str.equals("clientClosed")) {                    
                    removeObject();
                    System.out.println("closedClient " + clientsVector.size());
                } else if (str != null) {
                    System.out.println("StreamingListenenr"+str);
                    String response = Controller.handle(str);
                    if (response.contains("loginResponse")) {
                        getUserID(response);
                        System.out.println(("userID = " + userID));
                    }  
                    printStream.println(response);
                }if(str == null){
                    isRunning = false;
                }
            } catch (SocketException ex) {
                //SocketConnection.isServerRunning = false;
                isRunning = false;
                System.out.println("catch");
            } catch (IOException ex) {
                //SocketConnection.isServerRunning = false;
                isRunning = false;
                System.out.println("catch");
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(StreamingListner.class.getName()).log(Level.SEVERE, null, ex);
                //SocketConnection.isServerRunning = false;
                isRunning = false;
                System.out.println("catch");
            }
        }
        try {
            printStream.close();
            dataInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(StreamingListner.class.getName()).log(Level.SEVERE, null, ex);
        }
        th.stop();
    }

    public static void sendNotificationMessage(ArrayList<Integer> list) {
        
        for(int i=0;i<clientsVector.size();i++){
            for(int j=0;j<list.size();j++){
                if(clientsVector.get(i).getUserId() == list.get(j)){
                    clientsVector.get(i).printStream.println("notification received");
                }
            }
        }
        
    }
    
    public static void syncFriendsUI(ArrayList<UserEntity> list, String message) {

        for(int i=0;i<clientsVector.size();i++){
            for(int j=0;j<list.size();j++){
                if(clientsVector.get(i).getUserId() == list.get(j).getId()){
                    clientsVector.get(i).printStream.println(message);
                }
            }
        }
        
    }
    
    synchronized void sendMessageToAll(String msg) {
        if (clientsVector != null) {
            System.out.println(clientsVector.size());
            for (StreamingListner ch : clientsVector) {
                ch.printStream.println(msg);
            }
        }
    }

    private synchronized void removeObject() {
        int index = 0;
        for (int i = 0; i < clientsVector.size(); i++) {
            if (clientsVector.get(i).getUserId() == this.userID) {
                index = i;
            }
        }
        clientsVector.remove(index);
        System.out.println("RemoveObject = " + clientsVector.size());
    }

}
