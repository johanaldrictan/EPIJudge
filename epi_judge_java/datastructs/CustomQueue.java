package datastructs;

import java.util.Arrays;

public class CustomQueue<T> {
    private T[] storage;
    private int size;
    private static final int INIT_CAP = 10;

    @SuppressWarnings("unchecked")
    public CustomQueue() {
        storage = (T[]) new Object[INIT_CAP];
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T obj) {
        if (size == storage.length)
            increaseCap();
        storage[size++] = obj;
    }

    public T peek() {
        return storage[0];
    }

    public T pop() {
        if (size != 0) {
            T head = peek();
            storage = Arrays.copyOfRange(storage,1, storage.length);
            size--;
            return head;
        }
        return null;
    }

    private void increaseCap() {
        int newSize = storage.length * 2;
        storage = Arrays.copyOf(storage, newSize);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < size ;i++) {
            sb.append(storage[i].toString());
            if(i<size-1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomQueue<Integer> stack = new CustomQueue<>();
        System.out.println(stack.toString());
        System.out.println(stack.size());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack);

        //Remove elements from stack
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
        System.out.println(stack.isEmpty());
    }
}