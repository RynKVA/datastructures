package org.example.list.queue;

import java.util.StringJoiner;

public class LinkedQueue<E> implements Queue<E> {
    private int size;
    private final int capacity;

    public LinkedQueue() {
        capacity = 10;
    }

    public LinkedQueue(int capacity) {
        this.capacity = capacity;
    }

    private static class Node<E> {
        E element;
        Node<E> next;
    }

    private Node<E> head;
    private Node<E> tail;

    @Override
    public boolean add(E elem) {
        validateMaxSize();
        if (size == 0) {
            addFirst(elem);
            return true;
        } else {
            addNext(elem);
            return true;
        }
    }

    private void addFirst(E elem) {
        Node<E> node = new Node<>();
        head = tail = node;
        node.element = elem;
        size++;
    }

    private void addNext(E elem) {
        Node<E> node = new Node<>();
        tail.next = node;
        tail = node;
        node.element = elem;
        size++;
    }

    @Override
    public E element() {
        emptyQueue();
        return head.element;
    }

    @Override
    public boolean offer(E elem) {
        if (size == 0) {
            addFirst(elem);
            return true;
        } else if (size<capacity){
            addNext(elem);
            return true;
        }else
        return false;
    }

    @Override
    public E peek() {
        if(size == 0) {
            return null;
        }
        return head.element;
    }

    @Override
    public E poll() {
        if (size == 0){
        return null;
        }
        E pollElem= head.element;
        head.element = null;
        head=head.next;
        size--;
        return pollElem;
    }

    @Override
    public E remove() {
        emptyQueue();
        E removeElem = head.element;
        head.element = null;
        head=head.next;
        size--;
        return removeElem;
    }

    private void validateMaxSize() {
        if (size == capacity) {
            throw new IllegalStateException("Queue is full.");
        }
    }

    private void emptyQueue() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
    }
    @Override
    public String toString(){
        StringJoiner joiner= new StringJoiner(", ","[","]");
        Node<E> node= head;
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(node.element));
            node=node.next;
        }
        return joiner.toString();
    }
}
