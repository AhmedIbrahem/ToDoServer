package todolistserver.view;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import todolistserver.controller.Controller;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.GsonParser;
import todolistserver.model.ReflectionClass;
import todolistserver.model.StreamingListner;

import todolistserver.model.dao.implementation.ItemDBOperations;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.dao.implementation.TodoListDBOperations;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class ToDoListServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainFXML.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        //String data = "{\"className\":\"TodoListDBOperations\",\"operation\":\"assignTodo\",\"entity\":[{\"userName\":\"ibrahim\",\"currentUserId\":1,\"todoId\":2}]}";
        //Controller.handle(data);

        stage.setOnCloseRequest((WindowEvent event) -> {
            DatabaseConnection.getInstance().closeConnection();
            Platform.exit();
            System.exit(0);
        });
//        ItemEntity itemTest = new ItemEntity();
//        itemTest.setItemID(2);
//        ArrayList<Object> testList = new ArrayList<Object>();
//        testList.add(itemTest);
//        RequestEntity test = ItemDBOperations.deleteItem(testList);
//        System.out.println("");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
