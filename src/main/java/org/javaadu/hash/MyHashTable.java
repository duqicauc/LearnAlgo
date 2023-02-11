package org.javaadu.hash;

public class MyHashTable {

    private LinkNode[] bucketSet;
    private int capacity;
    private int count;

    public MyHashTable(int capacity) {
        this.capacity = capacity;
        bucketSet = new LinkNode[capacity];
        count = 0;
    }

    public int get(int key) {
        int index = hashFun(key);
        LinkNode linkNode = bucketSet[index];
        if (linkNode == null) {
            return -1;
        } else {
            return linkNode.val;
        }
    }

    public void put(int key, int value) {
        int index = hashFun(key);
        LinkNode linkNode = bucketSet[index];
        if (linkNode == null) {
            linkNode = new LinkNode(key, value);
            bucketSet[index] = linkNode;
            count++;
        } else {
            LinkNode cur = linkNode;
            while (cur != null) {
                if (cur.key == key) {
                    cur.val = value;
                    break;
                }
                cur = cur.next;
            }
            if (cur == null) {
                LinkNode nodeToInsert = new LinkNode(key, value);
                nodeToInsert.next = linkNode.next;
                linkNode.next = nodeToInsert;
                count++;
            }
        }
    }

    public boolean contains(int key) {
        return get(key) != -1;
    }

    private int hashFun(int key) {
        return key % capacity - 1;
    }

    public int getCount() {
        return count;
    }

    public int getCapacity() {
        return capacity;
    }

    public void printAll() {
        for (LinkNode linkNode : bucketSet) {
            LinkNode cur = linkNode;
            while (cur != null && cur.next != null) {
                System.out.print(cur);
                System.out.print("-->");
                cur = cur.next;
            }
            System.out.print(cur);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable(5);
        myHashTable.put(1, 2);
        myHashTable.put(1, 8);
        myHashTable.put(6, 88);
        myHashTable.put(11, 999);
        myHashTable.printAll();
    }
}

class LinkNode {
    int val;
    int key;
    LinkNode next;

    public LinkNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    @Override
    public String toString() {
        return "[" + key + "," + val + "]";
    }
}
