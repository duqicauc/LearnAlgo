package org.javaadu.array;

public class DynamicArray {

    private int[] data;
    private int length;

    public DynamicArray() {
        length = 0;
        data = new int[10];
    }

    public DynamicArray(int size) {
        length = 0;
        data = new int[size];
    }

    public void add(int value) {
        length++;
        data[length-1] = value;
        if (length == data.length) {
            int[] temp = new int[2 * length];
            System.arraycopy(data, 0, temp, 0, data.length);
            data = temp;
        }
    }

    public void remove(int index) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("index不合法");
        }
        if (length - (index + 1) >= 0) {
            System.arraycopy(data, index + 1, data, index, length - (index + 1));
            length--;
        }
    }

    public int getByIndex(int index) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("index不合法");
        }
        return data[index];
    }

    public void update(int index, int value) {
        if (index >= length || index < 0) {
            throw new IllegalArgumentException("index不合法");
        }
        data[index] = value;
    }

    public int size() {
        return length;
    }

    public void printAll() {
        for (int i : data) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DynamicArray dynamicArray = new DynamicArray(2);
        System.out.println("capacity:" + dynamicArray.data.length);
        dynamicArray.add(1);
        dynamicArray.add(2);
        dynamicArray.printAll();
        dynamicArray.add(3);
        dynamicArray.add(66);
        dynamicArray.printAll();

        System.out.println("size:" + dynamicArray.size());
        dynamicArray.remove(3);
        System.out.println("size:" + dynamicArray.size());

        System.out.println(dynamicArray.getByIndex(2));
        dynamicArray.update(2, 77);
        System.out.println(dynamicArray.getByIndex(2));
    }
}
