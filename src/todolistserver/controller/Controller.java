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

/**
 *
 * @author dell
 */
public class Controller {
    
    public static void handle(String str){
        
        RequestEntity  request = GsonParser.parseFromJson(str);
        Object returnValue = ReflectionClass.getObject(request.getClassName(), request.getOperation(), request.getEntity());                
        
        RequestEntity response = new RequestEntity(request.getClassName(), request.getOperation(), returnValue);
        String json = GsonParser.parseToJson(response);
        
        StreamingListner.getPrintStreamInstance().println(json);
        
        
    }
}
