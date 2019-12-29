package todolistserver.model;

/**
 * @author Ibrahim
 */
public class DatabaseConnection {
    private static DatabaseConnection newInstance;
    
    private DatabaseConnection(){
        //connect to database
    }
    
    public static DatabaseConnection getInstance(){
        if(newInstance == null){
            synchronized(DatabaseConnection.class){
                if(newInstance == null){
                    newInstance = new DatabaseConnection();
                }
            }
        }
        return newInstance;     
    }
}
