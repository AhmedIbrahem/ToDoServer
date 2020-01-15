package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.ComponentEntity;
import todolistserver.model.entities.RequestEntity;

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
            component = (ComponentEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(component.getItemId());
            queryValues.add(component.getComponentType());
            queryValues.add(component.getComponentText());
            queryValues.add(component.getFinisheFlag());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_COMPONENT_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                component = null;         
            }else{
                componentEntityList.add(component);
            }
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
        int result = -1;
        ComponentEntity component = null;
        RequestEntity<ComponentEntity> response = null;
        ArrayList<ComponentEntity> componentEntityList = new ArrayList<>();
        if (itemValue != null) {
            component = (ComponentEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(component.getItemId());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.RETRIEVE_ALL_COMPONENT_BY_ITEMID_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result > 0) {
                componentEntityList.add(component);
            }
        }
       response = new RequestEntity("ComponentDBOperations", "getAllComponentResponse", componentEntityList);
       return response; 
    }
}
