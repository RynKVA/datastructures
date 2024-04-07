package org.example.list.queue;

public interface NewQueue {
    void enqueue(Object value);
    Object dequeue();
    Object peek();
    int size();
    boolean isEmpty();
    boolean contains(Object value);
    void clear();
    String toString();
}
