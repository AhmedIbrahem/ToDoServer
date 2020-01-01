/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import todolistserver.model.entities.UserEntity;


/**
 *
 * @author dell
 */
public class ReflectionClass {

    private static String packageName = "todolistserver.model.dao.implementation";

    public static void getObject(String className, String methodName, UserEntity obj) {
        String objectClassName = packageName + "." + className;
        Class<?> objectClass;
        try {
            objectClass = Class.forName(objectClassName); // convert string classname to class
            Object object = objectClass.newInstance(); // invoke empty constructor
            
         Class<?>[] paramTypes = {Object.class};
        Method printDogMethod = object.getClass().getMethod(methodName, paramTypes);
            printDogMethod.invoke(object, obj);
    

        } catch (Throwable ex) {
            ex.printStackTrace();
        }

    }

}
