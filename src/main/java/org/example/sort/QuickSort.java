package org.example.sort;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {3, 4, 10, 99, -5, 15, 77, 0, 3};
        quickSort(array,0, array.length-1);
        System.out.println(Arrays.toString(array));
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low <= high) {
        int p = divider(array, low, high);

            quickSort(array, p + 1, high);
            quickSort(array, low, p - 1);
        }

    }

    private static int divider(int[] array, int low, int high) {
        int middle = low + (high - low) / 2;
        int pivot = array[middle];

        int temp = array[middle];
        array[middle] = array[high];
        array[high] = temp;

        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }

        temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }
}
