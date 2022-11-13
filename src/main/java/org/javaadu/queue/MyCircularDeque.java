package org.javaadu.queue;

/**
 * 双端循环队列不复杂，处理好3个事情：指针方向和取余
 * 1. 头部入队操作，head-1，并且注意别出现负数，以及按照数组大小去余
 * 2. 头部出对操作，head+1，并且按照数组大小取余
 * 3. 尾部出队操作，tail-1，并且注意别出现负数，以及按照数组大小去余
 * 4. 尾部入队操作，tail+1，并且按照数组大小取余
 *
 * data：一个固定大小的数组，用于保存循环队列的元素。
 * head：队列首元素对应的数组的索引
 * tail：队列尾元素对应的索引的下一个索引【！！！】
 */
public class MyCircularDeque {

    private int[] data;
    private int head;
    private int tail;
    private int length;

    public MyCircularDeque(int k) {
        length = 0;
        head = 0;
        tail = 0;
        data = new int[k];
    }

    public boolean insertFront(int value) {
        if (length == data.length) {
            return false;
        }
        head = (head - 1 + data.length) % data.length;
        data[head] = value;
        length++;
        return true;
    }

    public boolean insertLast(int value) {
        if (length == data.length) {
            return false;
        }
        data[tail] = value;
        tail = (tail + 1) % data.length;
        length++;
        return true;
    }

    public boolean deleteFront() {
        if (length == 0) {
            return false;
        }
        head = (head + 1) % data.length;
        length--;
        return true;
    }

    public boolean deleteLast() {
        if (length == 0) {
            return false;
        }
        tail = (tail - 1 + data.length) % data.length;
        length--;
        return true;
    }

    public int getFront() {
        if (length == 0) {
            return -1;
        }
        return data[head];
    }

    public int getRear() {
        if (length == 0) {
            return -1;
        }
        return data[(tail - 1 + data.length) % data.length];
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public boolean isFull() {
        return length == data.length;
    }

    public static void main(String[] args) {
        /**
         * ["MyCircularDeque","insertLast","insertLast","insertFront","insertFront","getRear","isFull","deleteLast","insertFront","getFront"]
         * [[3],[1],[2],[3],[4],[],[],[],[4],[]]
         */

        MyCircularDeque obj = new MyCircularDeque(3);
        boolean param_1 = obj.insertLast(1);
        System.out.println(param_1);
        boolean param_2 = obj.insertLast(2);
        System.out.println(param_2);
        boolean param_3 = obj.insertFront(3);
        System.out.println(param_3);
        boolean param_4 = obj.insertFront(4);
        System.out.println(param_4);
        int param_5 = obj.getRear();
        System.out.println(param_5);
        boolean param_6 = obj.isFull();
        System.out.println(param_6);
        boolean param_7 = obj.deleteLast();
        System.out.println(param_7);
        boolean param_8 = obj.insertFront(10000);
        System.out.println(param_8);
        int param_9 = obj.getFront();
        System.out.println(param_9);
    }
}
