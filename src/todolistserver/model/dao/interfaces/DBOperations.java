/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.interfaces;

/**
 *
 * @author dell
 */
public interface DBOperations{
          
    void doOperation(String flag,DBOperations obj);
}