package todolistserver.model;

import com.google.gson.Gson;
import todolistserver.model.entities.RequestEntity;

/**
 * @author Ibrahim
 */
public class GsonParser {
    public static RequestEntity parseFromJson(String request){
        Gson gson = new Gson();
        return gson.fromJson(request, RequestEntity.class);
    }
    public static String parseToJson(RequestEntity req){
        
        Gson gson = new Gson();
        return gson.toJson(req);
    }
}
