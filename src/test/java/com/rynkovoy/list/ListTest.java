package com.rynkovoy.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

abstract class ListTest {
    private List<Integer> emptyList;
    private List<Integer> listWithSomeElements;
    private Integer[] array;

    abstract List<Integer> getList();


    @BeforeEach
    void before() {
        emptyList = getList();
        listWithSomeElements = getList();

        listWithSomeElements.add(6);
        listWithSomeElements.add(5);
        listWithSomeElements.add(4);
        listWithSomeElements.add(3);
        listWithSomeElements.add(2);
    }


    @Test
    void whenElementIsAddedInEmptyListItTakesTheFirstPosition() {
        emptyList.add(1);
        Integer[] array = new Integer[]{1};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void whenThreeElementsAreAddedAllOfThemTakesTheNextPosition() {
        emptyList.add(1);
        emptyList.add(2);
        emptyList.add(3);
        Integer[] array = {1, 2, 3};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void elementIsAddedAtIndexZeroInNotEmptyList() {
        listWithSomeElements.add(3, 0);
        array = new Integer[]{3, 6, 5, 4, 3, 2};
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void elementIsAddedAtIndexThree() {
        listWithSomeElements.add(5, 3);
        array = new Integer[]{6, 5, 4, 5, 3, 2};
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void elementIsAddedAtIndexMoreThenSizeExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.add(0, 7));
        assertEquals(exception.getMessage(), "Index: 7 out of bounds [0, 5]");
    }

    @Test
    void checkContainElementInListWhenIsContained() {
        assertTrue(listWithSomeElements.contains(6));
    }

    @Test
    void checkContainElementInListWhenIsNotContained() {
        assertFalse(listWithSomeElements.contains(7));
    }

    @Test
    void whenElementIsRemovedNextElementsMovingBackAndReturnTrue() {
        array = new Integer[]{6, 4, 3, 2};
        assertTrue(listWithSomeElements.remove(Integer.valueOf(5)));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());

    }

    @Test
    void removeElementFromTheFirstPosition() {
        array = new Integer[]{5, 4, 3, 2};
        assertTrue(listWithSomeElements.remove((Integer) 6));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void removeElementFromTheLastPosition() {
        array = new Integer[]{6, 5, 4, 3};
        assertTrue(listWithSomeElements.remove((Integer) 2));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void whenRemoveElementWhichNoteContainedReturnFalse() {
        array = new Integer[]{6, 5, 4, 3, 2};
        assertFalse(listWithSomeElements.remove((Integer) 7));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());

    }

    @Test
    void whenElementIsRemovedFromEmptyListExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(5));
        assertEquals(exception.getMessage(), "List is empty");
    }

    @Test
    void whenRemovedByFirstIndexRemoveFirstElementInListAndReturnElement() {
        array = new Integer[]{5, 4, 3, 2};
        assertEquals(6, listWithSomeElements.remove(0));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void whenRemovedByLastIndexRemovingLastElementAndReturnElement() {
        array = new Integer[]{6, 5, 4, 3};
        assertEquals(2, listWithSomeElements.remove(4));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void whenRemovedByIndexThreeRemovingElementOnPositionThreeAndReturnElement() {
        array = new Integer[]{6, 5, 4, 2};
        assertEquals(3, listWithSomeElements.remove(3));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void whenRemovedByIndexInEmptyListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(0));
        assertEquals(exception.getMessage(), "List is empty");
    }

    @Test
    void whenRemovedByIndexWhichOutOfListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.remove(5));
        assertEquals(exception.getMessage(), "Index: 5 out of bounds [0, 5]");
    }

    @Test
    void whenRemoveByValueInListWithOneElementThenDeleteElementAndReturnRemovedElementValue() {
        emptyList.add(3);

        assertEquals(1, emptyList.size());

        emptyList.remove((Integer) 3);

        assertEquals(0, emptyList.size());
    }

    @Test
    void checkTrueToSize() {
        assertEquals(5, listWithSomeElements.size());
    }

    @Test
    void checkListIsEmptyReturnTrue() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void checkListIsEmptyReturnFalse() {
        assertFalse(listWithSomeElements.isEmpty());
    }

    @Test
    void getValueFromIndex() {
        assertEquals(4, listWithSomeElements.get(2));
        assertEquals(2, listWithSomeElements.get(4));
    }

    @Test
    void getValueFromIndexWhenListIsEmpty() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));
        assertEquals(exception.getMessage(), "List is empty");
    }

    @Test
    void getValueFromIndexOutOfListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.get(6));
        assertEquals(exception.getMessage(), "Index: 6 out of bounds [0, 5]");
    }

    @Test
    void returnIndexOfValueWhenIsContained() {
        assertEquals(4, listWithSomeElements.indexOf(2));
    }

    @Test
    void returnIndexOfValueWhenIsNotContained() {
        assertEquals(-1, listWithSomeElements.indexOf(7));
    }

    @Test
    void testLastIndexOfReturnElement() {
        assertEquals(2, listWithSomeElements.lastIndexOf(4));
    }

    @Test
    void testLastIndexOfWhenElementIsNotContained() {
        assertEquals(-1, listWithSomeElements.indexOf(7));
    }

    @Test
    void testSetMethodOnEmptyListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.set(6, 0));
        assertEquals(exception.getMessage(), "List is empty");
    }

    @Test
    void testSetMethodReturnSwapElement() {
        int[] array = {6, 5, 10, 3, 2};

        assertEquals(4, listWithSomeElements.set(10, 2));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void testSetMethodWithIndexOutOfBoundsExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.set(6, 10));
        assertEquals(exception.getMessage(), "Index: 10 out of bounds [0, 5]");
    }

    @Test
    void whenUseCLearMethodThenSizeStandZero() {
        listWithSomeElements.clear();
        assertEquals(0, listWithSomeElements.size());
    }

    @Test
    void testIteratorMethodNextOnListWithSomeElements() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        assertEquals(6, iterator.next());
        assertEquals(5, iterator.next());
        assertEquals(4, iterator.next());
        assertEquals(3, iterator.next());
        assertEquals(2, iterator.next());
    }

    @Test
    void testIteratorMethodNextWhenNoNextElementExpectNoSuchElementException() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        for (int i = 0; i < 5; i++) {
            iterator.next();
        }

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());

    }

    @Test
    void testIteratorMethodNextOnEmptyListExpectNoSuchElementException() {
        Iterator<Integer> iterator = emptyList.iterator();

        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator::next);
        assertEquals("No next element.", exception.getMessage());
    }

    @Test
    void testIteratorMethodHasNextOnListWithSomeElementsReturnTrue() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertTrue(iterator.hasNext());
    }

    @Test
    void testIteratorMethodHasNextWhenNoNextElementReturnFalse() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        for (int i = 0; i < 5; i++) {
            iterator.next();
        }

        assertFalse(iterator.hasNext());
    }

    @Test
    void testIteratorMethodHasNextOnEmptyListReturnFalse() {
        Iterator<Integer> iterator = emptyList.iterator();

        assertFalse(iterator.hasNext());
    }

    @Test
    void removeDeleteElementWhichCalledInIteratorMethodNext() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        int[] array = new int[]{5, 4, 3, 2};

        assertEquals(6, iterator.next());
        iterator.remove();
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());
    }

    @Test
    void whenIteratorMethodRemoveWithNoteUsedMethodNextExpectIllegalStateException() {
        Iterator<Integer> iterator = emptyList.iterator();

        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals(exception.getMessage(), "Method next() not used.");

    }

    @Test
    void whenUseIteratorMethodRemoveAfterAlreadyUsedItsExpectIllegalStateException() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        iterator.next();
        iterator.remove();
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals(exception.getMessage(), "Method next() not used.");

    }

    @Test
    void whenUsedIteratorMethodRemoveAfterUsingNextThenListStandEmpty() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        while (iterator.hasNext()) {
            iterator.next();
            iterator.remove();
        }
        assertEquals(0, listWithSomeElements.size());
    }

    @Test
    void whenUsedIteratorMethodRemoveAfterUsingNextInListWithOneElementsThenListStandEmpty() {
        emptyList.add(1);
        Iterator<Integer> iterator = emptyList.iterator();

        assertEquals(1, iterator.next());

    }

    @Test
    void whenUsedIteratorMethodRemoveAfterUsingNextOnLastElementThenSizeDecreased() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        while (iterator.hasNext()) {
            iterator.next();
        }
        iterator.remove();
        assertEquals(4, listWithSomeElements.size());
    }

    @Test
    void whenUsedIteratorMethodRemoveOnThirdElementThenSizeDecreased() {
        Iterator<Integer> iterator = listWithSomeElements.iterator();

        iterator.next();
        iterator.next();
        iterator.next();

        iterator.remove();
        assertEquals(4, listWithSomeElements.size());
    }
}