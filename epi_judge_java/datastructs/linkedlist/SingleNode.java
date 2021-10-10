package datastructs.linkedlist;

public class SingleNode<T> {
    private T value;
    private SingleNode<T> next;

    public SingleNode(T initVal, SingleNode<T> initNext){
        value = initVal;
        next = initNext;
    }

    public SingleNode(T initVal){
        this(initVal, null);
    }

    public void setValue(T newVal){
        value = newVal;
    }

    public void setNext(SingleNode<T> next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public SingleNode<T> getNext() {
        return next;
    }

}
