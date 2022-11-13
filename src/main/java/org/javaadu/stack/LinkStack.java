package org.javaadu.stack;

public class LinkStack {

    ListNode head;
    int count;

    public LinkStack() {
        head = new ListNode("-1");
        count = 0;
    }

    public void printAll() {
        ListNode cur = head.next;
        while (cur != null) {
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public void push(String item) {
        //头插法
        ListNode temp = new ListNode(item);
        temp.next = head.next;
        head.next = temp;
        count++;
    }

    public String pop() {
        ListNode peekNode = head.next;
        if (peekNode == null) {
            throw new IllegalArgumentException("堆栈已空");
        }
        String res = peekNode.val;
        head.next = head.next.next;
        peekNode.next = null;
        count--;
        return res;
    }

    public String peek() {
        ListNode peekNode = head.next;
        if (peekNode == null) {
            throw new IllegalArgumentException("堆栈已空");
        }
        return peekNode.val;
    }

    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        LinkStack linkStack = new LinkStack();
        System.out.println("init");
        System.out.println("count:" + linkStack.getCount());

        System.out.println("after push");
        linkStack.push("hello");
        linkStack.push("world");
        linkStack.push("i");
        linkStack.push("love");
        linkStack.push("leetcode");
        System.out.println("count:" + linkStack.getCount());
        linkStack.printAll();

        linkStack.pop();
        linkStack.pop();
        System.out.println(linkStack.peek());
        System.out.println(linkStack.pop());
        linkStack.printAll();
        linkStack.pop();
        linkStack.pop();

        //测试pop异常
//        linkStack.pop();
    }
}

class ListNode {
    String val;
    ListNode next;

    public ListNode(String val) {
        this.val = val;
        this.next = null;
    }
}
