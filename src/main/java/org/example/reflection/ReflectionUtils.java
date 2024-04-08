package org.example.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectionUtils {
    static Object createNewExemplar(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor[] constructor = clazz.getConstructors();
        for (int i = 0; i < constructor.length; i++) {
            if (constructor[i].getParameterCount() == 0) {
                return constructor[i].newInstance();
            }
        }
        throw new IllegalArgumentException("Class dose not have default constructor! ");
    }
}
