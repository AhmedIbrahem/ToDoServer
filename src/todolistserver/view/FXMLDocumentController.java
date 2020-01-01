package todolistserver.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import todolistserver.model.SocketConnection;

/**
 * @author Ibrahim
 */
public class FXMLDocumentController implements Initializable{
    SocketConnection socketConnection=new SocketConnection();
    public void handleStopServer() {
        socketConnection.closeSocketConnection();
        //ToDoListServer.th.suspend();;
    }

    public void handleStartServer() {
        socketConnection.openSocketConnection();
        //ToDoListServer.th.resume();;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // socketConnection = new SocketConnection();
    }

}
