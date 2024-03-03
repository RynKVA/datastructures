package org.example.list;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractListTest {
    
    List emptyList;
    List listWIthFiveElement;

    private int[] array;

    @BeforeEach
    public void before() {
        emptyList = getList();
        
        listWIthFiveElement = getList();
        listWIthFiveElement.add(1);
        
    }
    
    public abstract List getList();
    
    @Test
    void whenElementIsAddedInEmptyListItTakesTheFirstPosition() {
        emptyList.add(1);
        int[] array = new int[]{1};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void whenThreeElementsAreAddedAllOfThemTakesTheNextPosition() {
        emptyList.add(1);
        emptyList.add(2);
        emptyList.add(3);
        int[] array = {1, 2, 3};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void elementIsAddedAtIndexZeroInNotEmptyList() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        emptyList.add(3, 0);
        array = new int[]{3, 6, 5, 4, 3, 2};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void elementIsAddedAtIndexThree() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        emptyList.add(5, 3);
        array = new int[]{6, 5, 4, 5, 3, 2};
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void elementIsAddedAtIndexMoreThanSizeExpectIndexOutOFBoundsException() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.add(0, 7));
        assertEquals(exception.getMessage(),"Index out of emptyList.");
    }

    @Test
    void checkContainElementInListWhenIsContained() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertTrue(emptyList.contains(6));
    }

    @Test
    void checkContainElementInListWhenIsNotContained() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertFalse(emptyList.contains(7));
    }

    @Test
    void whenElementIsRemovedNextElementsMovingBackAndReturnTrue() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        array = new int[]{6, 4, 3, 2};
        assertTrue(emptyList.remove(5));
        assertEquals(Arrays.toString(array), emptyList.toString());

    }

    @Test
    void removeElementFromTheFirstPosition() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        array = new int[]{5, 4, 3, 2};
        assertTrue(emptyList.remove(6));
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void removeElementFromTheLastPosition() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        array = new int[]{6, 5, 4, 3};
        assertTrue(emptyList.remove(2));
        assertEquals(Arrays.toString(array), emptyList.toString());
    }

    @Test
    void whenRemoveElementWitchNoteContainedReturnFalse() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        array = new int[]{6, 5, 4, 3, 2};
        assertFalse(emptyList.remove(7));
        assertEquals(Arrays.toString(array), emptyList.toString());

    }

    @Test
    void whenElementIsRemovedFromEmptyListExpectIndexOutOFBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.remove(5));
        assertEquals(exception.getMessage(),"List is empty.");
    }

    @Test
    void checkTrueToSize() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertEquals(5, emptyList.size());
    }

    @Test
    void checkListIsEmptyReturnTrue() {
        assertTrue(emptyList.isEmpty());
    }

    @Test
    void checkListIsEmptyReturnFalse() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertFalse(emptyList.isEmpty());
    }

    @Test
    void getValueFromIndex() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertEquals(4, emptyList.get(2));
        assertEquals(2, emptyList.get(4));
    }

    @Test
    void getValueFromIndexWhenListIsEmpty() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(0));
        assertEquals(exception.getMessage(),"List is empty.");
    }

    @Test
    void getValueFromIndexOutOfListExpectIndexOutOfBoundsException() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyList.get(6));
        assertEquals(exception.getMessage(),"Index out of emptyList.");
    }

    @Test
    void returnIndexOfValueWhenIsContained() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertEquals(4, emptyList.indexOf(2));
    }

    @Test
    void returnIndexOfValueWhenIsNotContained() {
        emptyList.add(6);
        emptyList.add(5);
        emptyList.add(4);
        emptyList.add(3);
        emptyList.add(2);
        assertEquals(-1, emptyList.indexOf(7));
    }
}
