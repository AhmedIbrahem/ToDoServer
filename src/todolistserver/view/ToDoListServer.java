package todolistserver.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.SocketConnection;

/**
 *
 * @author dell
 */
public class ToDoListServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
       

        //String data = "{\"className\":\"TodoListDBOperations\",\"operation\":\"assignTodo\",\"entity\":[{\"userName\":\"ibrahim\",\"currentUserId\":1,\"todoId\":2}]}";
        //Controller.handle(data);
        stage.setOnCloseRequest((WindowEvent event) -> {
            DatabaseConnection.getInstance().closeConnection();
            if (SocketConnection.getInstance().streamListner != null) {
                SocketConnection.getInstance().streamListner.sendMessageToAll("closed");
            }
            Platform.exit();
            System.exit(0);
        });
        //        ItemEntity itemTest = new ItemEntity();
        //        itemTest.setItemID(2);
        //        ArrayList<Object> testList = new ArrayList<Object>();
        //        testList.add(itemTest);
        //        RequestEntity test = ItemDBOperations.deleteItem(testList);
        //        System.out.println("");

        //Bind to localhost adapter with a zero connection queue 
        System.out.println("ddd"+SocketConnection.getInstance().isServerRunning);
        
             if (!SocketConnection.getInstance().isServerRunning) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("The Server is Already Runing");
            alert.showAndWait();
            System.exit(1);

        } else {
            Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
