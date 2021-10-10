package datastructs.linkedlist;

public class DoubleNode<T> {
    private T value;
    private DoubleNode<T> next;
    private DoubleNode<T> prev;

    public DoubleNode(T initVal, DoubleNode<T> initNext, DoubleNode<T> initPrev){
        value = initVal;
        next = initNext;
        prev = initPrev;
    }

    public DoubleNode(T initVal){
        this(initVal, null, null);
    }

    public void setValue(T newVal){
        value = newVal;
    }

    public void setNext(DoubleNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public DoubleNode<T> getNext() {
        return next;
    }

    public DoubleNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleNode<T> prev) {
        this.prev = prev;
    }
}
