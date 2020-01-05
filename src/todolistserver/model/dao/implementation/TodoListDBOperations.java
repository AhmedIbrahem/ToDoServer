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
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class TodoListDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity addTodo(ArrayList<Object> value) {
        int result = -1;
        TodoEntity todo = null;
        ArrayList<TodoEntity> todoList = new ArrayList<>();
        RequestEntity<TodoEntity> response = null;
        ArrayList<TodoEntity> toDoEntityList = new ArrayList<>();
        if (value != null) {
            todo = (TodoEntity) value.get(0);


            queryValues = new ArrayList<>();
            queryValues.add(todo.getTitle());
            queryValues.add(todo.getDescription());
            queryValues.add(todo.getDeadlineDate());
            queryValues.add(todo.getAssignDate());
            queryValues.add(todo.getColor());
            queryValues.add(todo.getCreatorId());
            queryValues.add(todo.getStatus());


            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_TODO_LIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                todo = null;
            }else{
                todoList.add(todo);
            }
             else {
                toDoEntityList.add(todo);
            }
        }


        response = new RequestEntity("TodoListDBOperations", "addTodoResponse", toDoEntityList);
            
        return response;

    }

    public RequestEntity updateTodo(ArrayList<Object> value) {
          int result = -1;
           TodoEntity todo = null;
        RequestEntity<TodoEntity> response = null;
        ArrayList<TodoEntity> toDoEntityList = new ArrayList<>();
        if (value != null) {
            todo = (TodoEntity) value.get(0);
            queryValues = new ArrayList<Object>();
        
            queryValues.add(todo.getTitle());
            queryValues.add(todo.getDescription());
            queryValues.add(todo.getDeadlineDate());
            queryValues.add(todo.getAssignDate());
            queryValues.add(todo.getColor());
            queryValues.add(todo.getStatus());
            queryValues.add(todo.getId());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.UPDATE_TODO_LIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                todo = null;
            }
              else {
                toDoEntityList.add(todo);
            }
        }
        response = new RequestEntity("TodoListDBOperations", "updateTodoResponse", toDoEntityList);
        return response;

    }
     public RequestEntity deleteTodo(ArrayList<Object> value) {
          int result = -1;
           TodoEntity todo = null;
        RequestEntity<TodoEntity> response = null;
        ArrayList<TodoEntity> toDoEntityList = new ArrayList<>();
        if (value != null) {
            todo = (TodoEntity) value.get(0);
            queryValues = new ArrayList<Object>();
            queryValues.add(todo.getId());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_TODO_LIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                todo = null;
            }
              else {
                toDoEntityList.add(todo);
            }
        }
        response = new RequestEntity("TodoListDBOperations", "deleteTodoResponse", toDoEntityList);
        return response;

    }

    public RequestEntity assignTodo(ArrayList<Object> value){
        RequestEntity<Integer> response = null;
        ArrayList<AssignFriendTodoEntity> friendsList = new ArrayList<>();
        if (value != null) {
            AssignFriendTodoEntity todoFriend = (AssignFriendTodoEntity) value.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(todoFriend.getUserName());
            ArrayList<UserEntity> users = DBStatementsExecuter.retrieveUserData(DatabaseQueries.GET_USERID_BY_USERNAME, queryValues, DatabaseConnection.getInstance().getConnection());
            if (users.size() > 0) {
                queryValues.clear();
                queryValues.add(todoFriend.getCurrentUserId());
                queryValues.add(users.get(0).getId());
                int friend = DBStatementsExecuter.isFriend(DatabaseQueries.CHECK_IF_USER_FRIEND, queryValues, DatabaseConnection.getInstance().getConnection());
                if (friend != 0) {
                    queryValues.clear();
                    queryValues.add(todoFriend.getTodoId());
                    queryValues.add(users.get(0).getId());
                    int friendAssigned = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.ASSIGN_FRIEND_TO_TODOLIST, queryValues, DatabaseConnection.getInstance().getConnection());
                    response = new RequestEntity("TodoListDBOperations", "assignTodoResponse", friendsList);
                }
            }
        }
        return response;
    }

}
