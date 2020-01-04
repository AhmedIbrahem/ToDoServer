/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;


import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.AssignFriendTodoEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class TodoListDBOperations  {
    
    ArrayList<Object> queryValues = new ArrayList<>();

    private void addTodo(TodoEntity todo) {

    }

    private void updateTodo(TodoEntity todo) {

    }
    
    private void AssignToTodo(Object value){
        if(value!=null){
            AssignFriendTodoEntity todoFriend = (AssignFriendTodoEntity) value;
            queryValues = new ArrayList<>();
            queryValues.add(todoFriend.getUserName());
            ArrayList<UserEntity> users= DBStatementsExecuter.retrieveUserData(DatabaseQueries.GET_USERID_BY_USERNAME, queryValues, DatabaseConnection.getInstance().getConnection());
            if(users.size()>0){
                queryValues.clear();
                queryValues.add(users.get(0).getId());
                int friend = DBStatementsExecuter.isFriend(DatabaseQueries.CHECK_IF_USER_FRIEND, queryValues, DatabaseConnection.getInstance().getConnection());
                if(friend != 0){
                    
                }
            }
            
        }
    }   

}
