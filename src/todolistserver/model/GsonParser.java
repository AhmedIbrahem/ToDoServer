package todolistserver.model;

import com.google.gson.Gson;
import todolistserver.model.entities.RequestEntity;

/**
 * @author Ibrahim
 */
public class GsonParser {
    public static RequestEntity parseGson(String request){
        Gson gson = new Gson();
        return gson.fromJson(request, RequestEntity.class);
    }
}
