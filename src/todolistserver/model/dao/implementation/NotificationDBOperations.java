/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import java.util.ArrayList;
import todolistserver.model.DatabaseConnection;
import todolistserver.model.DatabaseQueries;
import todolistserver.model.StreamingListner;
import todolistserver.model.entities.NotificationEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class NotificationDBOperations {

    ArrayList<Object> queryValues = new ArrayList<>();

    public RequestEntity sendNotification(ArrayList<Object> notificationValue) {
        int result = -1, result2 = -1;
        NotificationEntity notification = null;
        RequestEntity<NotificationEntity> response = null;
        ArrayList<NotificationEntity> notificationList = new ArrayList<>();

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
            } else {
                queryValues.clear();

                queryValues.add(notification.getHeader());
                queryValues.add(notification.getSenderID());
                ArrayList<NotificationEntity> notifcations = DBStatementsExecuter.retrieveNotifications(DatabaseQueries.GET_NOTIFICATION_ID_BY_NOTIFICATION_HEADER, queryValues, DatabaseConnection.getInstance().getConnection());
                ArrayList<Integer> notificationRecievers = null;
                if (notifcations != null && notifcations.size() != 0) {

                    notificationRecievers = new ArrayList<>();

                    for (int i = 0; i < notification.getNotificationReceivers().size(); i++) {
                        queryValues.clear();

                        queryValues.add(notifcations.get(0).getNotificationID());
                        queryValues.add(notification.getNotificationReceivers().get(i).getReceiverID());
                        result2 = DBStatementsExecuter.executeUpdateStatement(DatabaseQueries.INSERT_NOTIFICATION_RECIEVERS_QUERY, queryValues, DatabaseConnection.getInstance().getConnection());
                        if (result2 > 0) {
                            notificationRecievers.add(notification.getNotificationReceivers().get(i).getReceiverID());
                        }

                    }

                    /*ArrayList<Object> senderList = new ArrayList<>();
                    senderList.add(notifcations.get(0).getSenderID());
                    senderList.add(notifcations.get(0).getNotificationID());
                    ArrayList<Integer> receiversList = DBStatementsExecuter.retrieveNotificationReceivers(DatabaseQueries.RETRIEVE_NOTIFICATION_RECEIVERS, senderList, DatabaseConnection.getInstance().getConnection());*/
                    StreamingListner.sendNotificationMessage(notificationRecievers);

                    notificationList.add(notification);
                }

            }
        }

        //response = new RequestEntity("NotificationDBOperations", "addNotificationResponse", notificationList);
        return null;

    }

    public RequestEntity receiveNotifications(ArrayList<Object> value) {

        UserEntity user = (UserEntity) value.get(0);

        RequestEntity<NotificationEntity> response = null;
        queryValues.clear();
        queryValues.add(user.getId());
        ArrayList<NotificationEntity> notifcations = DBStatementsExecuter.retrieveNotifications(DatabaseQueries.RETRIEVE_USER_NOTIFICATIONS, queryValues, DatabaseConnection.getInstance().getConnection());
        if (notifcations == null || notifcations.size() == 0) {

            notifcations = null;
        }
        response = new RequestEntity("NotificationDBOperations", "receiveNotificationsResponse", notifcations);
        return response;

    }

}
