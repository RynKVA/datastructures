package org.example.search;

public class QuickSearch {
    public static int quickSearch(int[] array, int targetElement) {
        int firstIndex = 0;
        int lastIndex = array.length - 1;
        while (firstIndex <= lastIndex) {
            int middleIndex= (firstIndex+lastIndex)/2;
            if (array[middleIndex]==targetElement){
                return middleIndex;
            }else{
                if (array[middleIndex]> targetElement){
                    lastIndex=middleIndex-1;
                }else {
                    firstIndex=middleIndex+1;
                }
            }

        }
        return -1;
    }
}
