package datastructs;

import java.util.ArrayList;

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
        int arr[] = {44, 2, 13, 5, 22, 69, 7};
        ob.bubbleSort(arr);
        System.out.println("Sorted array(BubbleSort): ");
        ob.printArray(arr);

        arr = new int[]{44, 2, 13, 5, 22, 69, 7};
        ob.insertionSort(arr);
        System.out.println("Sorted array(InsertionSort): ");
        ob.printArray(arr);

        arr = new int[]{44, 2, 13, 5, 22, 69, 7};
        ob.selectionSort(arr);
        System.out.println("Sorted array(SelectionSort): ");
        ob.printArray(arr);

        arr = new int[]{44, 2, 13, 5, 22, 69, 7};
        ob.mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted array(MergeSort): ");
        ob.printArray(arr);

    }
}
