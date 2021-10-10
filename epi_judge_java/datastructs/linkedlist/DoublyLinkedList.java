package datastructs.linkedlist;

import java.util.NoSuchElementException;

public class DoublyLinkedList<T> {
    int size;
    private DoubleNode<T> first; //first element

    public DoublyLinkedList(){
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
        DoubleNode<T> temp = new DoubleNode<T>(value, first, null);
        if(temp.getNext() != null)
            temp.getNext().setPrev(temp);
        first = temp;
        size++;
    }

    public void addLast(T value){
        DoubleNode<T> temp = first;
        while(temp != null){
            if(temp.getNext() == null){
                DoubleNode<T> newNode = new DoubleNode<T>(value);
                newNode.setNext(null);
                newNode.setPrev(temp);
                temp.setNext(newNode);
                size++;
                break;
            }
            temp = temp.getNext();
        }
    }

    public void insert(T value, int index){
        DoubleNode<T> temp = first;
        if(size == 0)
            addFirst(value);
        if(index == size -1)
            addLast(value);
        if(index >= size)
            addLast(value);
        for(int i = 0; temp != null && i < index; i++)
            temp = temp.getNext();

        DoubleNode<T> newNode = new DoubleNode<T>(value);
        newNode.setNext(temp.getNext());
        newNode.setPrev(temp);
        temp.setNext(newNode);
        size++;
    }

    public T remove(T value){
        T deleted = null;
        DoubleNode<T> temp = first;
        if(value == temp.getValue()){
            deleted = temp.getValue();
            first = temp.getNext();
            if(temp.getNext() != null)
                temp.getNext().setPrev(null);
            size--;
            return deleted;
        }
        while(temp != null){
            if(temp.getNext().getValue() == value){
                DoubleNode<T> next = temp.getNext().getNext();
                deleted = temp.getNext().getValue();
                temp.setNext(next);
                if(next != null)
                    next.setPrev(temp);
                size--;
                break;
            }
            temp = temp.getNext();
        }
        return deleted;
    }

    public void clear(){
        first = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public void printBackwards(){
        DoubleNode<T> temp = first;
        if(temp == null)
            return;
        while(temp.getNext() != null){
            temp = temp.getNext();
        }
        while(temp != null){
            System.out.print(temp.getValue() + " ");
            temp = temp.getPrev();
        }
        System.out.println();
    }

    public void printList(){
        DoubleNode<T> temp = first;
        while(temp != null){
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    public String toString(){
        String s = "[";

        DoubleNode<T> temp = first;  // start from the first node
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
        DoublyLinkedList<Integer> linkedList = new DoublyLinkedList<>();
        for (int k = 1; k <= 5; k++){
            linkedList.addFirst(k);
        }

        linkedList.printList();
        linkedList.addLast(100);
        linkedList.printList();
        System.out.println("Backwards");
        linkedList.printBackwards();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.remove(2);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.remove(100);
        linkedList.remove(5);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.insert(2, 1);
        linkedList.printList();
        System.out.println(String.format("Size: %d", linkedList.size()));

        linkedList.clear();
        System.out.println(String.format("Size: %d", linkedList.size()));
    }
}
