package datastructs.heap;

import java.util.Arrays;

public class MaxHeap {
    private int[] heap;
    private int size;
    private static final int INIT_CAP = 10;

    @SuppressWarnings("unchecked")
    public MaxHeap(){
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
        if(heap[pos] < heap[getLeftChild(pos)] ||
            heap[pos] < heap[getRightChild(pos)])   {
            if(heap[getLeftChild(pos)] > heap[getRightChild(pos)]){
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
        while (heap[current] > heap[getParent(current)]) {
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
        // Display message for better readability
        System.out.println("The Max Heap is ");

        MaxHeap maxHeap = new MaxHeap();

        // Inserting nodes
        // Custom inputs
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);
        maxHeap.insert(22);
        maxHeap.insert(9);

        // Calling maxHeap() as defined above
        maxHeap.print();

        // Print and display the maximum value in heap
        System.out.println("The max val is "
                + maxHeap.pop());

        System.out.println(maxHeap);
    }

}
