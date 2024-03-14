package org.example.list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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
    void elementIsAddedAtIndexMoreThanSizeExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.add(0, 7));
        assertEquals(exception.getMessage(), "Index out of List.");
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
    void whenRemoveElementWitchNoteContainedReturnFalse() {
        array = new Integer[]{6, 5, 4, 3, 2};
        assertFalse(listWithSomeElements.remove((Integer) 7));
        assertEquals(Arrays.toString(array), listWithSomeElements.toString());

    }

    @Test
    void whenElementIsRemovedFromEmptyListExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(5));
        assertEquals(exception.getMessage(), "Index out of List.");
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
        assertEquals(exception.getMessage(), "Index out of List.");
    }

    @Test
    void whenRemovedByIndexWhichOutOfListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.remove(5));
        assertEquals(exception.getMessage(), "Index out of List.");
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
        assertEquals(exception.getMessage(), "Index out of List.");
    }

    @Test
    void getValueFromIndexOutOfListExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> listWithSomeElements.get(6));
        assertEquals(exception.getMessage(), "Index out of List.");
    }

    @Test
    void returnIndexOfValueWhenIsContained() {
        assertEquals(4, listWithSomeElements.indexOf(2));
    }

    @Test
    void returnIndexOfValueWhenIsNotContained() {
        assertEquals(-1, listWithSomeElements.indexOf(7));
    }
}