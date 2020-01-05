/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todolistserver.model;

import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 *
 * @author dell
 */
public class ReflectionClass {

    private static final String PACKAGE_NAME = "todolistserver.model.dao.implementation";

    public static Object getObject(String className, String methodName, Object obj) {
        Object returnValue =null;
        String objectClassName = PACKAGE_NAME + "." + className;
        Class<?> objectClass;
        try {
            objectClass = Class.forName(objectClassName); // convert string classname to class
            Object object = objectClass.newInstance(); // invoke empty constructor
            
            Class<?>[] paramTypes = {ArrayList.class}; //params types 
            Method printObjectMethod = object.getClass().getMethod(methodName, paramTypes);
            returnValue = printObjectMethod.invoke(object, obj); // invoke the method.
            
            
        } catch (Throwable ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

}
