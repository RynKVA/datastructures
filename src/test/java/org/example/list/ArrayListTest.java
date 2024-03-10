package org.example.list;

class ArrayListTest  extends ListTest {
    @Override
    List<Integer> getList() {
        return new ArrayList<>();
    }
}