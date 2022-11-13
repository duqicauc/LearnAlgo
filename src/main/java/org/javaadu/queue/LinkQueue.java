package org.javaadu.queue;

import org.javaadu.common.ListNode;

import javax.swing.plaf.IconUIResource;

/**
 * 利用链表实现队列
 */
public class LinkQueue {

    private ListNode head;
    private ListNode tail;

    private int length;

    public LinkQueue() {
        head = new ListNode("-1");
        tail = head;
        length = 0;
    }

    /**
     * 从head到tail打印
     */
    public void printAll() {
        ListNode cur = head.next;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void enqueue(String val) {
        ListNode temp = new ListNode(val);
        tail.next = temp;
        tail = temp;
        length++;
    }

    public String dequeue() {
        if (length == 0) {
            throw new RuntimeException("队列已空");
        }

        ListNode toDequeue = head.next;
        head.next = toDequeue.next;
        length--;
        return toDequeue.val;
    }

    public String peek() {
        if (length == 0) {
            throw new RuntimeException("队列已空");
        }
        return head.next.val;
    }

    public int getLength() {
        return length;
    }

    public static void main(String[] args) {
        LinkQueue linkQueue = new LinkQueue();
        System.out.println("init");
        System.out.println("length:" + linkQueue.getLength());

        linkQueue.enqueue("hello");
        linkQueue.enqueue("world");
        linkQueue.enqueue("i");
        linkQueue.enqueue("love");
        linkQueue.enqueue("leetcode");
        System.out.println("after push");
        System.out.println("length:" + linkQueue.getLength());
        linkQueue.printAll();

        linkQueue.dequeue();
        linkQueue.dequeue();
        linkQueue.printAll();
        System.out.println(linkQueue.peek());
        linkQueue.printAll();
        System.out.println(linkQueue.dequeue());
        linkQueue.printAll();
        linkQueue.dequeue();
        linkQueue.dequeue();
        System.out.println("length:" + linkQueue.getLength());

        //测试dequeue异常
//        linkQueue.dequeue();
    }
}
