package todolistserver.view;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import todolistserver.model.ReflectionClass;
import todolistserver.model.SocketConnection;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class ToDoListServer extends Application{ // implements Runnable{

    //public static Thread th;
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();

        //th = new Thread(new ToDoListServer());
        //th.start();
        UserEntity test = new UserEntity();
        test.setId(1);
        
        ReflectionClass.getObject("UserDBOperations","login",test);
        stage.setOnCloseRequest((WindowEvent event) -> {
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

//    @Override
//    public void run() {
//       new SocketConnection(); 
//    }
}
