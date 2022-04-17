package linklist;

public class MyLinkedList {

    private int size;
    private SingleListNode head;

    public MyLinkedList() {
        size = 0;
        head = new SingleListNode(0);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        SingleListNode cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    public void addAtHead(int val) {
        addAtIndex(0, val);
    }

    public void addAtTail(int val) {
        addAtIndex(size, val);
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index < 0) {
            index = 0;
        }

        SingleListNode pred = head;
        SingleListNode cur = head.next;
        for (int i = 0; i < index; ++i) {
            pred = pred.next;
            cur = cur.next;
        }

        SingleListNode toAdd = new SingleListNode(val);
        toAdd.next = cur;
        pred.next = toAdd;

        size++;
    }

    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            return;
        }

        SingleListNode pre = head;
        SingleListNode cur = head.next;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
            cur = cur.next;
        }

        pre.next = cur.next;

        size--;
    }

    public void print() {
        SingleListNode p = head.next;
        while (p != null) {
            System.out.print(p.val + "\t");
            p = p.next;
        }
        System.out.print("\n");
    }

    public void reverse() {
        SingleListNode pre = null;
        SingleListNode cur = head.next;

        while (cur != null) {
            SingleListNode temp = cur.next;
            cur.next = pre;

            //滑动窗口
            pre = cur;
            cur = temp;
        }

        head.next = pre;
    }
}

class SingleListNode {

    public int val;
    public SingleListNode next;

    public SingleListNode(int val) {
        this.val = val;
        next = null;
    }
}
