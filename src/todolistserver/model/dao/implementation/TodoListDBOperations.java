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

    public RequestEntity addTodo(Object value) {
        int result = -1;
        TodoEntity todo = null;
        RequestEntity<TodoEntity> response = null;
        if (value != null) {
            todo = (TodoEntity) value;
            queryValues = new ArrayList<Object>();

            queryValues.add(todo.getTitle());
            queryValues.add(todo.getColor());
            queryValues.add(todo.getCreatorId());
            queryValues.add(todo.getStatus());
            queryValues.add(todo.getDescription());
            queryValues.add(todo.getAssignDate());
            queryValues.add(todo.getDeadlineDate());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_TODO_LIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                todo = null;
            }
        }

        response = new RequestEntity("TodoListDBOperations", "addTodoResponse", todo);
        return response;

    }

    public void updateTodo(TodoEntity todo) {

    }

    public RequestEntity assignTodo(Object value) {
        RequestEntity<Integer> response = null;
        if (value != null) {
            AssignFriendTodoEntity todoFriend = (AssignFriendTodoEntity) value;
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
                    response = new RequestEntity("TodoListDBOperations", "assignTodoResponse", friendAssigned);
                }
            }
        }
        return response;
    }

}
