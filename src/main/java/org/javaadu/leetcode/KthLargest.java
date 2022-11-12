package org.javaadu.leetcode;

import java.util.PriorityQueue;

public class KthLargest {

    public final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    public Integer k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i : nums) {
            this.add(i);
        }
    }

    //优先级队列，默认是小顶堆
    //如果新加进来的元素比堆中最小的都小，那说明它一定挤不进前K大个元素
    //如果新加进来的元素比堆中最小的都大，说明它至少可以替换掉这个最小的
    public int add(int val) {
        if (priorityQueue.size() < k) {
            priorityQueue.offer(val);
        } else if (priorityQueue.peek() != null && priorityQueue.peek() < val) {
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        return priorityQueue.peek() == null ? 0 : priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[] {4, 3, 5, 8};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(10));
    }
}
