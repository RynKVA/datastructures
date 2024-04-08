package org.example.reflection;

public class Person {
    boolean isCreatedByDefaultConstructor;
    boolean isCreatedByConstructorWithParameters;

   public Person() {
        isCreatedByDefaultConstructor = true;
    }

   public Person(int i) {
        isCreatedByConstructorWithParameters = true;
    }
}
