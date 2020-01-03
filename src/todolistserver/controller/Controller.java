/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.controller;

import todolistserver.model.GsonParser;
import todolistserver.model.ReflectionClass;
import todolistserver.model.StreamingListner;
import todolistserver.model.entities.RequestEntity;
import todolistserver.model.entities.UserEntity;

/**
 *
 * @author dell
 */
public class Controller {

    public static void handle(String str) throws InstantiationException, IllegalAccessException {

        RequestEntity request = GsonParser.parseFromJson(str);
        RequestEntity returnValue =(RequestEntity) ReflectionClass.getObject(request.getClassName(), request.getOperation(), request.getEntity());
        
        String json = GsonParser.parseToJson(returnValue);

        StreamingListner.getPrintStreamInstance().println(json);

    }
}
