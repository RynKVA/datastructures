package org.example.list.stack;

public interface Stack<E> {

    boolean isEmpty();

    E peek();

    E pop();

    E push(E item);

    int search(Object item);
    void clear();

    String toString();
}
