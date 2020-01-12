/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.controller;

import todolistserver.model.GsonParser;
import todolistserver.model.ReflectionClass;
import todolistserver.model.entities.RequestEntity;

/**
 *
 * @author dell
 */
public class Controller {

    public static String handle(String str) throws InstantiationException, IllegalAccessException {

        RequestEntity request = GsonParser.parseFromJson(str);
        RequestEntity returnValue =(RequestEntity) ReflectionClass.getObject(request.getClassName(), request.getOperation(), request.getEntity());
        
        String json = GsonParser.parseToJson(returnValue);
        System.out.println("Controller "+json);
       return json;
    }
}
