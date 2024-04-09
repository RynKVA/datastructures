package org.example.list;

import java.util.StringJoiner;

public class LinkedList<E> extends AbstractList<E> {

    private class Node {
        E data;
        Node next;
        Node prev;

        public Node(E data) {
            this.data = data;
        }

    }

    private Node head;
    private Node tail;


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

    private void addInEmptyList(E value) {
        Node targetNode = new Node(value);
        head = tail = targetNode;
        size++;
    }

    public void addFirst(E value) {
        Node firstNode = new Node(value);
        head.prev = firstNode;
        firstNode.next = head;
        head = firstNode;
        size++;
    }

    public void addLast(E value) {
        Node lastNode = new Node(value);
        tail.next = lastNode;
        lastNode.prev = tail;
        tail = lastNode;
        size++;
    }

    private void addToTheMiddle(E value, int index) {
        Node targetNode = new Node(value);
        Node nodeNext = findNode(index);
        Node nodePrev = nodeNext.prev;
        nodePrev.next = targetNode;
        targetNode.prev = nodePrev;
        nodeNext.prev = targetNode;
        targetNode.next = nodeNext;
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
        Node node = head;
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
            Node targetNode = findNode(index);
            targetNode.data = null;
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
            size--;
            return removedValue;
        }
    }

    private Node findNode(int index) {
        validateIndex(index);
        Node nodeFromTail = tail;
        Node nodeFromHead = head;
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
        head = head.next;
        head.prev.data = null;
        head.prev = null;
        size--;
    }

    private void removeLast() {
        tail = tail.prev;
        tail.next.data = null;
        tail.next = null;
        size--;
    }

    @Override
    public void clear() {
        for (Node node = head; node != null; ) {
            Node next = node.next;
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
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    @Override
    public E set(E value, int index) {
        validateIndex(index);
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        E previousValue = node.data;
        node.data = value;
        return previousValue;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", "[", "]");
        Node node = head;
        for (int i = 0; i < size; i++) {
            joiner.add(String.valueOf(node.data));
            node = node.next;
        }
        return joiner.toString();
    }
}
