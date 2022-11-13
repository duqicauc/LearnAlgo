package org.javaadu.leetcode;

import java.util.HashMap;
import java.util.Map;

public class MyAtoiSolutionWithAutomachine {

    public static int myAtoi(String s) {
        Automachine automachine = new Automachine();
        for (int i = 0; i < s.length(); i++) {
            automachine.get(s.charAt(i));
            if (automachine.isEnd()) {
                break;
            }
        }

        return automachine.sign * automachine.ans;
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

class Automachine {
    int sign = 1;
    int ans = 0;

    private String state = "start";
    private Map<String, String[]> machine = new HashMap<String, String[]>() {{
        //空格,+/-,number,other
        put("start", new String[]{"start", "signed", "in_number", "end"});
        put("end", new String[]{"end", "end", "end", "end"});
        put("signed", new String[]{"end", "end", "in_number", "end"});
        put("in_number", new String[]{"end", "end", "in_number", "end"});
    }};


    public void get(char c) {
        state = machine.get(state)[getCol(c)];
        if ("in_number".equals(state)) {
            int num = c - '0';
            if (sign == 1) {
                if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    ans = Integer.MAX_VALUE;
                    state = "end";
                } else {
                    ans = ans * 10 + num;
                }
            } else {
                if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && -num > Integer.MIN_VALUE % 10)) {
                    ans = Integer.MIN_VALUE;
                    state = "end";
                    //-1 * Integer.MIN_VALUE=-2147483648，所以这里不用特殊照顾
                } else {
                    ans = ans * 10 + num;
                }
            }
        } else if ("signed".equals(state)) {
            sign = c == '+' ? 1 : -1;
        }
    }

    private int getCol(char c) {
        if (c == ' ') {
            return 0;
        } else if (c == '+' || c == '-') {
            return 1;
        } else if (Character.isDigit(c)) {
            return 2;
        } else {
            return 3;
        }
    }

    public boolean isEnd() {
        return "end".equals(state);
    }
}
