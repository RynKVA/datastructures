package org.example.list.iterators;


import org.example.list.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListIteratorTest {

    private final ArrayList<Integer> someElementList= new ArrayList<>();;
    private final ArrayList<Integer> emptyList= new ArrayList<>();;
    private final Iterator<Integer> iterator=someElementList.iterator();

    private final Iterator<Integer> iterator1=emptyList.iterator();

    @BeforeEach
    void before(){
        someElementList.add(1);
        someElementList.add(0);
        someElementList.add(3);
        someElementList.add(9);
        someElementList.add(7);
        someElementList.add(4);
    }

    @Test
    void whenUseHasNextOnListWhichHaveNextElementReturnTrue() {
        assertTrue(iterator.hasNext());
    }
    @Test
    void whenUseHasNextOnEmptyListReturnFalse() {
        assertFalse(iterator1.hasNext());
    }

    @Test
    void whenUseNextOnListWhichHaveNextElementReturnThisElement() {
        assertEquals(1,iterator.next());
        assertEquals(0,iterator.next());
        assertEquals(3,iterator.next());
        assertEquals(9,iterator.next());
    }
    @Test
    void whenUseNextOnListWhichNotHaveNextElementExpectNoSuchElementException() {
        NoSuchElementException exception = assertThrows(NoSuchElementException.class,
                iterator1::next);
        assertEquals(exception.getMessage(),"No next element.");
    }

    @Test
    void removeDeleteElementWitchCalledInMethodNext() {
        int[] array=new int[]{0,3,9,7,4};
        assertEquals(1,iterator.next());
        iterator.remove();
        assertEquals(Arrays.toString(array), someElementList.toString());
    }
    @Test
    void whenRemoveWithNoteUsedMethodNextExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator1::remove);
        assertEquals(exception.getMessage(),"Method next() not used.");

    }
    @Test
    void whenUseRemoveMethodAfterAlreadyUsedThisMethodExpectIllegalStateException() {
        iterator.next();
        iterator.remove();
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                iterator::remove);
        assertEquals(exception.getMessage(),"Method next() not used.");

    }
}