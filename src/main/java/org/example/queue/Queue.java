package org.example.queue;

public interface Queue<E> {
    public boolean add(E elem);
    public E element();
    public boolean offer(E elem);
    public E peek();
    public E poll();
    public E remove();
    String toString();
}
