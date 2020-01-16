/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.SocketConnection;
import todolistserver.model.dao.implementation.DBStatementsExecuter;
import todolistserver.model.dao.implementation.FriendsDBOperations;
import todolistserver.model.entities.UserEntity;

/**
 * FXML Controller class
 *
 * @author AhmedIbrahem
 */
public class MainFXMLController implements Initializable {

    @FXML
    private VBox contant;
    @FXML
    private VBox leftContact;
    @FXML
    private AnchorPane parent;
    SocketConnection socketConnection = SocketConnection.getInstance();
    ArrayList<Object> queryValues = new ArrayList<>();
    final int ROW_HEIGHT = 25;
    final int ROW_WIDTH = 350;
    boolean onOf;
    Image imageStartServer;
    Image imageStopServer;
    ImageView controlServer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        onOf = true;

    }

    public void getStatistics() {
        System.out.println("Hiiii sta");
        contant.getChildren().clear();
        ArrayList<UserEntity> onlineusers = new ArrayList<>();
        onlineusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_ONLINE_USERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        ArrayList<UserEntity> offlineeusers = new ArrayList<>();
        offlineeusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_OFFLINE_USERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());

        ObservableList<PieChart.Data> pieChartData
                = FXCollections.observableArrayList(
                        new PieChart.Data("online Users", onlineusers.size()),
                        new PieChart.Data("offline USers", offlineeusers.size()));
        final PieChart chart = new PieChart(pieChartData);
        chart.setTitle("Users in system");
        System.out.println(onlineusers.size());
        System.out.println(offlineeusers.size());


        contant.getChildren().add(chart);

    }

    public void getOnlineUsres() {
        contant.getChildren().clear();
        ArrayList<UserEntity> users = new ArrayList<>();
        users=FriendsDBOperations.getOnlineUSers(queryValues);
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < users.size(); i++) {
            items.add(users.get(i).getUsername().toString());
        }
        ListView<String> list = new ListView<>();
        list.setItems(items);
        list.setMaxHeight(items.size() * ROW_HEIGHT);
        list.setMaxWidth(ROW_WIDTH);
        contant.getChildren().add(list);
        contant.setAlignment(Pos.CENTER);

    }

    public void getOfflineUsres() {
        contant.getChildren().clear();
        ArrayList<UserEntity> users = new ArrayList<>();
        users=FriendsDBOperations.getOfflineUSers(queryValues);
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < users.size(); i++) {
            items.add(users.get(i).getUsername());
        }
        ListView<String> list = new ListView<>();
        list.setItems(items);
        list.setPrefHeight(items.size() * ROW_HEIGHT + 2);
        list.setMaxWidth(ROW_WIDTH);

        contant.getChildren().add(list);
        contant.setAlignment(Pos.CENTER);

    }

    public void getAllUsres() {
        System.out.println("Hiiii get all");
        contant.getChildren().clear();
        ArrayList<UserEntity> users = new ArrayList<>();
        users=FriendsDBOperations.getAllUSers(queryValues);
        ObservableList<String> items = FXCollections.observableArrayList();
        System.out.println(users.size());
        for (int i = 0; i < users.size(); i++) {
            items.add(users.get(i).getUsername());
        }
        ListView<String> list = new ListView<>();
        list.setItems(items);

        list.setPrefHeight(items.size() * ROW_HEIGHT + 2);
        list.setMaxWidth(ROW_WIDTH);

        contant.getChildren().add(list);
        contant.setAlignment(Pos.CENTER);
    }

    public void controlServer() {
        System.out.println("Hiiii get ser");
        contant.getChildren().clear();
        controlServer = new ImageView();
        imageStartServer = new Image(getClass().getResourceAsStream("on.png"));
        imageStopServer = new Image(getClass().getResourceAsStream("of.png"));
        controlServer.setImage(imageStopServer);
        controlServer.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent event) -> {
            if (onOf) {
                controlServer.setImage(imageStartServer);
                onOf = false;
                handleStopServer();

            } else {
                controlServer.setImage(imageStopServer);
                handleStartServer();
                onOf = true;

            }
        });

        contant.getChildren().addAll(controlServer);
        contant.setAlignment(Pos.CENTER);

    }

    public void handleStopServer() {
        if (SocketConnection.isServerRunning) {
            socketConnection.closeSocketConnection();
        }
    }

    public void handleStartServer() {
        if (!SocketConnection.isServerRunning) {
            DatabaseConnection.getInstance();
            socketConnection.openSocketConnection();
        }
    }

}
