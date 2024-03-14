
package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;


public class Roman {
    private Map<Character, Integer> m;

    public Roman() {
        m = new HashMap<>();
        m.put('I', 1);
        m.put('V', 5);
        m.put('X', 10);
        m.put('L', 50);
        m.put('C', 100);
        m.put('D', 500);
        m.put('M', 1000);
    }

    public int romanToInt(String s) {
        char[] array = s.toCharArray();
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            if (i != array.length - 1 & array[i] == 'I') {
                if (array[i + 1] == 'V') {
                    num += 4;
                    i++;
                } else if (array[i + 1] == 'X') {
                    num += 9;
                    i++;
                } else num += 1;
            } else if (i != array.length - 1 & array[i] == 'X') {
                if (array[i + 1] == 'L') {
                    num += 40;
                    i++;
                } else if (array[i + 1] == 'C') {
                    num += 90;
                    i++;
                } else num += 10;
            } else if (i != array.length - 1 & array[i] == 'C') {
                if (array[i + 1] == 'D') {
                    num += 400;
                    i++;
                } else if (array[i + 1] == 'M') {
                    num += 900;
                    i++;
                } else num += 100;
            } else {
                num += m.get(array[i]);
            }
        }
        return num;
    }

}

