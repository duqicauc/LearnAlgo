package org.javaadu.leetcode;

public class ReverseWordStrDemo {

    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        StringBuilder newSb = trimSpaces(sb);
        reverseString(newSb, 0, newSb.length() - 1);
        reverseEachWord(newSb);
        return newSb.toString();
    }

    private static void reverseString(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    private static StringBuilder trimSpaces(StringBuilder sb) {
        int left = 0;
        int right = sb.length() - 1;
        while (left < right && sb.charAt(left) == ' ') {
            left++;
        }

        while (left < right && sb.charAt(right) == ' ') {
            right--;
        }

        StringBuilder newSb = new StringBuilder();
        for (int i = left; i <= right; i++) {
            if (sb.charAt(i) != ' ') {
                newSb.append(sb.charAt(i));
            } else if (sb.charAt(i) == ' ' && sb.charAt(i + 1) != ' ') {
                newSb.append(' ');
            }
        }
        return newSb;
    }

    private static void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = start;
        while (start < sb.length()) {
            while (end < sb.length() && sb.charAt(end) != ' ') {
                end++;
            }

            reverseString(sb, start, end - 1);

            start = end + 1;
            end++;
        }
    }

    public static void main(String[] args) {
        System.out.println(reverseWords("   sky    is       blue   "));
        System.out.println(reverseWords("hello "));
        System.out.println(reverseWords("hello world   "));
    }
}
