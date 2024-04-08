package org.example.queue;

import org.example.list.queue.LinkedQueue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class LinkedQueueTest {
    private LinkedQueue<Integer> emptyQueue;
    private LinkedQueue<Integer> queueWithCapacityThree;

    @BeforeEach
    void before() {
        emptyQueue=new LinkedQueue<>();
        queueWithCapacityThree= new LinkedQueue<>(3);

        queueWithCapacityThree.add(3);
        queueWithCapacityThree.add(5);
        queueWithCapacityThree.add(4);
    }

    @Test
    void addInEmptyQueueAndReturnTrue() {
        int[] array=new int[]{3};
        assertTrue(emptyQueue.add(3));
        assertEquals(Arrays.toString(array),emptyQueue.toString());
    }
    @Test
    void addInNotEmptyQueueAndReturnTrue() {
        int[] array=new int[]{3,4};
        assertTrue(emptyQueue.add(3));
        assertTrue(emptyQueue.add(4));
        assertEquals(Arrays.toString(array),emptyQueue.toString());
    }
    @Test
    void addInFullQueueExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> queueWithCapacityThree.add(6));
        assertEquals(exception.getMessage(),"Queue is full.");
    }

    @Test
    void elementReturnFirstElement() {
        assertEquals(3, queueWithCapacityThree.element());
    }
    @Test
    void elementInEmptyQueueExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> emptyQueue.element());
        assertEquals(exception.getMessage(),"Queue is empty.");
    }

    @Test
    void offerInEmptyQueueAndReturnTrue() {
        int[] array=new int[]{3};
        assertTrue(emptyQueue.offer(3));
        assertEquals(Arrays.toString(array),emptyQueue.toString());
    }
    @Test
    void offerInNotEmptyQueueAndReturnTrue() {
        int[] array=new int[]{3,4};
        assertTrue(emptyQueue.offer(3));
        assertTrue(emptyQueue.offer(4));
        assertEquals(Arrays.toString(array),emptyQueue.toString());
    }
    @Test
    void offerInFullQueueReturnFalse() {
        assertFalse(queueWithCapacityThree.offer(6));
    }


    @Test
    void peekReturnFirstElement() {
        assertEquals(3, queueWithCapacityThree.peek());
    }
    @Test
    void peekInEmptyQueueReturnNull() {
        assertNull(emptyQueue.peek());
    }

    @Test
    void pollInFullQueueReturnAndRemoveFirstElement() {
        int[] array=new int[]{5,4};
        assertEquals(3,queueWithCapacityThree.poll());
        assertEquals(Arrays.toString(array),queueWithCapacityThree.toString());
    }
    @Test
    void pollInEmptyQueueReturnNull() {
        assertNull(emptyQueue.poll());
    }
    @Test
    void pollAllElementsInQueueWithCapacityThreeAndReturnNullInEmptyQueue() {
        assertEquals(3,queueWithCapacityThree.poll());
        assertEquals(5,queueWithCapacityThree.poll());
        assertEquals(4,queueWithCapacityThree.poll());
        assertNull(queueWithCapacityThree.poll());
    }

    @Test
    void removeInFullQueueReturnAndRemoveFirstElement() {
        int[] array=new int[]{5,4};
        assertEquals(3,queueWithCapacityThree.remove());
        assertEquals(Arrays.toString(array),queueWithCapacityThree.toString());
    }
    @Test
    void removeInEmptyQueueExpectIllegalStateException() {
        IllegalStateException exception = assertThrows(IllegalStateException.class,
                () -> emptyQueue.remove());
        assertEquals(exception.getMessage(),"Queue is empty.");
    }
}