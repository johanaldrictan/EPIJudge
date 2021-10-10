package datastructs.linkedlist;

import datastructs.CustomList;

import java.util.NoSuchElementException;

public class SinglyLinkedList<T> {

    int size = 0;
    private SingleNode<T> first; //first element

    public SinglyLinkedList(){
        first = null;
        size = 0;
    }

    public T getFirst(){
        if(first == null)
            throw new NoSuchElementException();
        else
            return first.getValue();
    }

    public void addFirst(T value){
        first = new SingleNode<T>(value, first);
        size++;
    }

    public void addLast(T value){
        SingleNode<T> temp = first;
        while(temp != null){
            if(temp.getNext() == null){
                SingleNode<T> newNode = new SingleNode<T>(value);
                newNode.setNext(temp.getNext());
                temp.setNext(newNode);
                size++;
                break;
            }
            temp = temp.getNext();
        }
    }

    public T remove(T value){
        T deleted = null;
        SingleNode<T> temp = first;
        if(value == temp.getValue()){
            first = temp.getNext();
            size--;
            return temp.getValue();
        }
        while(temp != null){
            if(temp.getNext().getValue() == value){
                SingleNode<T> next = temp.getNext().getNext();
                deleted = temp.getNext().getValue();
                temp.setNext(next);
                size--;
                break;
            }
            temp = temp.getNext();
        }
        return deleted;
    }

    public void clear(){
        first = null;
    }

    public int size(){
        return size;
    }

    public void printList(){
        SingleNode<T> temp = first;
        while(temp != null){
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public String toString(){
        String s = "[";

        SingleNode<T> temp = first;  // start from the first node
        while (temp != null){
            s += temp.getValue(); // append the data
            temp = temp.getNext();      // go to next node
            if (temp != null)
                s += ", ";
        }
        s += "]";
        return s;
    }
    public static void main(String[] args) {
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();
        for (int k = 1; k <= 5; k++){
            linkedList.addFirst(k);
        }

        linkedList.printList();
        linkedList.addLast(100);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.remove(2);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.remove(100);
        linkedList.remove(5);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));
    }
}
