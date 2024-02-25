package org.example.list;


public class LinkedList extends AbstractList implements List {

    // simplify add
    // fix remove

    private int size = 0;

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
    public void add(int value){
        add(value, size);
//        Node node = new Node(value);
//        if (size == 0) {
//            head = tail = node;
//            size++;
//        } else { // add to the end
//            tail.next = node;
//            node.prev = tail;
//            tail = node;
//        }
    }

    @Override
    public void add(int value, int index){
        // - add to the empty list (list size = 0)
        // - add to the end
        // - add to the middle of the list
        // - add to the start (not empty list - add(value, 0)
        validateIndex(index);
        if (size == 0) {
            addInEmptyList(value);
        } else if (index == 0) {
            addFirst(value);
        } else if (index == size) {
            addLast(value);
        } else if (index <= size / 2) {
            addFromHead(value, index);
        } else if (index > size / 2) {
            addFromTail(value, index);
        }
        // getNode() ->
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

    private void addInEmptyList(int value) {
        Node targetNode = new Node(value);
        head = tail = targetNode;
        size++;
    }

    private void addFromHead(int value, int index){
        validateIndex(index);
        Node targetNode = new Node(value);
        Node nodeNext = head;
        Node nodePrev;
        for (int i = 0; i < index; i++) {
            nodeNext = nodeNext.next;
        }
        nodePrev = nodeNext.prev;
        nodePrev.next = targetNode;
        targetNode.prev = nodePrev;
        nodeNext.prev = targetNode;
        targetNode.next = nodeNext;
        size++;
    }

    private void addFromTail(int value, int index){
        validateIndex(index);
        Node targetNode = new Node(value);
        Node nodePrev = tail;
        Node nodeNext;
        for (int i = size; i > index; i--) {
            nodePrev = nodePrev.prev;
        }
        nodeNext = nodePrev.next;
        nodePrev.next = targetNode;
        targetNode.prev = nodePrev;
        nodeNext.prev = targetNode;
        targetNode.next = nodeNext;
        size++;
    }

    @Override
    public boolean contains(int value){
        return indexOf(value) != -1;
    }

    @Override
    public boolean remove(int value){
        if (indexOf(value) == 0) {
            removeFirst(value);
        } else if (indexOf(value)==size-1) {
            removeLast(value);
        }
        Node node = head;
        for (int i = 0; i < size - 1; i++) {
            node = node.next;
            if (node.data == value) {
                Node nodePrev = node.prev;
                Node nodeNext = node.next;
                nodePrev.next = nodeNext;
                nodeNext.prev = nodePrev;
                node.prev = node.next = null;
                size--;
                return true;
            }
        }
        return false;
    }

    private void removeFirst(int valur) {
        Node node = head;
        head = node.next;
        node.next = null;
        head.prev = null;
        size--;
    }

    private void removeLast(int value) {
        Node node= tail;
        tail= node.prev;
        node.prev=null;
        tail.next=null;
        size--;
    }

  /*  private int returnFromHead(int value) throws ListIsEmptyException {
        listIsEmpty();
        Node node = head;
        for (int i = 0; i < size; i++) {
            node = node.next;
            if (node.data == value){
                Node nodePrev= node.prev;
                Node nodeNext= node.next;
                nodePrev.next=nodeNext;
                nodeNext.prev=nodePrev;
                node.prev=node.next=null;
                size--;
                return node.data;
            }
        }
        return value;
    }*/

    private int returnFromTail(int value) {
        Node node = tail;

        return value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public int get(int index){
        validateIndex(index);
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.data;
    }

    @Override
    public int indexOf(int value){
        for (int i = 0; i < size; i++) {
            if (value == get(i)) {
                return i;
            }
        }
        return -1;
    }

    public int[] toArray(){
        int[] arrayOfElements = new int[size];
        for (int i = 0; i < size; i++) {
            arrayOfElements[i] = get(i);
        }
        return arrayOfElements;
    }

    private void validateIndex(int index){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

}
