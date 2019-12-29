/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import todolistserver.model.dao.interfaces.DBOperations;

import todolistserver.model.entities.TodoEntity;

/**
 *
 * @author dell
 */
public class TodoListDBOperations implements DBOperations {

    @Override
    public void doOperation(String flag, DBOperations obj) {
        switch (flag) {
            case "addTodo":
                addTodo((TodoEntity) obj);
                break;
            case "updateTodo":
                updateTodo((TodoEntity) obj);
        }
    }

    private void addTodo(TodoEntity todo) {

    }

    private void updateTodo(TodoEntity todo) {

    }

}
