package org.javaadu.leetcode;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class FibSoloution {

    private static int fibSolutionDp(int n) {
        return 0;
    }

    private static int fibSolutionDiGui(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibSolutionDiGui(n - 1) + fibSolutionDiGui(n - 2);
    }

    private static Map<Integer, Integer> cache = new HashMap<>();

    private static int fibSolutionDiGuiWithCache(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }

        Integer x1 = cache.get(n - 1);
        if (x1 == null) {
            x1 = fibSolutionDiGuiWithCache(n - 1);
            cache.put(n - 1, x1);
        }
        Integer x2 = cache.get(n - 2);
        if (x2 == null) {
            x2 = fibSolutionDiGuiWithCache(n - 2);
            cache.put(n - 2, x2);
        }
        return (x1 + x2) % 1000000007;
    }

    public static void main(String[] args) {
//        long current = System.currentTimeMillis();
//        int res = fibSolutionDiGuiWithCache(100);
//        System.out.println("fibSolutionDiGuiWithCache res:" + res);
//        System.out.println("fibSolutionDiGuiWithCache cost:" + (System.currentTimeMillis() - current));
//
//
//        long current2 = System.currentTimeMillis();
//        int res2 = fibSolutionDiGui(100);
//        System.out.println("fibSolutionDiGui res:" + res2);
//        System.out.println("fibSolutionDiGui cost:" + (System.currentTimeMillis() - current2));
//
//
        int[][] ex = new int[3][5];
        System.out.println(ex.length);//行数
        System.out.println(ex[0].length);//列数
    }
}
