package todolistserver.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static todolistserver.model.Constants.*;

/**
 * @author Ibrahim
 */
public class DatabaseConnection {
    
    private static DatabaseConnection instance;
    private Connection connection;
    
    private DatabaseConnection(){
        try {    
            String connectionUrl = "jdbc:sqlserver://localhost:1455;databaseName=ToDoListProject;user="+DATABASE_USERNAME+";password="+DATABASE_PASSWORD;
            connection = DriverManager.getConnection(connectionUrl);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DatabaseConnection getInstance(){
        if(instance == null){
            synchronized(DatabaseConnection.class){
                if(instance == null){
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;     
    }
    public Connection getConnection() {
        return connection;        
    }
    
    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
