package org.example.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.StringJoiner;

public class LinkedList<E> extends AbstractList<E> {

    private Node<E> head;
    private Node<E> tail;


    @Override
    public void add(E value, int index) {
        validateIndexOnAdd(index);
        if (size == 0) {
            addInEmptyList(value);
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            addToTheMiddle(value, index);
        }
    }

    public void addFirst(E value) {
        Node<E> firstNode = new Node<>(value);
        head.prev = firstNode;
        firstNode.next = head;
        head = firstNode;
        size++;
    }

    public void addLast(E value) {
        Node<E> lastNode = new Node<>(value);
        tail.next = lastNode;
        lastNode.prev = tail;
        tail = lastNode;
        size++;
    }


    @Override
    public boolean remove(E value) {
        if (indexOf(value) == 0) {
            removeFirst();
            return true;
        } else if (indexOf(value) == size - 1) {
            removeLast();
            return true;
        }
        Node<E> node = head;
        for (int i = 0; i < size - 1; i++) {
            node = node.next;
            if (value.equals(node.data)) {
                node.data = null;
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
        }
        return false;
    }


    @Override
    public E remove(int index) {
        validateIndex(index);
        E removedValue = get(index);
        if (index == 0) {
            removeFirst();
            return removedValue;
        } else if (index == size - 1) {
            removeLast();
            return removedValue;
        } else {
            Node<E> targetNode = findNode(index);
            targetNode.data = null;
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
            size--;
            return removedValue;
        }
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
        return (E) targetNode.data;
    }

    @Override
    public E set(E value, int index) {
        validateIndex(index);
        Node<E> node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E previousValue = (E) node.data;
        node.data = value;
        return previousValue;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node<E> node = head;
        for (E value : this) {
            joiner.add(value.toString());
            node = node.next;
        }
        return joiner.toString();
    }

    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        private Node(E data) {
            this.data = data;
        }

    }

    private void addInEmptyList(E value) {
        Node<E> targetNode = new Node<>(value);
        head = tail = targetNode;
        size++;
    }

    private void addToTheMiddle(E value, int index) {
        Node<E> targetNode = new Node<>(value);
        Node<E> nodeNext = findNode(index);
        Node<E> nodePrev = nodeNext.prev;
        nodePrev.next = targetNode;
        targetNode.prev = nodePrev;
        nodeNext.prev = targetNode;
        targetNode.next = nodeNext;
        size++;
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

    private void removeFirst() {
        if (size > 1) {
            head = head.next;
            head.prev.data = null;
            head.prev = null;
            size--;
        }else {
            head = tail = null;
            size--;
        }
    }

    private void removeLast() {
        tail = tail.prev;
        tail.next.data = null;
        tail.next = null;
        size--;
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<?> current = head;
        private int position;
        private int useFlag;
        private int removeUsed = 0;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        @SuppressWarnings("unchecked")
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No next element.");
            }
            E value = (E) current.data;
            current = current.next;
            useFlag = 1;
            position++;
            return value;
        }

        @Override
        public void remove() {
            if (useNext() && removeUsed == 0) {
                LinkedList.this.remove(position - 1);
                removeUsed++;
                position--;
                useFlag = 0;
            } else {
                throw new IllegalStateException("Method next() not used.");
            }
            removeUsed--;
        }

        private boolean useNext() {
            return useFlag != 0;
        }
    }


}
