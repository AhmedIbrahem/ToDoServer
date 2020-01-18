package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.StreamingListner;
import todolistserver.model.entities.ComponentEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author Ibrahim
 */
public class ComponentDBOperations {
    
    ArrayList<Object> queryValues = new ArrayList<>();
    
    public RequestEntity addComponent(ArrayList<Object> itemValue) {
        int result = -1;
        ComponentEntity component = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = new ArrayList<>();
        if (itemValue != null) {
            for(int i = 0; i < itemValue.size() ; i++){
                component = (ComponentEntity) itemValue.get(i);
                queryValues = new ArrayList<>();
                queryValues.add(component.getItemId());
                queryValues.add(component.getComponentType());
                queryValues.add(component.getComponentText());
                queryValues.add(component.getFinishedFlag());

                result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
                if (result <= 0) {
                    component = null;         
                }else{
                    componentEntityList.add(component);
                }
            }
            
            queryValues.clear();
            queryValues.add(component.getItemId());
            ArrayList<TodoEntity> todo = DBStatementsExecuter.retrieveTodoData(DatabaseQueries.Retrieve_todo_by_itemid, queryValues, DatabaseConnection.getInstance().getConnection());
            queryValues.clear();
            queryValues.add(todo.get(0).getId());
            ArrayList<UserEntity> collaborators = FriendsDBOperations.getTodoCollaborators(queryValues);
            StreamingListner.syncFriendsUI(collaborators, "Task Notification+" + component.getItemId());
        }
        response = new RequestEntity("ComponentDBOperations", "addComponentResponse", componentEntityList);
        return response;
    }
    
    public  RequestEntity deleteComponent(ArrayList<Object> itemValue) {
        int result = -1;
        ComponentEntity component = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = new ArrayList<>();
        if (itemValue != null) {
            component = (ComponentEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(component.getItemId());
            queryValues.add(component.getComponentId());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result > 0) {
                componentEntityList.add(component);
            }
        }
        response = new RequestEntity("ComponentDBOperations", "deleteComponentResponse", componentEntityList);
        return response;
    }
    
    public RequestEntity getAllComponent(ArrayList<Object> itemValue){
        ComponentEntity component = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = null;
        if (itemValue != null) {
            component = (ComponentEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(component.getItemId());
            componentEntityList = DBStatementsExecuter.retrieveComponentData(DatabaseQueries.RETRIEVE_ALL_COMPONENT_BY_ITEMID_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        }
       response = new RequestEntity("ComponentDBOperations", "getAllComponentResponse", componentEntityList);
       return response; 
    }
    
    public RequestEntity updateComponent(ArrayList<Object> itemValue) {
        int result = -1;
        ComponentEntity component = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = new ArrayList<>();
        if (itemValue != null) {
            for(int i = 0; i < itemValue.size() ; i++){
                component = (ComponentEntity) itemValue.get(i);
                queryValues = new ArrayList<>();              
                queryValues.add(component.getFinishedFlag());
                queryValues.add(component.getComponentId());
                
                result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.UPDATE_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
                if (result <= 0) {
                    component = null;         
                }else{
                    componentEntityList.add(component);
                }
            }
        }
        response = new RequestEntity("ComponentDBOperations", "updateComponentResponse", componentEntityList);
        return response;
    }
     public RequestEntity getAllCheckBoxComponent(ArrayList<Object> itemValue){
        TodoEntity todo = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = null;
        if (itemValue != null) {
            todo =(TodoEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(todo.getId());
            componentEntityList = DBStatementsExecuter.retrieveComponentData(DatabaseQueries.RETRIEVE_ALL_COMPONENT_BY_TODOID_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
        }
       response = new RequestEntity("ComponentDBOperations", "getAllCheckBoxComponentResponse", componentEntityList);
         System.out.println("dddddddd"+response);
       return response; 
    }
}
