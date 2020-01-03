/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import com.google.gson.Gson;
import java.sql.DriverManager;
import java.util.ArrayList;
import static todolistserver.model.Constants.DATABASE_PASSWORD;
import static todolistserver.model.Constants.DATABASE_USERNAME;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.UserEntity;
import java.sql.Connection;
import java.sql.SQLException;
import todolistserver.model.entities.RequestEntity;

/**
 *
 * @author dell
 */
public class UserDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity<UserEntity> login(Object value) throws SQLException {

        UserEntity user = null;
        RequestEntity<UserEntity> response = null;
        if (value != null) {
            user = (UserEntity) value;

            queryValues.add(user.getUsername());
            queryValues.add(user.getPassword());
            ArrayList<UserEntity> users = DBStatementsExecuter.retrieveUserData(DatabaseQueries.LOGIN_USER_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (users != null &&users.size()!=0) {
                user = users.get(0);
            } else {
                user = null;
            }

        }
        response = new RequestEntity("UserDBOperations", "loginResponse", user);
        return response;
    }

    public int register(Object user) {

        return -1;
    }
}
