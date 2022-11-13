package org.javaadu.stack;

import java.util.Deque;
import java.util.LinkedList;

public class LongestValidParenthesesSolution {

    public static int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                //入栈，充当参照
                stack.push(i);
            } else {
                //先讲栈顶元素出栈，表示完成匹配
                stack.pop();

                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses("()(())"));
        System.out.println(longestValidParentheses("(()"));
        System.out.println(longestValidParentheses(")()())"));
        System.out.println(longestValidParentheses("("));
        System.out.println(longestValidParentheses(")"));
        System.out.println(longestValidParentheses(""));
    }
}
