package datastructs.heap;

import java.util.Arrays;

public class MinHeap {
    private int[] heap;
    private int size;
    private static final int INIT_CAP = 10;

    @SuppressWarnings("unchecked")
    public MinHeap(){
        size = 0;
        heap = new int[INIT_CAP];
    }

    private int getParent(int pos) { return (pos - 1)/2; }
    private int getLeftChild(int pos) { return 2 * pos; }
    private int getRightChild(int pos) { return (2 * pos) + 1; }

    private boolean isLeaf(int pos){ return pos > (size/2) && pos <= size; }

    private void swap(int l, int r){
        int temp = heap[l];
        heap[l] = heap[r];
        heap[r] = temp;
    }

    private void heapify(int pos){
        if(isLeaf(pos))
            return;
        if(heap[pos] > heap[getLeftChild(pos)] ||
                heap[pos] > heap[getRightChild(pos)])   {
            if(heap[getLeftChild(pos)] < heap[getRightChild(pos)]){
                swap(pos, getLeftChild(pos));
                heapify(getLeftChild(pos));
            }
            else{
                swap(pos, getRightChild(pos));
                heapify(getRightChild(pos));
            }
        }
    }

    public void insert(int value){
        if(size == heap.length)
            increaseCap();
        heap[size] = value;
        int current = size;
        while (heap[current] < heap[getParent(current)]) {
            swap(current, getParent(current));
            current = getParent(current);
        }
        size++;
    }

    public int pop(){
        int popped = heap[0];
        heap = Arrays.copyOfRange(heap,1, heap.length);
        heapify(0);
        return popped;
    }

    public void print()
    {
        for (int i = 0; i <= (size / 2) - 1; i++) {
            System.out.print(
                    " PARENT : " + heap[i]
                            + " LEFT CHILD : " + heap[2 * i + 1]
                            + " RIGHT CHILD :" + heap[2 * i + 2]);
            System.out.println();
        }
    }

    //Print method
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for(int i = 0; i < size ;i++) {
            sb.append(heap[i]);
            if(i<size-1){
                sb.append(",");
            }
        }
        sb.append(']');
        return sb.toString();
    }

    private void increaseCap(){
        int newSize = heap.length * 2;
        heap = Arrays.copyOf(heap, newSize);
    }

    public static void main(String[] arg)
    {

        // Display message
        System.out.println("The Min Heap is ");

        MinHeap minHeap = new MinHeap();

        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);
        minHeap.insert(22);
        minHeap.insert(9);

        // Print all elements of the heap
        minHeap.print();

        // Removing minimum value from above heap
        // and printing it
        System.out.println("The Min val is "
                + minHeap.pop());
    }

}