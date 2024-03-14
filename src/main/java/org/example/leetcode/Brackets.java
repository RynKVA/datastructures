package org.example.leetcode;


import java.util.Stack;

public class Brackets {
    private final Stack<Character> stack;
    Brackets() {
        stack = new Stack<>();
    }
    public boolean bracketsRight(String s) {
        char[] ch = s.toCharArray();
        for (char c : ch) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && stack.peek() == '{') {
                stack.pop();
            } else return false;
        }
        return true;
    }
}
