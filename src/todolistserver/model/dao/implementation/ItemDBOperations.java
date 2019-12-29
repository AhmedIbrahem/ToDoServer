/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import todolistserver.model.dao.interfaces.DBOperations;
import todolistserver.model.entities.ItemEntity;

/**
 *
 * @author dell
 */
public class ItemDBOperations implements DBOperations {

    @Override
    public void doOperation(String flag, DBOperations obj) {
        switch (flag) {
            case "addItem":
                addItem((ItemEntity) obj);
                break;
            case "updateItem":
                updateItem((ItemEntity) obj);
        }
    }

    public void addItem(ItemEntity item) {
    }

    public void updateItem(ItemEntity item) {
    }

}
