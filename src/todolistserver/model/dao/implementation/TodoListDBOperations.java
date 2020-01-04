/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;


import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;

/**
 *
 * @author dell
 */
public class TodoListDBOperations  {
     ArrayList<Object> queryValues = new ArrayList<>();


    
    private RequestEntity addTodo(Object value) {
          int result = -1;
        TodoEntity todo=null;
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
            if(result<=0)
                todo=null;
        }

        response = new RequestEntity("TodoListDBOperations", "addTodoResponse", todo);
        return response;

    }

    private void updateTodo(TodoEntity todo) {

    }

}
