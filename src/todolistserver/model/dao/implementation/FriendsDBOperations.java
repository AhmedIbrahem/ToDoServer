/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;
import java.sql.SQLException;
import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class FriendsDBOperations {

    public static ArrayList<UserEntity> getAllUSers(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> allusers = new ArrayList<>();
        allusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_USERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        
        return allusers;
    }
      public static ArrayList<UserEntity> getOnlineUSers(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> onlineusers = new ArrayList<>();
        onlineusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_ONLINE_USERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        
        return onlineusers;
    }
       public static ArrayList<UserEntity> getOfflineUSers(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> onlineusers = new ArrayList<>();
        onlineusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_OFFLINE_USERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        
        return onlineusers;
    }
        public static ArrayList<UserEntity> getFrinds(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> frinds = new ArrayList<>();
        frinds = DBStatementsExecuter.retrievefrindsIDs(DatabaseQueries.RETRIEVE_FRINDLIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        return frinds;
    }
        public static ArrayList<UserEntity> getFrindsData(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> frinds = new ArrayList<>();
        frinds = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_FRINDLIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        return frinds;
    }
       
       

}
