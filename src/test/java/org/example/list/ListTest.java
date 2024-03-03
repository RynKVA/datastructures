package org.example.list;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ListTest {
    private List list;
    private int[] array;

    ListTest(List list){
        this.list=list;
    }


    @Test
    void whenElementIsAddedInEmptyListItTakesTheFirstPosition() {
        list.add(1);
        int[] array = new int[]{1};
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void whenThreeElementsAreAddedAllOfThemTakesTheNextPosition() {
        list.add(1);
        list.add(2);
        list.add(3);
        int[] array = {1, 2, 3};
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void elementIsAddedAtIndexZeroInNotEmptyList() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(3, 0);
        array = new int[]{3, 6, 5, 4, 3, 2};
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void elementIsAddedAtIndexThree() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        list.add(5, 3);
        array = new int[]{6, 5, 4, 5, 3, 2};
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void elementIsAddedAtIndexMoreThanSizeExpectIndexOutOFBoundsException() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> list.add(0, 7));
        assertEquals(exception.getMessage(),"Index out of List.");
    }

    @Test
    void checkContainElementInListWhenIsContained() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertTrue(list.contains(6));
    }

    @Test
    void checkContainElementInListWhenIsNotContained() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertFalse(list.contains(7));
    }

    @Test
    void whenElementIsRemovedNextElementsMovingBackAndReturnTrue() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        array = new int[]{6, 4, 3, 2};
        assertTrue(list.remove(5));
        assertEquals(Arrays.toString(array), list.toString());

    }

    @Test
    void removeElementFromTheFirstPosition() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        array = new int[]{5, 4, 3, 2};
        assertTrue(list.remove(6));
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void removeElementFromTheLastPosition() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        array = new int[]{6, 5, 4, 3};
        assertTrue(list.remove(2));
        assertEquals(Arrays.toString(array), list.toString());
    }

    @Test
    void whenRemoveElementWitchNoteContainedReturnFalse() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        array = new int[]{6, 5, 4, 3, 2};
        assertFalse(list.remove(7));
        assertEquals(Arrays.toString(array), list.toString());

    }

    @Test
    void whenElementIsRemovedFromEmptyListExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> list.remove(5));
        assertEquals(exception.getMessage(),"List is empty.");
    }

    @Test
    void checkTrueToSize() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertEquals(5, list.size());
    }

    @Test
    void checkListIsEmptyReturnTrue() {
        assertTrue(list.isEmpty());
    }

    @Test
    void checkListIsEmptyReturnFalse() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertFalse(list.isEmpty());
    }

    @Test
    void getValueFromIndex() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertEquals(4, list.get(2));
        assertEquals(2, list.get(4));
    }

    @Test
    void getValueFromIndexWhenListIsEmpty() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(0));
        assertEquals(exception.getMessage(),"List is empty.");
    }

    @Test
    void getValueFromIndexOutOfListExpectIndexOutOfBoundsException() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> list.get(6));
        assertEquals(exception.getMessage(),"Index out of List.");
    }

    @Test
    void returnIndexOfValueWhenIsContained() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertEquals(4, list.indexOf(2));
    }

    @Test
    void returnIndexOfValueWhenIsNotContained() {
        list.add(6);
        list.add(5);
        list.add(4);
        list.add(3);
        list.add(2);
        assertEquals(-1, list.indexOf(7));
    }
}