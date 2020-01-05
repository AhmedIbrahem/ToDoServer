/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.entities.NotificationEntity;
import todolistserver.model.entities.RequestEntity;

/**
 *
 * @author dell
 */
public class NotificationDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();
    

    private RequestEntity sendNotification(ArrayList<Object> notificationValue) {
           int result = -1;
        NotificationEntity notification = null;
        RequestEntity<NotificationEntity> response = null;
        ArrayList<NotificationEntity> notificationList=new ArrayList<>();
       
        if (notificationValue != null) {
            notification = (NotificationEntity) notificationValue.get(0);
            queryValues = new ArrayList<>();
            queryValues.add(notification.getHeader());
            queryValues.add(notification.getText());
            queryValues.add(notification.getSenderID());
            queryValues.add(notification.getNotificationType());

            
            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_NOTIFICATION_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                notification = null;
            }
            else
            {
                notificationList.add(notification);
            }
        }

 
        

        response = new RequestEntity("NotificationDBOperations", "addNotificationResponse", notificationList);
        return response;

    }


 
    private void sendNotification(NotificationEntity notification) {

    }

    private RequestEntity<NotificationEntity> receiveNotifications(ArrayList<Object> value) {

        
        int userID = (int) value.get(0);

        RequestEntity<NotificationEntity> response = null;
        queryValues.clear();
        queryValues.add(userID);
        ArrayList<NotificationEntity> notifcations = DBStatementsExecuter.retrieveNotifications(DatabaseQueries.RETRIEVE_USER_NOTIFICATIONS, queryValues, DatabaseConnection.getInstance().getConnection());
        if (notifcations == null || notifcations.size() == 0) {
            return null;
        } 
        response = new RequestEntity("NotificationDBOperations", "receiveNotificationsResponse", notifcations);
        return response;
        
    }

}
