/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import todolistserver.model.entities.ItemEntity;

/**
 *
 * @author dell
 */
public class ItemDBOperations {

  

    public ItemEntity addItem(Object itemValue) {
        
        ItemEntity test = (ItemEntity) itemValue;
        return test;
        
    }

    public void updateItem(ItemEntity item) {
    }

}
