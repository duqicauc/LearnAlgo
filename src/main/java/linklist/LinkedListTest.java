package linklist;

public class LinkedListTest {
    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(2, 4); //链表变为1->3->4
        linkedList.addAtIndex(1,2);   //链表变为1->2->3->4
        linkedList.print();
        System.out.println(linkedList.get(1));   //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1->3->4
        linkedList.print();
        System.out.println(linkedList.get(1)); //返回3

        linkedList.print();
        linkedList.reverse();
        linkedList.print();
    }


}
