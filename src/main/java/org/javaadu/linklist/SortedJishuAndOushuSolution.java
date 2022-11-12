package org.javaadu.linklist;

public class SortedJishuAndOushuSolution {

    public static void main(String[] args) {
        LinkNode head = new LinkNode(0);
        addAtHead(head, 6);
        addAtHead(head, 3);
        addAtHead(head, 8);
        addAtHead(head, 2);
        addAtHead(head, 10);
        addAtHead(head, 1);
        print(head);
        System.out.print("\n");

        //拆分链表
        if (head.next == null || head.next.next == null) {
            //只有1个或2个的情况，不需要动
            return;
        }
        LinkNode jishuHead= new LinkNode(0);
        LinkNode jishuTail = jishuHead;
        LinkNode oushuHead = new LinkNode(0);
        LinkNode oushuTail = oushuHead;

        LinkNode pre = head;
        LinkNode cur = head.next;
        int count = 1;
        while (cur != null) {
            //从原来的链表中删除cur
            pre = pre.next;

            //按照奇数或偶数，将cur节点加入到对应的链表中
            if (count % 2 == 0) {
                oushuTail.next = cur;
                oushuTail = oushuTail.next;
            } else {
                jishuTail.next = cur;
                jishuTail = jishuTail.next;
            }

            //处理下一个节点
            cur = cur.next;
            count++;
        }
        //切断跟原来链表的链接
        jishuTail.next = null;
        oushuTail.next = null;
        print(jishuHead);
        System.out.print("\n");
        print(oushuHead);
        System.out.print("\n");

        //反转偶数链表
        pre = null;
        cur = oushuHead.next;
        while (cur != null) {
            //暂存下一个节点
            LinkNode tmp = cur.next;

            //执行反转操作
            cur.next = pre;

            //滑动到下一个节点
            pre = cur;
            cur = tmp;
        }
        oushuHead.next = pre;
        print(oushuHead);
        System.out.print("\n");

        //合并2个链表
        LinkNode p1 = jishuHead.next;
        LinkNode p2 = oushuHead.next;
        head.next = null;
        LinkNode tail = head;
        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                tail.next = p2;
                p2 = p2.next;
            } else {
                tail.next = p1;
                p1 = p1.next;
            }
            tail = tail.next;
        }
        while (p1 != null) {
            tail.next = p1;
            tail = tail.next;
            p1 = p1.next;
        }
        while (p2 != null) {
            tail.next = p2;
            tail = tail.next;
            p2 = p2.next;
        }
        print(head);
        System.out.print("\n");
    }

    public static void addAtHead(LinkNode head, int val) {
        LinkNode temp = new LinkNode(val);
        temp.next = head.next;
        head.next = temp;
    }

    public static void print(LinkNode head) {
        LinkNode p = head.next;
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
    }
}

class LinkNode {
    public int val;
    public LinkNode next;

    public LinkNode(int val) {
        this.val = val;
    }
}
