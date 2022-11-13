package org.javaadu.stack;

import java.util.*;

public class ValidBracketsSolution {

    public static boolean isValid(String s) {
        Map<Character, Character> temp = new HashMap<>();
        temp.put(')', '(');
        temp.put(']', '[');
        temp.put('}', '{');

        Stack<Character> stack = new Stack();
        for (char c : s.toCharArray()) {
            if (temp.values().contains(c)) {
                stack.push(c);
            } else if(temp.keySet().contains(c)){
                if (stack.isEmpty()) {
                    return false;
                }
                if (temp.get(c).equals(stack.peek())) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                continue;
            }
        }
        if(stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(isValid("[]"));
        System.out.println(isValid("((())"));
        System.out.println(isValid("(){}[]"));
        System.out.println(isValid(""));
    }
}
