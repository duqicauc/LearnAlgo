package org.javaadu.leetcode;

public class ReverseStringSolution {

    public static String reverseString(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        int left = 0;
        int right = stringBuilder.length() - 1;
        while (left < right) {
            char temp = stringBuilder.charAt(left);
            stringBuilder.setCharAt(left++, stringBuilder.charAt(right));
            stringBuilder.setCharAt(right--, temp);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseString("hello world"));
    }
}
