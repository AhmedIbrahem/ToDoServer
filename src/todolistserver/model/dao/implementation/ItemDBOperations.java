/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.RequestEntity;

/**
 *
 * @author dell
 */
public class ItemDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity addItem(Object itemValue) {

        int result = -1;
        ItemEntity item = null;
        RequestEntity<ItemEntity> response = null;
        if (itemValue != null) {
            item = (ItemEntity) itemValue;
            queryValues = new ArrayList<Object>();

            queryValues.add(item.getTitle());
            queryValues.add(item.getDescription());
            queryValues.add(item.getTodoID());
            queryValues.add(item.getCreatorID());
            queryValues.add(item.getDeadlineDate());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                item = null;
            }
        }

        response = new RequestEntity("ItemDBOperations", "addItemResponse", item);
        return response;

    }

    public void updateItem(ItemEntity item) {
    }

}
