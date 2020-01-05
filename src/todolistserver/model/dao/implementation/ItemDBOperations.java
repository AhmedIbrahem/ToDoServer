/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.sql.Array;
import java.util.ArrayList;
import javax.swing.text.html.parser.Entity;
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
            } else {
                itemEntityList.add(item);
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
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.UPDATE_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                item = null;
            } else {
                itemEntityList.add(item);
            }
        }

        response = new RequestEntity("ItemDBOperations", "UpdateItemResponse", itemEntityList);
        return response;

    }

    public  RequestEntity deleteItem(ArrayList<Object> itemValue) {
        int result = -1;
        ItemEntity item = null;
        RequestEntity<ItemEntity> response = null;
        ArrayList<ItemEntity> itemEntityList = new ArrayList<>();
        if (itemValue != null) {
            item = (ItemEntity) itemValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(item.getItemID());
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_FRIEND_ON_ITEM, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result > 0) {
                int finalResult = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.DELETE_ITEM_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
                if (finalResult > 0) {
                    itemEntityList.add(item);
                }
            }
        }
        response = new RequestEntity("ItemDBOperations", "UpdateItemResponse", itemEntityList);
        return response;

    }

}
