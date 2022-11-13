package org.javaadu.linklist;

import org.javaadu.common.ListNode;

public class MyLinkedList {

    private int size;
    private ListNode head;

    public MyLinkedList() {
        size = 0;
        head = new ListNode("-1");
    }

    public String get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        ListNode cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(String val) {
        addAtIndex(0, val);
    }

    public void addAtTail(String val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, String val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }

        ListNode pred = head;
        ListNode cur = head.next;
        for (int i = 0; i < index; ++i) {
            pred = pred.next;
            cur = cur.next;
        }

        ListNode toAdd = new ListNode(val);
        toAdd.next = cur;
        pred.next = toAdd;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
            cur = cur.next;
        }

        pre.next = cur.next;

        size--;
    }

    public void print() {
        ListNode p = head.next;
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
        System.out.print("\n");
    }

    public void reverse() {
        ListNode pre = null;
        ListNode cur = head.next;

        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;

            //滑动窗口
            pre = cur;
            cur = temp;
        }

        head.next = pre;
    }

    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead("1");
        linkedList.addAtTail("3");
        linkedList.addAtIndex(2, "4"); //链表变为1->3->4
        linkedList.addAtIndex(1, "2");   //链表变为1->2->3->4
        linkedList.print();
        System.out.println(linkedList.get(1));   //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1->3->4
        linkedList.print();
        System.out.println(linkedList.get(1)); //返回3

        linkedList.print();
        linkedList.reverse();
        linkedList.print();
    }
}
