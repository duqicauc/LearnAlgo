package org.javaadu.leetcode;

import java.util.PriorityQueue;

/**
 * 优先级队列，默认是小顶堆——堆顶的元素是堆中最小的元素，这里要求第K大的元素，
 * 那么如果一个数比这个集合里最小的都小，那肯定不满足要求，否则它就可以替换掉这个堆顶元素
 */
public class KthLargest {

    public final PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
    public Integer k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int i : nums) {
            this.add(i);
        }
    }

    public int add(int val) {
        if (priorityQueue.size() < k) {
            //如果堆中的元素不足k个，直接入队
            priorityQueue.offer(val);
        } else if (priorityQueue.peek() != null && priorityQueue.peek() < val) {
            //如果堆顶元素比新来的元素小，说明新来的元素可以替换这个堆顶元素
            priorityQueue.poll();
            priorityQueue.offer(val);
        }
        return priorityQueue.peek() == null ? 0 : priorityQueue.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 3, 5, 8};
        KthLargest kthLargest = new KthLargest(3, nums);
        System.out.println(kthLargest.add(10));
    }
}
