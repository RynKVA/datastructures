package org.example.list;

public abstract class AbstractList implements List {
    // - В абстрасктный лист выносишь общее из Linked и Array
    // - Создаешь в тестах абстракцию с хорошо прописанными тестами
    //      (интуитивно понятные имена и больше покрытие разных случаев) в которую сможешь передать
    //      конкретную реализацию Листа (что б не дублровать тесты)
    // - посмотреть иерархию исключений
    protected int size;

    @Override
    public void add(int value) {
        add(value, size);
    }

    @Override
    public boolean contains(int value) {
        return indexOf(value) != -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    protected void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of List.");
        }
    }

    /*protected void validateIndexOnAdd(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index out of List.");
        }
    }*/

    protected void listISEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException("List is empty.");
        }
    }
}
