package org.javaadu.stack;

public class ArrayStack {

    private String[] data;
    //栈的大小
    private int size;
    //栈中元素的个数
    private int count;

    public ArrayStack() {
        this.size = 10;
        data = new String[size];
        count = 0;
    }

    public ArrayStack(int nums) {
        this.size = nums;
        data = new String[size];
        count = 0;
    }

    /**
     * 从栈顶开始依次打印
     */
    public void printAll() {
        for (int i = count - 1; i >= 0; i--) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    /**
     * 入栈操作
     *
     * @param value
     */
    public void push(String value) {
        if (count == size) {
            throw new IllegalArgumentException("堆栈已满");
        }
        count++;
        data[count - 1] = value;
    }

    /**
     * 出栈操作
     *
     * @return
     */
    public String pop() {
        if (count == 0) {
            throw new IllegalArgumentException("堆栈已空");
        }
        String res = data[count - 1];
        data[count - 1] = "";
        count--;
        return res;
    }

    /**
     * 访问栈顶元素，但是不弹出
     *
     * @return
     */
    public String peek() {
        if (count == 0) {
            throw new IllegalArgumentException("堆栈已空");
        }
        return data[count - 1];
    }

    /**
     * 获取栈的大小
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 获取栈中元素的个数
     *
     * @return
     */
    public int getCount() {
        return count;
    }

    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        System.out.println("init");
        System.out.println("size:" + arrayStack.getSize());
        System.out.println("count:" + arrayStack.getCount());

        System.out.println("after push");
        arrayStack.push("hello");
        arrayStack.push("world");
        arrayStack.push("i");
        arrayStack.push("love");
        arrayStack.push("leetcode");
        System.out.println("size:" + arrayStack.getSize());
        System.out.println("count:" + arrayStack.getCount());
        arrayStack.printAll();

        //测试push异常
//        arrayStack.push("test");
        arrayStack.pop();
        arrayStack.pop();
        System.out.println(arrayStack.peek());
        System.out.println(arrayStack.pop());
        arrayStack.printAll();
        arrayStack.pop();
        arrayStack.pop();

        //测试pop异常
//        arrayStack.pop();
    }
}
