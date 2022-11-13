package org.javaadu.hashtable;

import org.javaadu.queue.LinkQueue;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    /**
     * 记录key到Node的直接映射，将查找的时间复杂度缩短到O(1)
     */
    private Map<Integer, DLinkNode> keyToNodeMap;
    private DLinkNode dummyHeadNode;
    private DLinkNode dummyTailNode;
    private int count;
    /**
     * 记录LRU缓存的空间大小
     */
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;

        keyToNodeMap = new HashMap<>();

        dummyHeadNode = new DLinkNode(-1, -1);
        dummyTailNode = new DLinkNode(-1, -1);
        dummyHeadNode.next = dummyTailNode;
        dummyTailNode.prev = dummyHeadNode;
    }

    /**
     * 如果key存在，则将其从链表中移动到头部
     * 如果不存在，则返回-1
     *
     * @param key
     * @return
     */
    public int get(int key) {
        DLinkNode dLinkNode = keyToNodeMap.get(key);
        if (dLinkNode == null) {
            return -1;
        } else {
            moveToHead(dLinkNode);
            return dLinkNode.val;
        }
    }

    /**
     * 如果存在，则更新指定节点的值，并移动到链表头部
     * 如果不存在，则添加到头部，并判断是否需要剔除尾部的元素
     *
     * @param key
     * @param value
     */
    public void put(int key, int value) {
        DLinkNode dLinkNode = keyToNodeMap.get(key);
        if (dLinkNode == null) {
            dLinkNode = new DLinkNode(value, key);
            keyToNodeMap.put(key, dLinkNode);
            addToHead(dLinkNode);
            count++;
            if (count > capacity) {
                int keyToDelete = removeFromTail();
                count--;
                keyToNodeMap.remove(keyToDelete);
            }
        } else {
            dLinkNode.val = value;
            moveToHead(dLinkNode);
        }
    }

    /**
     * 将节点移动到链表头部
     *
     * @param dLinkNode
     */
    private void moveToHead(DLinkNode dLinkNode) {
        removeNode(dLinkNode);
        addToHead(dLinkNode);
    }

    /**
     * 删除指定节点
     *
     * @param dLinkNode
     */
    private void removeNode(DLinkNode dLinkNode) {
        dLinkNode.prev.next = dLinkNode.next;
        dLinkNode.next.prev = dLinkNode.prev;
    }

    /**
     * 将节点添加到链表头部
     *
     * @param dLinkNode
     */
    private void addToHead(DLinkNode dLinkNode) {
        dLinkNode.next = dummyHeadNode.next;
        dLinkNode.prev = dummyHeadNode;

        dummyHeadNode.next.prev = dLinkNode;
        dummyHeadNode.next = dLinkNode;
    }

    /**
     * 删除尾部节点
     */
    private int removeFromTail() {
        int res = dummyTailNode.prev.key;
        removeNode(dummyTailNode.prev);
        return res;
    }

    public void printAll() {
        DLinkNode cur = dummyHeadNode.next;
        while (cur != dummyTailNode) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(3);
        //测试添加
        lruCache.put(123, 789); //789
        lruCache.printAll();
        lruCache.put(122, 788); //788,789
        lruCache.printAll();
        lruCache.put(124, 999); //999,788,789
        lruCache.printAll();
        lruCache.put(125, 1000); //1000,999,788【789被剔除】
        lruCache.printAll();
        //测试重复访问
        int res = lruCache.get(124);
        System.out.println(res);//输出999，链表改成999,1000,788
        lruCache.printAll();

        /**
         * ["LRUCache","put","put","get","put","get","put","get","get","get"]
         * [[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
         */
        LRUCache lruCache2 = new LRUCache(2);
        lruCache2.put(1, 0); //0
        lruCache2.printAll();
        lruCache2.put(2, 2); //2,0
        lruCache2.printAll();

        int res1 = lruCache2.get(1); //0,2
        System.out.println(res1);
        lruCache2.printAll();

        lruCache2.put(3, 3); //3,0
        lruCache2.printAll();

        int res2 = lruCache2.get(2); //-1
        System.out.println(res2);
        lruCache2.printAll();

        lruCache2.put(4, 4); //4,3
        lruCache2.printAll();

        int res3 = lruCache2.get(1);
        System.out.println(res3);//-1
        lruCache2.printAll();//4,3

        int res4 = lruCache2.get(3);
        System.out.println(res4);
        lruCache2.printAll();

        int res5 = lruCache2.get(4);
        System.out.println(res5);
        lruCache2.printAll();
    }

}

class DLinkNode {
    int val;
    int key;
    DLinkNode prev;
    DLinkNode next;

    public DLinkNode(int val, int key) {
        this.val = val;
        this.key = key;
    }
}
