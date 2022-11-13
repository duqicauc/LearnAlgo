package org.javaadu.leetcode;

import java.util.*;

public class TopKFrequencyWordSolution {

    public static List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordToCount = new HashMap<>();
        for (String word : words) {
            Integer count = wordToCount.get(word);
            if (count == null) {
                count = 1;
            } else {
                count += 1;
            }
            wordToCount.put(word, count);
        }

        PriorityQueue<Map.Entry<String, Integer>> priorityQueue = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                //如果不同的单词有相同出现频率， 按字典顺序排序。
                //因为是小顶堆，因此从堆中依次弹出的过程，就是将出现频率前K大的单词，按照频率从小到大的排序过程，因此最后需要再做一下数组翻转
                //为了让数组翻转的时候，依然保障"相同频率的词按字典序排序"，因此这里需要将o1和o2的位置取反
                return o1.getValue().equals(o2.getValue()) ? o2.getKey().compareTo(o1.getKey()) : o1.getValue() - o2.getValue();
            }
        });
        for (Map.Entry<String, Integer> entry : wordToCount.entrySet()) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(entry);
            } else {
                Map.Entry<String, Integer> peekEntry = priorityQueue.peek();
                if (peekEntry != null && entry.getValue() < peekEntry.getValue()) {
                    priorityQueue.poll();
                    priorityQueue.offer(entry);
                }
            }
        }

        List<String> res = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            res.add(priorityQueue.poll().getKey());
        }

        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));
        System.out.println(topKFrequent(new String[]{"the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is", "ad", "ad", "ad", "ad"}, 4));
    }
}
