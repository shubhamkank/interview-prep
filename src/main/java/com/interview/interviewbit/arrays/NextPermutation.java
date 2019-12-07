package com.interview.interviewbit.arrays;

public class NextPermutation {

    //Time complexity: O(n), Amortised O(1)
    public static int[] nextPermutation(int[] A) {
        int k, l;
        for(k = A.length - 2; k >= 0; k--) {
            if(A[k] < A[k + 1]) {
                break;
            }
        }

        if(k < 0) {
            reverseArray(A, 0, A.length - 1);
            return A;
        }

        for(l = A.length - 1; l > k; l--) {
            if(A[l] > A[k]) {
                swap(A, l, k);
                break;
            }
        }
        reverseArray(A, k + 1, A.length - 1);
        return A;
    }

    private static void reverseArray(int[] A, int start, int end) {
        while (start < end) {
            swap(A, start, end);
            start++;
            end--;
        }
    }

    private static void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    public static void main(String[] args) {
        int[] A = nextPermutation(new int[] {444,994,508,72,125,299,181,238,354,223,691,249,838,890,758,675,424,199,201,788,609,582,979,259,901,371,766,759,983,728,220,16,158,822,515,488,846,321,908,469,84,460,961,285,417,142,952,626,916,247,116,975,202,734,128,312,499,274,213,208,472,265,315,335,205,784,708,681,160,448,365,165,190,693,606,226,351,241,526,311,164,98,422,363,103,747,507,669,153,856,701,319,695,52});
        for(int x : A) {
            System.out.print(x + " ");
        }
    }
}
