package org.example.list;


import java.util.Arrays;
import java.util.StringJoiner;

public class LinkedList extends AbstractList {

    // simplify add +
    // fix remove +
    // overriding method toString +


    private static class Node {
        int data;
        Node next;
        Node prev;

        public Node(int data) {
            this.data = data;
        }

    }

    private Node head;
    private Node tail;


    @Override
    public void add(int value, int index) {
        validateIndex(index);
        if (size == 0) {
            addInEmptyList(value);
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else {
            addFromHeadOrTail(value, index);
        }
    }

    private void addInEmptyList(int value) {
        Node targetNode = new Node(value);
        head = tail = targetNode;
        size++;
    }

    public void addFirst(int value) {
        Node firstNode = new Node(value);
        head.prev = firstNode;
        firstNode.next = head;
        head = firstNode;
        size++;
    }

    public void addLast(int value) {
        Node lastNode = new Node(value);
        tail.next = lastNode;
        lastNode.prev = tail;
        tail = lastNode;
        size++;
    }

    private void addFromHeadOrTail(int value, int index) {
        Node targetNode = new Node(value);
        Node nodePrev = tail;
        Node nodeNext = head;
        if (index <= size / 2) {
            for (int i = 0; i < index; i++) {
                nodeNext = nodeNext.next;
            }
            nodePrev = nodeNext.prev;
        } else {
            for (int i = size; i > index; i--) {
                nodePrev = nodePrev.prev;
            }
            nodeNext = nodePrev.next;
        }
        nodePrev.next = targetNode;
        targetNode.prev = nodePrev;
        nodeNext.prev = targetNode;
        targetNode.next = nodeNext;
        size++;
    }

    @Override
    public boolean remove(int value) {
        listISEmpty();
        if (indexOf(value) == 0) {
            removeFirst();
            return true;
        } else if (indexOf(value) == size - 1) {
            removeLast(value);
            return true;
        }
        Node node = head;
        for (int i = 0; i < size - 1; i++) {
            node = node.next;
            if (node.data == value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
        }
        return false;
    }

    private void removeFirst() {
        head = head.next;
        head.prev = null;
        size--;
    }

    private void removeLast(int value) {
        Node node = tail;
        tail = node.prev;
        node.prev = null;
        tail.next = null;
        size--;
    }

    public int get(int index) {
        listISEmpty();
        validateIndex(index);
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }


    @Override
    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (value == get(i)) {
                return i;
            }
        }
        return -1;
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
