package org.example.sort;

public class Sort {
    static int swaps = 0;

    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                    swaps++;
                }
            }
        }
        return array;
    }
}
