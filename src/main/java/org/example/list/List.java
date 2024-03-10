package org.example.list;
public interface List <T> {

    T get(int index);

    int indexOf(T value);

    void add(T value);

    void add(T value, int index);

    boolean contains(T value);

    boolean remove(T value);

    T remove(int index);

    int size();

    boolean isEmpty();

    String toString();


}
