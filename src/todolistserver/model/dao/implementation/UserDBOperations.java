/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.UserEntity;
import java.sql.SQLException;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;

/**
 *
 * @author dell
 */
public class UserDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity<UserEntity> login(ArrayList<Object> value) throws SQLException {

        UserEntity user = null;
        ArrayList<UserEntity> users = null;
        RequestEntity<UserEntity> response = null;
        users = new ArrayList<>();
        if (value != null) {
            user = (UserEntity) value.get(0);

            queryValues.add(user.getUsername());
            queryValues.add(user.getPassword());
            users = DBStatementsExecuter.retrieveUserData(DatabaseQueries.LOGIN_USER_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (users != null && users.size() != 0) {
                queryValues.clear();
                queryValues.add(1);
                queryValues.add(user.getUsername());
                int result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.UPDATE_ONLINE_FLAG, queryValues, DatabaseConnection.getInstance().getConnection());
                if(result == 1){
                    users.get(0).setOnlineFlag(1);
                }
                user = users.get(0);
            } else {
                user = null;
            }
        }
        response = new RequestEntity("UserDBOperations", "loginResponse", users);
        return response;
    }

    public RequestEntity register(ArrayList<Object> value) {
        int result = -1;
        UserEntity user = null;
        RequestEntity<UserEntity> response = null;
        ArrayList<UserEntity> users = new ArrayList<>();
        if (value != null) {
            user = (UserEntity) value.get(0);
            queryValues = new ArrayList<Object>();

            queryValues.add(user.getUsername());
            queryValues.add(user.getPassword());
            queryValues.add(user.getEmail());
            queryValues.add(user.getOnlineFlag());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.REGISTER_USER_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                user = null;
            } else {
                users.add(user);
            }
        }

        response = new RequestEntity("UserDBOperations", "registerResponse", users);
        return response;

    }
    
    public RequestEntity getAllTodos(ArrayList<Object> value){
        UserEntity userId = (UserEntity) value.get(0);

        RequestEntity<TodoEntity> response = null;
        queryValues.clear();
        queryValues.add(userId.getId());
        ArrayList<TodoEntity> items = DBStatementsExecuter.retrieveTodoData(DatabaseQueries.RETRIEVE_ALL_TODO_LISTS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        if (items == null || items.size() == 0) {
            items = null;
        } 
        response = new RequestEntity("UserDBOperations", "getAllTodosResonse", items);
        return response;
    }
}
