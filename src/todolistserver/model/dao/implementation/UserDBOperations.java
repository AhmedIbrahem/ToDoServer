/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class UserDBOperations {

    ArrayList<Object> queryValues;
  
    
    public UserEntity login(Object user) {
       
        UserEntity test = new UserEntity();
        test.setUsername("test");
        test.setPassword("test2");
        return test;
    }

    
    public int register(Object user) {

        return -1;
    }
}
