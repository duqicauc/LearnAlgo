package org.javaadu.leetcode;

public class MyAtoiSolution {

    public static int myAtoi(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int start = 0;
        while (start < s.length() && s.charAt(start) == ' ') {
            start++;
        }

        int res = 0;
        int sigh = 1;
        int i = start;
        while (i < s.length()) {
            if (i == start && s.charAt(i) == '+') {
                sigh = 1;
            } else if (i == start && s.charAt(i) == '-') {
                sigh = -1;
            } else if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                int num = s.charAt(i) - '0';

                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    //要么是res导致溢出，要么是num导致溢出
                    return Integer.MAX_VALUE;
                }

                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    //要么是res导致溢出，要么是num导致溢出
                    return Integer.MIN_VALUE;
                }

                res = res * 10 + num * sigh;
            } else {
                break;
            }
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("42"));
        System.out.println(myAtoi("-42"));
        System.out.println(myAtoi("    -422333ddddd"));
        System.out.println(myAtoi("    -4223337474747474774747747747474747747474747747474777777747747474ddddd"));
        System.out.println(myAtoi("    +4223337474747474774747747747474747747474747747474777777747747474ddddd"));
        System.out.println(myAtoi("ddd4193 with words"));
    }
}
