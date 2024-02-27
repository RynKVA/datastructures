package org.example.list;

public class AbstractList {
    // - В абстрасктный лист выносишь общее из Linked и Array
    // - Создаешь в тестах абстракцию с хорошо прописанными тестами
    //      (интуитивно понятные имена и больше покрытие разных случаев) в которую сможешь передать
    //      конкретную реализацию Листа (что б не дублровать тесты)
    // - посмотреть иерархию исключений
    protected int size;
    protected void validateIndex(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
    }

    protected void listISEmpty() {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
