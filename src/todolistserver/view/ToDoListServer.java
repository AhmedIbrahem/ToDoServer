package todolistserver.view;

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
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        //String data = "{\"className\":\"TodoListDBOperations\",\"operation\":\"assignTodo\",\"entity\":[{\"userName\":\"ibrahim\",\"currentUserId\":1,\"todoId\":2}]}";
        //Controller.handle(data);

        stage.setOnCloseRequest((WindowEvent event) -> {
            DatabaseConnection.getInstance().closeConnection();
            Platform.exit();
            System.exit(0);
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
