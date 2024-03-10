package org.example.stack;

public interface Stack<E> {

    boolean isEmpty();

    E peek();

    E pop();

    E push(E item);

    int search(Object o);
}
