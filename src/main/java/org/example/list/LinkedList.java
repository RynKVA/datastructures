package org.example.list;

public class LinkedList implements List {

    private int size = 0;

    private static class Node {
        int data;
        Node next;
        Node prev;

        /*public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }*/

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;
    private Node tail;

    @Override
    public void add(int value) {
        add(value, size);
        /*Node node = new Node(value, null, null);
        if (size == 0) {
            head = tail = node;
            size++;
        } else { // add to the end
            tail.next = node;
            node.prev = tail;
            tail = node;
//            Node nextNode = new Node(value, tail, head.next);
//            tail.prev = nextNode;
        }*/
    }

    @Override
    public void add(int value, int index) {
        // - add to the empty list (list size = 0)
        // - add to the end
        // - add to the middle of the list
        // - add to the start (not empty list - add(0, value)
    }

    @Override
    public boolean contains(int value) {
        return false;
    }

    @Override
    public boolean remove(int value) {
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    private int get(int index) {
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }
}
