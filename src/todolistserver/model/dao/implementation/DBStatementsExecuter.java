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

/**
 *
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

    public static ArrayList<Object> executeRetrieveStatement(String query, ArrayList<Object> parameters, Connection con) {

        ArrayList<Object> result = new ArrayList<Object>();

        try {
            PreparedStatement pst = prepareStatement(query, parameters, con);

            ResultSet resSet = pst.executeQuery();
            while (resSet.next()) {
                result.add(resSet.getObject(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return result;

    }

}
