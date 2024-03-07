package org.example.list;

import java.util.StringJoiner;

public class LinkedList<T> extends AbstractList<T> {

    // simplify add +
    // fix remove +
    // overriding method toString +


    private class Node {
        T data;
        Node next;
        Node prev;

        public Node(T data) {
            this.data = data;
        }

    }

    private Node head;
    private Node tail;


    @Override
    public void add(T value, int index) {
        validateIndexOnAdd(index);
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

    private void addInEmptyList(T value) {
        Node targetNode = new Node(value);
        head = tail = targetNode;
        size++;
    }

    public void addFirst(T value) {
        Node firstNode = new Node(value);
        head.prev = firstNode;
        firstNode.next = head;
        head = firstNode;
        size++;
    }

    public void addLast(T value) {
        Node lastNode = new Node(value);
        tail.next = lastNode;
        lastNode.prev = tail;
        tail = lastNode;
        size++;
    }

    private void addFromHeadOrTail(T value, int index) {
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
    public boolean remove(T value) {
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
            if (node.data == value) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
                return true;
            }
        }
        return false;
    }


    @Override
    public Object remove(int index) {
        validateIndex(index);
        Object removedValue = get(index);
        if (index == 0){
            removeFirst();
            return removedValue;
        } else if (index == size-1) {
            removeLast();
            return removedValue;
        }else {
            Node targetNode = findNode(index);
            targetNode.prev.next = targetNode.next;
            targetNode.next.prev = targetNode.prev;
            return removedValue;
        }
    }
    private Node findNode(int index){

        Node nodeFromTail = tail;
        Node nodeFromHead = head;
        if (index <= size / 2) {
            for (int i = 0; i < index; i++) {
                nodeFromHead = nodeFromHead.next;
            }
           return nodeFromHead;
        }else {
            for (int i = size; i > index; i--) {
                nodeFromTail = nodeFromTail.prev;
            }
           return nodeFromTail;

        }
    }

    private void removeFirst() {
        head = head.next;
        head.prev = null;
        size--;
    }

    private void removeLast() {
        tail=tail.prev;
        tail.next=null;
        size--;
    }

    public Object get(int index) {
        validateIndex(index);
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }


    @Override
    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (value.equals(get(i))) {
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
