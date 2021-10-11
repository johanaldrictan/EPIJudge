package datastructs;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class CustomSort {
    public void bubbleSort(int arr[])
    {
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            for(int j = 0; j < n - i - 1; j++){
                if(arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }

    public void insertionSort(int arr[]){
        int n = arr.length;
        for(int i = 1; i < n; i++){
            int current = arr[i];
            int j = i - 1;
            while(j >= 0 && arr[j] > current){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j + 1] = current;
        }
    }

    public void selectionSort(int arr[]){
        int n = arr.length;
        for(int i = 0; i < n - 1; i++){
            int indexOfMin = i;
            for(int j = i+1; j < n; j++){
                if(arr[j] < arr[indexOfMin])
                    indexOfMin = j;
            }
            int temp = arr[i];
            arr[i] = arr[indexOfMin];
            arr[indexOfMin] = temp;
        }
    }

    public void merge(int arr[], int left, int mid, int right){
        int leftArrSize = mid - left + 1;
        int rightArrSize = right - mid;

        int[] tempLeft = new int[leftArrSize];
        int[] tempRight = new int[rightArrSize];

        for(int i = 0; i < leftArrSize; i++)
            tempLeft[i] = arr[left + i];
        for(int j = 0; j < rightArrSize; j++)
            tempRight[j] = arr[mid + 1 + j];

        int indexLeft = 0;
        int indexRight = 0;
        int indexMerged = left;

        while (indexLeft < leftArrSize && indexRight < rightArrSize) {
            if (tempLeft[indexLeft] <= tempRight[indexRight])
                arr[indexMerged++] = tempLeft[indexLeft++];
            else
                arr[indexMerged++] = tempRight[indexRight++];
        }
        while (indexLeft < leftArrSize) //copy leftovers
            arr[indexMerged++] = tempLeft[indexLeft++];

        while (indexRight < rightArrSize)
            arr[indexMerged++] = tempRight[indexRight++];
    }

    public void mergeSort(int arr[], int start, int end){
        if(start >= end)
            return;
        int mid = start + (end - start)/2;
        mergeSort(arr, start, mid);
        mergeSort(arr, mid +1, end);
        merge(arr, start, mid, end);
    }

    public void iterativeMergeSort(int arr[], int n){
        for(int max = 1; max <= n-1; max *= 2){ //max size of subarray
            for(int start = 0; start < n-1; start += 2*max){ //at each loop take each mini subarray
                int mid = Math.min(start + max -1, n-1);//at the end or somewhere in the middle of subarray
                int end = Math.min(start + 2*max -1, n-1);//end of the subarray or end of list
                merge(arr, start, mid, end);
            }
        }
    }

    public void quickSort(int arr[], int low, int high){
        if(low < high){
            int part = partition(arr, low, high);
            quickSort(arr, low, part -1);
            quickSort(arr, part + 1, high);
        }
    }

    public int partition(int arr[], int low, int high){
        int pivot = arr[high];
        int i = low - 1;

        for(int j = low; j <= high -1; j++){
            if(arr[j] < pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
    public void heapify(int arr[], int heapSize, int parent){
        int max = parent; // Initialize largest as root
        int l = 2 * parent + 1; // left = 2*i + 1
        int r = 2 * parent + 2; // right = 2*i + 2
        if(l < heapSize && arr[l] > arr[max])
            max = l;
        if(r < heapSize && arr[r] > arr[max])
            max = r;

        if(max != parent){
            int temp = arr[max];
            arr[max] = arr[parent];
            arr[parent] = temp;
            heapify(arr, heapSize, max);
        }

    }
    public void buildMaxHeap(int arr[]){
        int n = arr.length;
        for(int i = n/2 - 1; i >= 0; i--){
            heapify(arr, n, i);
        }
    }
    public void heapSort(int arr[]){
        buildMaxHeap(arr);
        int n = arr.length;
        for(int i = n-1 ; i > 0; i--){
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }
    }

    /* Prints the array */
    void printArray(int arr[])
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver method to test above
    public static void main(String args[])
    {
        CustomSort ob = new CustomSort();
        int arr[] = {44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        long start = System.nanoTime();
        ob.bubbleSort(arr);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("Sorted array(BubbleSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.insertionSort(arr);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(InsertionSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.selectionSort(arr);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(SelectionSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.mergeSort(arr, 0, arr.length - 1);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(MergeSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.iterativeMergeSort(arr, arr.length);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(Iterative MergeSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.quickSort(arr, 0, arr.length-1);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(QuickSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

        arr = new int[]{44, 2, 13, 5, 22, 69, 7, 51, 42, 97, 45, 28, 29};
        start = System.nanoTime();
        ob.heapSort(arr);
        finish = System.nanoTime();
        timeElapsed = finish - start;
        System.out.println("Sorted array(HeapSort): ");
        ob.printArray(arr);
        System.out.println(String.format("Sort finished in %d ns", timeElapsed));

        System.out.println();

    }
}
