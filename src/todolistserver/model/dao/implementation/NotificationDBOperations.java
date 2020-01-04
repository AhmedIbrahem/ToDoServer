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

    private RequestEntity addNotification(Object notificationValue) {
        int result = -1;
        NotificationEntity notification = null;
        RequestEntity<NotificationEntity> response = null;
        if (notificationValue != null) {
            notification = (NotificationEntity) notificationValue;
            queryValues = new ArrayList<>();

            queryValues.add(notification.getHeader());
            queryValues.add(notification.getText());
            queryValues.add(notification.getSenderID());
            queryValues.add(notification.getNotificationType());

            result = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_NOTIFICATION_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
            if (result <= 0) {
                notification = null;
            }
        }

        response = new RequestEntity("NotificationDBOperations", "addNotificationResponse", notification);
        return response;

    }

    private void sendNotification(NotificationEntity notification) {

    }

    private void receiveNotifications() {

    }

}
