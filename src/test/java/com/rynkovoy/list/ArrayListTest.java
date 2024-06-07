package com.rynkovoy.list;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest  extends ListTest {
    ArrayList<Integer> emptyList = new ArrayList<>();
    @Override
    List<Integer> getList() {
        return new ArrayList<>();
    }

    @Test
    @DisplayName("When default capacity (10) is full then capacity grow on default extension (1.5) + 1")
    void whenDefaultCapacityIsFullThenCapacityExpendingOnDefaultExtensionPlusOne(){
       assertEquals(10, emptyList.capacityArrayLength());

        for (int i = 0; i < 11; i++) {
            emptyList.add(i);
        }

        assertEquals(16, emptyList.capacityArrayLength() );
    }

    @Test
    @DisplayName("When used method trimToSize then inner array length trimmed to size")
    void whenUsedTrimToSizeThenInnerArrayLengthTrimToSize(){
        emptyList.add(4);
        emptyList.add(4);
        emptyList.add(4);
        emptyList.add(4);

        assertEquals(10, emptyList.capacityArrayLength());
        assertEquals(4, emptyList.size());

        emptyList.trimToSize();
        assertEquals(4, emptyList.capacityArrayLength());
    }
}