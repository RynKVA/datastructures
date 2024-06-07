package com.rynkovoy.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<E> extends AbstractList<E> {
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(E value, int index) {
        validateIndexOnAdd(index);
        Node<E> targetNode = new Node<>(value);
        if (size == 0) {
            head = tail = targetNode;
        } else if (index == 0) {
            head.prev = targetNode;
            targetNode.next = head;
            head = targetNode;
        } else if (index == size) {
            tail.next = targetNode;
            targetNode.prev = tail;
            tail = targetNode;
        } else {
            Node<E> nodeNext = findNode(index);
            Node<E> nodePrev = nodeNext.prev;
            nodePrev.next = targetNode;
            targetNode.prev = nodePrev;
            nodeNext.prev = targetNode;
            targetNode.next = nodeNext;
        }
        size++;
    }

    public void addFirst(E value) {
        add(value, 0);
    }

    public void addLast(E value) {
        add(value, size - 1);
    }

    @Override
    public E remove(int index) {
        E removedValue = get(index);
        if (index == 0) {
            if (size == 1) {
                head = tail = null;
            } else {
                head = head.next;
                head.prev.data = null;
                head.prev = null;
            }
        } else if (index == size - 1) {
            tail = tail.prev;
            tail.next.data = null;
            tail.next = null;
        } else {
            Node<E> targetNode = findNode(index);
            targetNode.data = null;
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
        }
        size--;
        return removedValue;
    }

    @Override
    public void clear() {
        for (Node<E> node = head; node != null; ) {
            Node<E> next = node.next;
            node.data = null;
            node.prev = null;
            node.next = null;
            node = next;
        }
        size = 0;
        head = tail = null;
    }

    @Override
    public E get(int index) {
        validateIndex(index);
        Node<E> targetNode = findNode(index);
        return targetNode.data;
    }

    @Override
    public E set(E value, int index) {
        validateIndex(index);
        Node<E> targetNode = findNode(index);
        E previousValue = targetNode.data;
        targetNode.data = value;
        return previousValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        private Node(E data) {
            this.data = data;
        }
    }

    private Node<E> findNode(int index) {
        validateIndex(index);
        Node<E> nodeFromTail = tail;
        Node<E> nodeFromHead = head;
        if (index <= size / 2) {
            for (int i = 0; i < index; i++) {
                nodeFromHead = nodeFromHead.next;
            }
            return nodeFromHead;
        } else {
            for (int i = size - 1; i > index; i--) {
                nodeFromTail = nodeFromTail.prev;
            }
            return nodeFromTail;
        }
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private Node<E> targetNode;
        private boolean isNextUsed;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            targetNode = current;
            current = current.next;
            isNextUsed = true;
            return targetNode.data;
        }

        @Override
        public void remove() {
            if (!isNextUsed) {
                throw new IllegalStateException("Method next() not used.");
            }
            if (targetNode.prev == null && targetNode.next == null) {
                head = tail = null;
            } else if (targetNode.prev == null) {
                current.prev = null;
                head = current;
            } else if (targetNode.next == null) {
                tail = targetNode.prev;
                tail.next = null;
            } else {
                targetNode.prev.next = current;
                current.prev = targetNode.prev;
            }
            size--;
            isNextUsed = false;
        }
    }
}
