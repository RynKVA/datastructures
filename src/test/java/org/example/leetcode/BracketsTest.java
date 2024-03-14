package org.example.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BracketsTest {
    private final Brackets b=new Brackets();

    @Test
    void bracketsRight() {
        assertTrue(b.bracketsRight("(){}[]"));
    }
    @Test
    void bracketsFalse(){
        assertFalse(b.bracketsRight("[{)]"));
    }
}