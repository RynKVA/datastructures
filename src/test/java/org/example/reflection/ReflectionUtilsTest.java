package org.example.reflection;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class ReflectionUtilsTest {

    @Test
    void createNewExemplar() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Object obj = ReflectionUtils.createNewExemplar(Person.class);

        assertNotNull(obj);
        assertEquals(Person.class, obj.getClass());
        Person personTest = (Person) obj;
        assertTrue(personTest.isCreatedByDefaultConstructor);
        assertFalse(personTest.isCreatedByConstructorWithParameters);

    }
}