package org.example.list;


import java.util.Arrays;

public class LinkedList extends AbstractList implements List {

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
    public void add(int value) {
        add(value, size);
    }

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
    public boolean contains(int value) {
        return indexOf(value) != -1;
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
//                Node nodePrev = node.prev;
//                Node nodeNext = node.next;
//                nodePrev.next = nodeNext;
//                nodeNext.prev = nodePrev;
//                node.prev = node.next = null;
                size--;
                return true;
            }
        }
        return false;
    }

    private void removeFirst() {
        head = head.next;
        head.prev = null;
//        Node node = head;
//        head = node.next;
//        node.next = null;
//        head.prev = null;
        size--;
    }

    private void removeLast(int value) {
        Node node = tail;
        tail = node.prev;
        node.prev = null;
        tail.next = null;
        size--;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean isEmpty() {
        return size == 0;
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
        int[] array= new int[size];
        Node node= head;
        for (int i=0; i<size;i++){
            array[i]= node.data;
            node=node.next;
        }
        return Arrays.toString(array);
    }
}
