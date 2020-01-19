/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.StreamingListner;
import todolistserver.model.entities.AssignFriendTodoEntity;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoCollaboratorEntity;
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
        RequestEntity<TodoEntity> response = null;
        ArrayList<TodoEntity> toDoEntityList = new ArrayList<>();
        if (value != null) {
            todo = (TodoEntity) value.get(0);

            System.out.println(todo.getTitle());
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
            } else {
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
            queryValues = new ArrayList<>();

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
            } else {
                toDoEntityList.add(todo);
                //NotifyUserCollaborators
                ArrayList<UserEntity> collaborators = FriendsDBOperations.getAllCollaborators(todo);
                StreamingListner.syncFriendsUI(collaborators, "Update Notification");
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
            queryValues = new ArrayList<>();
            queryValues.add(todo.getId());
            ArrayList<UserEntity> collaborators = FriendsDBOperations.getAllCollaborators(todo);
            ArrayList<ItemEntity> items = DBStatementsExecuter.retrieveItemData(DatabaseQueries.RETRIEVE_ALL_ITEMS_QUERY_BY_TODO_ID, queryValues, DatabaseConnection.getInstance().getConnection());
            for (int i = 0; i < items.size(); i++) {
                queryValues.clear();
                queryValues.add(items.get(i).getItemID());
                int userItemResult = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_FRIEND_ON_ITEM, queryValues, DatabaseConnection.getInstance().getConnection());
                int deleteTasks = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_ALL_ITEM_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
                int itemResult = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            }
            queryValues.clear();
            queryValues.add(todo.getId());
            int userTodo = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_FRIEND_ON_TODO, queryValues, DatabaseConnection.getInstance().getConnection());
            int finalResult = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_TODO_LIST_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if(finalResult>0)
                toDoEntityList.add(todo);
                //getUserFriends.
                StreamingListner.syncFriendsUI(collaborators, "Update Notification");
            }
        response  = new RequestEntity("TodoListDBOperations", "deleteTodoResponse", toDoEntityList);
        return response ;
    }

public RequestEntity assignTodo(ArrayList<Object> value) {
        RequestEntity<Integer> response = null;
        ArrayList<Integer> friendsList = new ArrayList<>();
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
                    friendsList.add(friendAssigned);
                }
            }
            response = new RequestEntity("TodoListDBOperations", "assignTodoResponse", friendsList);
        }
        return response;
    }
    
    public RequestEntity getAllItems(ArrayList<Object> value){
        TodoEntity todo = (TodoEntity) value.get(0);

        RequestEntity<TodoEntity> response = null;
        queryValues.clear();
        queryValues.add(todo.getId());
        ArrayList<ItemEntity> items = DBStatementsExecuter.retrieveItemData(DatabaseQueries.RETRIEVE_ALL_ITEMS_BY_TODOID, queryValues, DatabaseConnection.getInstance().getConnection());
        if (items == null || items.size() == 0) {
            items = null;
        } 
        response = new RequestEntity("TodoListDBOperations", "getAllItemsResonse", items);
        return response;
    }
    
    public RequestEntity getTodoCollaborators(ArrayList<Object> value) {
        TodoEntity todo = null;
        RequestEntity<UserEntity> response = null;
        ArrayList<UserEntity> collaborators = new ArrayList<>();

        if (value != null) {
            todo = (TodoEntity) value.get(0);
            queryValues.clear();
            queryValues.add(todo.getId());
            System.out.println(todo.getId());
            collaborators = FriendsDBOperations.getTodoCollaborators(queryValues);
            if (collaborators != null || !collaborators.isEmpty()) {
                System.out.println("size"+collaborators.size());
                for(int i = 0 ;i <collaborators.size();i++){
                    for(int j =0 ;j<StreamingListner.clientsVector.size();j++){
                        if(collaborators.get(i).getId() == StreamingListner.clientsVector.get(j).getId()){
                            collaborators.get(i).setOnlineFlag(1);
                        }else{
                            collaborators.get(i).setOnlineFlag(0);
                        }
                    }
                }
            }
        }
        response = new RequestEntity("TodoListDBOperations", "getToDoCollaboratorsResonse", collaborators);
        return response;

    }
        
     public RequestEntity removeTodoCollaborator(ArrayList<Object> value){
         TodoCollaboratorEntity todoCollaborator = (TodoCollaboratorEntity) value.get(0);

        RequestEntity<TodoCollaboratorEntity> response = null;
        queryValues.clear();
        queryValues.add(todoCollaborator.getTodoID());
        queryValues.add(todoCollaborator.getUserID());
        ArrayList<TodoCollaboratorEntity> responseList = new ArrayList<>();
        int result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.REMOVE_TODO_COLLABORATOR, queryValues, DatabaseConnection.getInstance().getConnection());
        if (result < 1) {
            responseList = null;
        } 
        else{
            queryValues.clear();
            queryValues.add(todoCollaborator.getTodoID());
            ArrayList<TodoEntity> todo= DBStatementsExecuter.retrieveTodoData(DatabaseQueries.RETRIEVE_TODO_DATA_BY_TODOID, queryValues, DatabaseConnection.getInstance().getConnection());
            int creatorID = todo.get(0).getCreatorId();
            responseList.add(todoCollaborator);
            UserEntity collaboratorUser = new UserEntity();
            collaboratorUser.setId(todoCollaborator.getUserID());
            UserEntity creatorUser = new UserEntity();
            creatorUser.setId(creatorID);
            ArrayList<UserEntity> collaborators = new ArrayList<>();
            collaborators.add(creatorUser);
            collaborators.add(collaboratorUser);
            
            StreamingListner.syncFriendsUI(collaborators, "Update Notification");
        }
        response = new RequestEntity("TodoListDBOperations", "removeCollaboratorResponse", responseList);
        return response;
    }
}
