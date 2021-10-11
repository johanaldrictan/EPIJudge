package datastructs;

import java.util.Arrays;

public class CustomStack<T> {
    private T[] storage;
    private int size;
    private static final int INIT_CAP = 10;

    @SuppressWarnings("unchecked")
    public CustomStack(){
        storage = (T[])new Object[INIT_CAP];
        size = 0;
    }

    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }

    public void push(T obj){
        if(size == storage.length)
            increaseCap();
        storage[size++] = obj;
    }
    public T peek() { return storage[size-1]; }
    public T pop() {
        if(size != 0) {
            T end = peek();
            storage[size-- - 1] = null;
            return end;
        }
        return null;
    }

    private void increaseCap(){
        int newSize = storage.length * 2;
        storage = Arrays.copyOf(storage, newSize);
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = size - 1; i >= 0 ;i--) {
            sb.append(storage[i].toString());
            if(i > 0){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public static void main(String[] args) {
        CustomStack<Integer> stack = new CustomStack<>();
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
