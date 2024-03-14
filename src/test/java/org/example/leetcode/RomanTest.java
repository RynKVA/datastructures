package org.example.leetcode;

import org.example.leetcode.Roman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanTest {
    Roman roman=new Roman();
    @Test
    void XXITo21(){
        assertEquals(21, roman.romanToInt("XXI"));
    }
    @Test
    void MCMXCIVTo1994(){
        assertEquals(1994, roman.romanToInt("MCMXCIV"));
    }
    @Test
    void MCMXCVIIITo1998(){
        assertEquals(1998,roman.romanToInt("MCMXCVIII"));
    }
    @Test
    void CMXCIXTo999(){
        assertEquals(999,roman.romanToInt("CMXCIX"));
    }
}