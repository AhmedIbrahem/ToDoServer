/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.dao.interfaces.DBOperations;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class UserDBOperations implements DBOperations {

    ArrayList<Object> queryValues;

    @Override
    public void doOperation(String flag, DBOperations obj) {
        switch (flag) {
            case "login":
                login((UserEntity) obj);
                break;
            case "register":
                register((UserEntity) obj);
        }
    }

    public void login(UserEntity user) {

        queryValues = new ArrayList<Object>();
        queryValues.add(user.getId());
    }

    public void register(UserEntity user) {

    }
}
