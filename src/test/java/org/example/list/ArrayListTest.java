package org.example.list;

class ArrayListTest  extends ListTest {
//    private final List <Integer> list=getList();
    @Override
    List<Integer> getList() {
        return new ArrayList<>();
    }
}