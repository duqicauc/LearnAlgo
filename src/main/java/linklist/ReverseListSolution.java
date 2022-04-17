package linklist;

public class ReverseListSolution {
    //普通方法实现
    public ListNode reverseListDieDai(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while (cur != null) {
            tmp = cur.next;

            cur.next = pre;

            pre = cur;
            cur = tmp;
        }
        return pre;
    }


    //递归法实现
    public ListNode reverseList(ListNode head) {
        return reverseListTmp(null, head);
    }
    //最小子问题
    public ListNode reverseListTmp(ListNode pre, ListNode cur) {
        if (cur == null) {
            //结束条件
            return pre;
        }

        //记录下一个递归的入口
        ListNode tmp = cur.next;

        //进行反转
        cur.next = pre;

        //触发递归
        return reverseListTmp(cur, tmp);
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */