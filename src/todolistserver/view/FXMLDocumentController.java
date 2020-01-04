package todolistserver.view;

import todolistserver.model.DatabaseConnection;
import todolistserver.model.SocketConnection;

/**
 * @author Ibrahim
 */
public class FXMLDocumentController{

    SocketConnection socketConnection = new SocketConnection();

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
