package org.javaadu.queue;

/**
 * 利用数组实现队列
 */
public class ArrayQueue {

    private String[] data;
    private int count;
    private int head;
    private int tail;

    public ArrayQueue(int size) {
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
        if (tail == data.length - 1) {
            System.arraycopy(data, head, data, 0, tail - head);
            tail = tail - head;
            head = 0;
        }
        data[tail++] = item;
        count++;
    }

    public String dequeue() {
        if (count == 0) {
            throw new RuntimeException("队列已空");
        }
        String res = data[head];
        data[head++] = "";
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
        ArrayQueue arrayQueue = new ArrayQueue(5);
        System.out.println("init");
        System.out.println("size:" + arrayQueue.getSize());
        System.out.println("count:" + arrayQueue.getCount());

        arrayQueue.enqueue("hello");
        arrayQueue.enqueue("world");
        arrayQueue.enqueue("i");
        arrayQueue.enqueue("love");
        arrayQueue.enqueue("leetcode");
        System.out.println("after push");
        System.out.println("size:" + arrayQueue.getSize());
        System.out.println("count:" + arrayQueue.getCount());
        arrayQueue.printAll();

        //测试入队异常
//        arrayQueue.enqueue("hhhh");

        arrayQueue.dequeue();
        arrayQueue.dequeue();
        arrayQueue.printAll();
        System.out.println(arrayQueue.peek());
        arrayQueue.printAll();
        System.out.println(arrayQueue.dequeue());
        arrayQueue.printAll();
        arrayQueue.dequeue();
        arrayQueue.dequeue();
        System.out.println("size:" + arrayQueue.getSize());
        System.out.println("count:" + arrayQueue.getCount());

        //测试dequeue异常
//        arrayQueue.dequeue();
    }
}
