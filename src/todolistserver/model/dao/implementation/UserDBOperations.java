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

/**
 *
 * @author dell
 */
public class UserDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public UserEntity login(Object value) {

        UserEntity user = null;
        if (value != null) {
            user = (UserEntity) value;            
            queryValues.add(user.getUsername());
            queryValues.add(user.getPassword());
            user = DBStatementsExecuter.retrieveUserData(DatabaseQueries.LOGIN_USER_QUERY, queryValues, DatabaseConnection.getInstance().getConnection()).get(0);            
        }

        return user;
    }

    public int register(Object user) {

        return -1;
    }
}
