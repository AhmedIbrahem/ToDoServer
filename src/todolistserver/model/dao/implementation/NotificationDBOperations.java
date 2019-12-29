/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model.dao.implementation;

import todolistserver.model.dao.interfaces.DBOperations;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.NotificationEntity;

/**
 *
 * @author dell
 */
public class NotificationDBOperations implements DBOperations {

    @Override
    public void doOperation(String flag, DBOperations obj) {
        switch (flag) {
            case "sendNotification":
                sendNotification((NotificationEntity) obj);
                break;
            case "receiveNotifications":
                receiveNotifications();
        }
    }

    private void sendNotification(NotificationEntity notification) {

    }

    private void receiveNotifications() {

    }

}
