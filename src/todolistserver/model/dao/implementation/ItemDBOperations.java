package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.StreamingListner;
import todolistserver.model.entities.AssignFriendTodoEntity;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class ItemDBOperations {

   ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity addItem(ArrayList<Object> itemValue) {
        int result = -1;
        ItemEntity item = null;
        RequestEntity<ItemEntity> response = null;
        ArrayList<ItemEntity> itemEntityList = new ArrayList<>();
        if (itemValue != null) {
            item = (ItemEntity) itemValue.get(0);
            queryValues = new ArrayList<Object>();
            queryValues.add(item.getTitle());
            queryValues.add(item.getDescription());
            queryValues.add(item.getTodoID());
            queryValues.add(item.getCreatorID());
            queryValues.add(item.getDeadlineDate());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                item = null;
            }else{
                itemEntityList.add(item);
                queryValues.clear();
                queryValues.add(item.getTodoID());
                ArrayList<UserEntity> collaborators = FriendsDBOperations.getTodoCollaborators(queryValues);
                StreamingListner.syncFriendsUI(collaborators, "Item Notification+"+item.getTodoID());
            }
        }
        response = new RequestEntity("ItemDBOperations", "addItemResponse", itemEntityList);
        return response;
    }

    public RequestEntity updateItem(ArrayList<Object> itemValue) {
        int result = -1;
        ItemEntity item = null;
        RequestEntity<ItemEntity> response = null;
        ArrayList<ItemEntity> itemEntityList = new ArrayList<>();
        if (itemValue != null) {
            item = (ItemEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(item.getTitle());
            queryValues.add(item.getDescription());
            queryValues.add(item.getTodoID());
            queryValues.add(item.getCreatorID());
            queryValues.add(item.getDeadlineDate());
            queryValues.add(item.getItemID());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.UPDATE_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                item = null;
            } else {
                itemEntityList.add(item);
                queryValues.clear();
                queryValues.add(item.getTodoID());
                ArrayList<UserEntity> collaborators = FriendsDBOperations.getTodoCollaborators(queryValues);
                StreamingListner.syncFriendsUI(collaborators, "Item Notification+"+item.getTodoID());
            }
        }

        response = new RequestEntity("ItemDBOperations", "updateItemResponse", itemEntityList);
        return response;

    }
    //to delete item , 1- delete item assigned user 2- delete item tasks 3- delete item itself 
    public  RequestEntity deleteItem(ArrayList<Object> itemValue) {
        int resultDeleteCollaborators = -1;
        int resultDeleteItemTasks = -1;
        ItemEntity item = null;
        RequestEntity<ItemEntity> response = null;
        ArrayList<ItemEntity> itemEntityList = new ArrayList<>();
        if (itemValue != null) {
            item = (ItemEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(item.getItemID());
            resultDeleteCollaborators = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_FRIEND_ON_ITEM, queryValues, DatabaseConnection.getInstance().getConnection());
            resultDeleteItemTasks = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_ALL_ITEM_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            int finalResult = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (finalResult > 0 && resultDeleteCollaborators > 0 && resultDeleteItemTasks > 0 ) {
                itemEntityList.add(item);
            }
            queryValues.clear();
            queryValues.add(item.getTodoID());
            ArrayList<UserEntity> collaborators = FriendsDBOperations.getTodoCollaborators(queryValues);
            StreamingListner.syncFriendsUI(collaborators, "Item Notification+"+item.getTodoID());
        }
        response = new RequestEntity("ItemDBOperations", "deleteItemResponse", itemEntityList);
        return response;
    }
    
    public RequestEntity assignItem(ArrayList<Object> value) {
        RequestEntity<Integer> response = null;
        ArrayList<Integer> friendsList = new ArrayList<>();
        if (value != null) {
            AssignFriendTodoEntity itemFriend = (AssignFriendTodoEntity) value.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(itemFriend.getUserName());
            ArrayList<UserEntity> users = DBStatementsExecuter.retrieveUserData(DatabaseQueries.GET_USERID_BY_USERNAME, queryValues, DatabaseConnection.getInstance().getConnection());
            if (users.size() > 0) {
                queryValues.clear();
                queryValues.add(itemFriend.getCurrentUserId());
                queryValues.add(users.get(0).getId());
                int friend = DBStatementsExecuter.isFriend(DatabaseQueries.CHECK_IF_USER_FRIEND, queryValues, DatabaseConnection.getInstance().getConnection());
                if (friend != 0) {
                    queryValues.clear();
                    queryValues.add(itemFriend.getTodoId());
                    queryValues.add(users.get(0).getId());
                    int friendAssigned = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.ASSIGN_FRIEND_TO_iTEM, queryValues, DatabaseConnection.getInstance().getConnection());
                    friendsList.add(friendAssigned);
                }
            }
        }
        response = new RequestEntity("ItemDBOperations", "assignItemResponse", friendsList);
        return response;
    }
       public RequestEntity getItemCollaborators(ArrayList<Object> value) {
        ItemEntity item = null;
        RequestEntity<UserEntity> response = null;
        ArrayList<UserEntity> collaborators = new ArrayList<>();

        if (value != null) {
            item = (ItemEntity) value.get(0);
            queryValues.clear();
            queryValues.add(item.getItemID());
            collaborators = FriendsDBOperations.getItemCollaborators(queryValues);
            System.out.println("size"+collaborators.size());
            if (collaborators != null || !collaborators.isEmpty()) {
            
            }
        }
        response = new RequestEntity("ItemDBOperations", "getItemCollaboratorsResonse", collaborators);
        return response;

    }

}
