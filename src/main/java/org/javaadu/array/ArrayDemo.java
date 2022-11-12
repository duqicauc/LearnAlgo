package org.javaadu.array;

public class ArrayDemo {

    private int[] data;

    private int capacity;

    private int lastIndex;

    public ArrayDemo(int length) {
        this.capacity = length;
        this.data = new int[length];
        this.lastIndex = 0;
    }

    public void printAll() {
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public void add(int index, int value) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("index不合法");
        }
        if (lastIndex + 1 - index >= 0) {
            System.arraycopy(data, index, data, index + 1, lastIndex + 1 - index);
        }
        data[index] = value;
        lastIndex++;
        capacity++;
    }

    public int delete(int index) {
        if (index < 0 || index >= capacity) {
            throw new IllegalArgumentException("index不合法");
        }
        int res = data[index];
        if (lastIndex == index) {
            data[index] = 0;
        } else {
            if (lastIndex - index >= 0) {
                System.arraycopy(data, index + 1, data, index, lastIndex - index);
            }
        }
        lastIndex--;
        capacity--;
        return res;
    }

    public static void main(String[] args) {
        ArrayDemo arrayDemo = new ArrayDemo(4);
        arrayDemo.add(0, 11);
        arrayDemo.add(1, 12);
        arrayDemo.add(3, 13);
        arrayDemo.printAll();
        System.out.println(arrayDemo.delete(3));
        arrayDemo.printAll();
    }
}
