package todolistserver.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import todolistserver.model.DatabaseConnection;

/**
 * @author Ibrahim
 */
public class FXMLDocumentController {
    
    public Label label;

    public FXMLDocumentController() {
      

    }
    
    public void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
       
}
