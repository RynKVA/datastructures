package org.example.list;

public class LinkedListTest extends ListTest {

    @Override
    public List<Integer > getList() {
        return new LinkedList<>();
    }
}
