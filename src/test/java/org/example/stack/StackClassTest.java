package org.example.stack;

import org.example.list.stack.StackClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


public class StackClassTest {
    private StackClass<Integer> emptyStack;
    private StackClass<Integer> stack;
    private Integer[] array;

    @BeforeEach
    void before() {
        emptyStack = new StackClass<>();

        stack = new StackClass<>();
        stack.push(6);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
        stack.push(0);
    }

    @Test
    void whenCallMethodIsEmptyOnEmptyStackThanReturnTrue() {
        StackClass<Integer> emptyStack = new StackClass<>();
        assertTrue(emptyStack.isEmpty());
    }

    @Test
    void whenItemPeekedThanReturnItemButNotRemoving() {
        array = new Integer[]{6, 5, 4, 3, 2, 1, 0};

        assertEquals(0, stack.peek());
        assertEquals(Arrays.toString(array), stack.toString());
    }

    @Test
    void whenPoppedThanReturnLastItemInStackAndRemoveIts() {
        array = new Integer[]{6, 5, 4, 3, 2, 1};

        assertEquals(0, stack.pop());
        assertEquals(Arrays.toString(array), stack.toString());
    }

    @Test
    void whenPoppedInEmptyStackExpectIndexOutOfBoundsException() {
        IndexOutOfBoundsException exception = assertThrows(IndexOutOfBoundsException.class,
                () -> emptyStack.pop());
        assertEquals(exception.getMessage(), "Stack is empty, push item.");
    }

    @Test
    void whenPushAnItemInEmptyStackItsTakesTheFirstPositionAndReturnItem() {
        array = new Integer[]{1};

        assertEquals(1, emptyStack.push(1));
        assertEquals(Arrays.toString(array), emptyStack.toString());
    }

    @Test
    void whenPushAnItemInNotEmptyStackItsTakesTheLastPositionInStackAndReturnItem() {
        array = new Integer[]{6, 5, 4, 3, 2, 1, 0, -1};

        assertEquals(-1, stack.push(-1));
        assertEquals(Arrays.toString(array), stack.toString());
    }

    @Test
    void whenSearchItemWhichInStackReturnPosition() {
        assertEquals(4, stack.search(2));
    }

    @Test
    void whenSearchItemWhichNotInStackReturnMinusOne() {
        assertEquals(-1, stack.search(7));
    }

    @Test
    void testClearOnEmptyStack() {
        emptyStack.clear();

        assertEquals(0, emptyStack.size());
    }

    @Test
    void testClearOnStackWithSomeElements() {
        stack.clear();

        assertEquals(0,stack.size());
    }
}
