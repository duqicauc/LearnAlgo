package org.javaadu.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class CombineSolution {

    public static List<List<Integer>> combine(int n, int k) {
        Deque<Integer> path = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        backtracking(path, result, n, k, 1);
        return result;
    }

    public static void backtracking(Deque<Integer> path, List<List<Integer>> result, int n, int k, int startIndex) {
        //叶子节点，收集结果，递归终止逻辑
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        //单层搜索逻辑
        for (int i = startIndex; i <= n; i++) {
            //处理当前节点
            path.addLast(i);

            //递归
            backtracking(path, result, n, k, i + 1);

            //回溯
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> result = combine(4, 2);
        System.out.println(result);

        List<List<Integer>> result2 = combine(1, 1);
        System.out.println(result2);
    }
}
