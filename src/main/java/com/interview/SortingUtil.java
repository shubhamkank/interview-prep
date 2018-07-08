package com.interview;

public class SortingUtil {

    //O(n^2)
    public static void selectionSort(int [] a) {
        int i = -1;
        int n = a.length;

        while(i <= n-2) {
            int minIndex = i+1;
            for(int j = i+2; j < n; j++) {
                if(a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = a[minIndex];
            i++;
            a[minIndex] = a[i];
            a[i] = temp;
        }
    }

    //O(n^2) - worst case = reverse sorted
    //O(n) - best case - already sorted
    public static void bubbleSort(int [] a) {
        int n = a.length;
        boolean flag;

        for(int i = 0; i < n-1; i++) {
            flag = false;
            for(int j = 0; j < n-i-1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    flag = true;
                }
            }
            if(!flag) {
                break;
            }
        }
    }

    //O(n^2) - worst case = reverse sorted
    //O(n) - best case - already sorted
    public static void insertionSort(int [] a) {
        int n = a.length;
        for(int i = 1; i < n; i++) {
            int value = a[i];
            int j = i-1;
            for(;j >= 0; j--) {
                if(a[j] > value) {
                    a[j+1] = a[j];
                } else {
                    break;
                }
            }
            a[j+1] = value;
        }
    }

    //O(nlogn) - worst, average & best case
    //O(n) - auxiliary space
    public static void mergeSort(int [] a, int l, int r) {
        if(l < r) {
            int m = (l + r)/2;
            mergeSort(a, l, m);
            mergeSort(a, m+1, r);
            merge(a, l, m, r);
        }
    }

    private static void merge(int [] a, int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;

        int [] left = new int[n1];
        int [] right = new int[n2];

        for(int i = 0; i < n1; i++) {
            left[i] = a[l + i];
        }

        for(int i = 0; i < n2; i++) {
            right[i] = a[m + i + 1];
        }

        int i = 0, j = 0;
        int k = l;

        while(i < n1 && j < n2) {
            if(left[i] < right[j]) {
                a[k] = left[i];
                i++;
            } else {
                a[k] = right[j];
                j++;
            }
            k++;
        }

        while(i < n1) {
            a[k] = left[i];
            i++;
            k++;
        }

        while(j < n2) {
            a[k] = right[j];
            j++;
            k++;
        }
    }

    //O(nlogn) - best, average case
    //O(n^2) - worst case - sorted array - unbalanced partition
    //Space complexity - O(logn) - best case; O(n) - worst case
    public static void quickSort(int [] a, int l, int r) {
        if(l < r) {
            int pivotIndex = partition(a, l, r);
            quickSort(a, l, pivotIndex - 1);
            quickSort(a, pivotIndex + 1, r);
        }
    }

    private static int partition(int [] a, int l, int r) {
        int pivot = a[r];
        int pivotIndex = l;

        for(int i = l; i < r; i++) {
            if(a[i] <= pivot) {
                int temp = a[pivotIndex];
                a[pivotIndex] = a[i];
                a[i] = temp;
                pivotIndex++;
            }
        }

        int temp = a[pivotIndex];
        a[pivotIndex] = a[r];
        a[r] = temp;
        return pivotIndex;
    }
}
