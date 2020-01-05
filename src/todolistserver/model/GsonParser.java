package todolistserver.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import todolistserver.model.entities.AssignFriendTodoEntity;
import todolistserver.model.entities.ItemEntity;
import todolistserver.model.entities.NotificationEntity;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.TodoEntity;
import todolistserver.model.entities.UserEntity;

/**
 * @author Ibrahim
 */
public class GsonParser {
    public static RequestEntity parseFromJson(String request){
        Gson gson = new Gson();        
        Type requestType = null;
        switch(request.charAt(14)){
            case 'U':
                 requestType = new TypeToken<RequestEntity<UserEntity>>(){}.getType();
                 break;
            case 'I':
                requestType = new TypeToken<RequestEntity<ItemEntity>>(){}.getType();
                break;
            case 'N':
                requestType = new TypeToken<RequestEntity<NotificationEntity>>(){}.getType();
                break;
            case 'T':
                if(request.contains("assignTodo"))
                    requestType = new TypeToken<RequestEntity<AssignFriendTodoEntity>>(){}.getType();
                else
                    requestType = new TypeToken<RequestEntity<TodoEntity>>(){}.getType();
                break;
            case 'z':
                requestType = new TypeToken<RequestEntity<ArrayList<UserEntity>>>(){}.getType();
        }
         
       System.out.println(request);
       return gson.fromJson(request, requestType);
     
    }
    public static String parseToJson(RequestEntity req){
        
        Gson gson = new Gson();
        return gson.toJson(req);
    }
}
