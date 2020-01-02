/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 * @author dell
 */
public abstract class DBStatementsExecuter {

    private static PreparedStatement prepareStatement(String query, ArrayList<Object> parameters, Connection con) {
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            for (int i = 0; i < parameters.size(); i++) {
                pst.setObject((i + 1), parameters.get(i));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return pst;

    }

    public static void executeUpdateStatement(String query, ArrayList<Object> parameters, Connection con) {

        try {
            PreparedStatement pst = prepareStatement(query, parameters, con);
            pst.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    private static ResultSet executeRetrieveStatement(String query, ArrayList<Object> parameters, Connection con) {
        ResultSet resSet = null;
        try {
            PreparedStatement pst = prepareStatement(query, parameters, con);
            resSet = pst.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return resSet;
    }
    public static ArrayList<UserEntity> retrieveUserData(String query, ArrayList<Object> parameters, Connection con){
            ResultSet set = executeRetrieveStatement(query, parameters, con);
            ArrayList<UserEntity> list = new ArrayList<>();
        try {
            while(set.next()){
                list.add(new UserEntity(set.getInt(1), set.getString(2), set.getString(3), set.getString(4), set.getInt(5)));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBStatementsExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static ArrayList<TodoEntity> retrieveTodoData(String query, ArrayList<Object> parameters, Connection con){
            ResultSet set = executeRetrieveStatement(query, parameters, con);
            ArrayList<TodoEntity> list = new ArrayList<>();
        try {
            while(set.next()){
                list.add(new TodoEntity(set.getInt(1), set.getString(2), set.getInt(3), set.getInt(4), 
                        set.getString(5), set.getString(6), set.getDate(7), set.getDate(8)));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBStatementsExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
    public static ArrayList<ItemEntity> retrieveItemData(String query, ArrayList<Object> parameters, Connection con){
            ResultSet set = executeRetrieveStatement(query, parameters, con);
            ArrayList<ItemEntity> list = new ArrayList<>();
        try {
            while(set.next()){
                list.add(new ItemEntity(set.getInt(1), set.getString(2), set.getString(3), set.getInt(4), set.getInt(5), set.getDate(6)));
            }
            set.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBStatementsExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     

}
