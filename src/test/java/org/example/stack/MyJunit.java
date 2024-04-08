package org.example.stack;
import org.junit.jupiter.api.Test;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyJunit {
    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        runTest(StackClassTest.class);
    }

    static void runTest(Class clazz) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Constructor constructor = clazz.getConstructor();
        Object instance = constructor.newInstance();
        ArrayList testMethods = getTest(clazz);

        for (int i = 0; i < testMethods.size(); i++) {
            Method testMethod = (Method) testMethods.get(i);
            try {
                testMethod.invoke(instance);
                System.out.println("Test: " + testMethod.getName() + " success!");
            } catch (Throwable exception) {
                System.out.println("Test: " + testMethod.getName() + " failed!");
            }
        }
    }
    private static java.util.ArrayList getTest(Class clazz) {
        java.util.ArrayList methodList = new java.util.ArrayList();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                methodList.add(method);
            }
        }
        return methodList;
    }
}
