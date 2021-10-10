package datastructs;


import epi.test_framework.GenericTest;

import java.util.Arrays;

public class CustomList<T> {
    //create custom list class using arrays
    private T[] storage;
    private int size = 0;
    private static final int INIT_CAP = 10;

    @SuppressWarnings("unchecked")
    public CustomList(){
        storage = (T[])new Object[INIT_CAP];
    }

    public void add(T obj){
        if(size == storage.length)
            increaseCap();
        storage[size++] = obj;
    }

    public T get(int i){
        if(i >= size || i < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", i, size));
        return storage[i];
    }

    @SuppressWarnings("unchecked")
    public T remove(int i){
        if(i >= size || i < 0)
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", i, size));
        T item = storage[i];
        T[] arr = (T[])new Object[size - 1];
        int k = 0;
        for(int j = 0; j < size; j++)
        {
            if(j == i)
                continue;
            arr[k++] = storage[j];
        }
        storage = arr;
        size -= 1;
        return item;
    }

    public int size(){
        return size;
    }

    //Print method
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

    private void increaseCap(){
        int newSize = storage.length * 2;
        storage = Arrays.copyOf(storage, newSize);
    }
    public static void main(String[] args) {
        CustomList<Integer> list = new CustomList<>();
        System.out.println(list.toString());
        System.out.println(list.size());
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);

        //Remove elements from index
        list.remove(2);
        System.out.println(list);

        //Get element with index
        System.out.println( list.get(0) );
        System.out.println( list.get(1) );
    }
}
