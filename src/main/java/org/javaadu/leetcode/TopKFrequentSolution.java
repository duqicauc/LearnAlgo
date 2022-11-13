package org.javaadu.leetcode;

import org.javaadu.queue.ArrayQueue;

import java.util.*;

public class TopKFrequentSolution {

    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> itemToCount = new HashMap<>();
        for (int num : nums) {
            Integer count = itemToCount.get(num);
            if (count == null) {
                count = 1;
            } else {
                count += 1;
            }
            itemToCount.put(num, count);
        }

        //使用优先级队列的时候，需要提前想清楚比较器是根据什么比较
        PriorityQueue<int[]> priorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //默认小顶堆
                //如果大顶堆，则o2在前，o1在后
                return o1[1] - o2[1];
            }
        });
        for (Map.Entry<Integer, Integer> entry : itemToCount.entrySet()) {
            int[] entryItem = new int[]{entry.getKey(), entry.getValue()};
            if (priorityQueue.size() < k) {
                priorityQueue.offer(entryItem);
            } else {
                int[] peekItem = priorityQueue.peek();
                if (peekItem != null && peekItem[1] < entry.getValue()) {
                    priorityQueue.poll();
                    priorityQueue.offer(entryItem);
                }
            }
        }

        int[] res = new int[k];
        int j = 0;
        while (!priorityQueue.isEmpty()) {
            res[j++] = priorityQueue.poll()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(topKFrequent(new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 3}, 2)));
        System.out.println(Arrays.toString(topKFrequent(new int[]{1}, 1)));
    }
}
