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
import static todolistserver.model.dao.implementation.UserDBOperations.queryValues;
import todolistserver.model.entities.RequestEntity;
import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.TodoEntity;
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

    public static ArrayList<UserEntity> getTodoCollaborators(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> Collaborators = new ArrayList<>();
        Collaborators = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_COLLABROTOR_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        System.out.println("ss" + Collaborators.size());
        return Collaborators;
    }

    public static ArrayList<UserEntity> getAllUSersExpictedLoginUser(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> allusers = new ArrayList<>();
        allusers = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_All_USERS_EXPICTED_LOFIN_USER, queryValues, DatabaseConnection.getInstance().getConnection());

        return allusers;
    }

    public static ArrayList<UserEntity> getItemCollaborators(ArrayList<Object> queryValues) {
        ArrayList<UserEntity> Collaborators = new ArrayList<>();
        Collaborators = DBStatementsExecuter.retrieveUserData(DatabaseQueries.RETRIEVE_ITEM_COLLABROTOR_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        System.out.println("ss" + Collaborators.size());
        return Collaborators;
    }

    public static int logout(ArrayList<Object> queryValues) {
        int result = -1;
        result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.LOGOUT_QUARY, queryValues, DatabaseConnection.getInstance().getConnection());
        return result;
    }

        
       
    public static ArrayList<UserEntity> getAllCollaborators(TodoEntity todo){
        ArrayList<Object> queryValues = new ArrayList<>();
        queryValues.add(todo.getId());
        ArrayList<UserEntity> collaborators = DBStatementsExecuter.retrieveUserData(DatabaseQueries.GET_ALL_FRIENDS_ASSIGNED_TO_TODO, queryValues, DatabaseConnection.getInstance().getConnection());
        return collaborators;
    }
}
