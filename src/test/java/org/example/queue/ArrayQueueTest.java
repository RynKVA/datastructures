package org.example.queue;

import org.example.list.queue.ArrayQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    ArrayQueue queue = new ArrayQueue();

    @Test
    void testEnqueueAndDequeueOnEmptyQueue() {
        queue.enqueue(3);
        queue.enqueue(4);
        assertEquals(2, queue.size());

        queue.enqueue(6);
        assertEquals(3, queue.size());

        assertEquals(3, queue.dequeue());
        assertEquals(4, queue.dequeue());
        assertEquals(6, queue.dequeue());
    }

    @Test
    void testDequeueOnEmptyListExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> queue.dequeue());
        assertEquals("Queue is empty!", exception.getMessage());
    }

    @Test
    void testPeekOnEmptyQueueExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> queue.peek());
        assertEquals("Queue is empty!", exception.getMessage());
    }

    @Test
    void testPeekOnQueueWithSomElementsInside() {
        queue.enqueue(2);
        assertEquals(2, queue.peek());

        queue.enqueue(4);
        assertEquals(2, queue.peek());

    }

    @Test
    void testSizeQueue() {
        assertEquals(0, queue.size());

        queue.enqueue(5);
        queue.enqueue(5);
        queue.enqueue(5);
        assertEquals(3, queue.size());

    }

    @Test
    void testIsEmptyOnNOtEmptyQueue() {
        queue.enqueue(3);
        queue.enqueue(3);
        queue.enqueue(3);


        assertFalse(queue.isEmpty());

    }

    @Test
    void testIsEmptyOnEmptyQueue() {
        assertTrue(queue.isEmpty());
    }

    @Test
    void testContainReturnTrue() {
        queue.enqueue(3);
        queue.enqueue(10);
        queue.enqueue(22);

        assertTrue(queue.contains(22));
        assertTrue(queue.contains(3));
        assertTrue(queue.contains(10));

    }

    @Test
    void testContainReturnFalse() {
        queue.enqueue(3);
        queue.enqueue(10);
        queue.enqueue(22);

        assertFalse(queue.contains(17));

    }

    @Test
    void testClearOnQueueWithSomeElements() {
        queue.enqueue(3);
        queue.enqueue(10);
        queue.enqueue(22);
        assertEquals(3, queue.size());

        queue.clear();
        assertEquals(0,queue.size());

    }

    @Test
    void testMethodToString(){
        queue.enqueue(3);
        queue.enqueue(10);
        queue.enqueue(22);

        assertEquals("[3, 10, 22]",queue.toString());

    }

}