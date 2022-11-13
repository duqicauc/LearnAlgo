package org.javaadu.queue;

/**
 * 实现循环队列
 */
public class MyCircularQueue {

    private String[] data;
    private int count;
    private int head;
    private int tail;

    public MyCircularQueue(int size) {
        this.count = 0;
        head = 0;
        tail = 0;
        data = new String[size];
    }

    /**
     * 按照出队顺序打印
     */
    public void printAll() {
        for (int i = head; i < tail; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 需要处理空间还有，但是队列已经满的情况
     *
     * @param item
     */
    public void enqueue(String item) {
        if (count == data.length) {
            throw new RuntimeException("队列已满");
        }
        tail = (tail + 1) % data.length;
        data[tail] = item;
        count++;
    }

    public String dequeue() {
        if (count == 0) {
            throw new RuntimeException("队列已空");
        }
        String res = data[head];
        data[head] = "";
        head = (head + 1) % data.length;
        count--;
        return res;
    }

    public String peek() {
        if (count == 0) {
            throw new RuntimeException("队列已空");
        }
        return data[head];
    }

    public int getCount() {
        return count;
    }

    public int getSize() {
        return data.length;
    }

    public static void main(String[] args) {
        MyCircularQueue circularQueue = new MyCircularQueue(5);
        System.out.println("init");
        System.out.println("size:" + circularQueue.getSize());
        System.out.println("count:" + circularQueue.getCount());

        circularQueue.enqueue("hello");
        circularQueue.enqueue("world");
        circularQueue.enqueue("i");
        circularQueue.enqueue("love");
        circularQueue.enqueue("leetcode");
        System.out.println("after push");
        System.out.println("size:" + circularQueue.getSize());
        System.out.println("count:" + circularQueue.getCount());
        circularQueue.printAll();

        //测试入队异常
//        arrayQueue.enqueue("hhhh");

        circularQueue.dequeue();
        circularQueue.dequeue();
        circularQueue.printAll();
        System.out.println(circularQueue.peek());
        circularQueue.printAll();
        System.out.println(circularQueue.dequeue());
        circularQueue.printAll();
        circularQueue.dequeue();
        circularQueue.dequeue();
        System.out.println("size:" + circularQueue.getSize());
        System.out.println("count:" + circularQueue.getCount());

        //测试dequeue异常
//        arrayQueue.dequeue();
    }
}
