package todolistserver.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import todolistserver.model.entities.AssignFriendTodoEntity;
import todolistserver.model.entities.ComponentEntity;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.NotificationEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 * @author Ibrahim
 */
public class GsonParser {

    public static RequestEntity parseFromJson(String request) {
        Gson gson = new Gson();
        Type requestType = null;
        switch (request.charAt(14)) {
            case 'U':
                requestType = new TypeToken<RequestEntity<UserEntity>>() {
                }.getType();
                break;
            case 'I':
                requestType = new TypeToken<RequestEntity<ItemEntity>>() {
                }.getType();
                break;
            case 'N':
                if (request.contains("receiveNotifications")) {
                    requestType = new TypeToken<RequestEntity<UserEntity>>() {
                    }.getType();
                } else {
                    requestType = new TypeToken<RequestEntity<NotificationEntity>>() {
                    }.getType();
                }
                break;
            case 'T':
                if (request.contains("assignTodo")) {
                    requestType = new TypeToken<RequestEntity<AssignFriendTodoEntity>>() {
                    }.getType();
                
                } else {
                    requestType = new TypeToken<RequestEntity<TodoEntity>>() {
                    }.getType();
                }

                 /*else if (request.contains("getTodoCollaborators")) {
                    requestType = new TypeToken<RequestEntity<UserEntity>>() {
                    }.getType();*/
                break;
            case 'C':
                 if (request.contains("getAllCheckBoxComponent")) {
                        requestType = new TypeToken<RequestEntity<TodoEntity>>() {
                        }.getType();
                    }
                 else{
                requestType = new TypeToken<RequestEntity<ComponentEntity>>() {
                }.getType();
                 }
                break;
        }

        System.out.println("GsonParser "+request);
        return gson.fromJson(request, requestType);

    }

    public static String parseToJson(RequestEntity req) {

        Gson gson = new Gson();
        return gson.toJson(req);
    }
}
