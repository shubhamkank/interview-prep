package com.interview.interviewbit.arrays;

import java.util.*;

public class MaxAbsoluteDifference {

    //Time complexity: O(n)
    // |A[i] - A[j]| = A[i] - A[j] or
    // |A[i] - A[j]| = - (A[i] - A[j])
    // |i - j| = i - j or
    // |i - j| = - (i - j)
    // f(i, j) will have four cases using the above values
    // Case 1. f(i, j) = (A[i] + i) - (A[j] + j)
    // Case 2. f(i, j) = (A[i] - i) - (A[j] - j)
    // Case 3. f(i, j) = -(A[i] - i) + (A[j] - j)
    // Case 4. f(i, j) = -(A[i] + i) + (A[j] + j)
    // Case 1 & 4 are similar, Case 2 & 3 are similar
    // Calculate max and min values of A[i] + i and A[i] - i
    public int maxArr(ArrayList<Integer> A) {
        int min1 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE;

        int min2 = Integer.MAX_VALUE;
        int max2 = Integer.MIN_VALUE;

        for(int i = 0; i < A.size(); i++) {
            min1 = Math.min(min1, A.get(i) + i);
            max1 = Math.max(max1, A.get(i) + i);

            min2 = Math.min(min2, A.get(i) - i);
            max2 = Math.max(max2, A.get(i) - i);
        }
        return Math.max(max1 - min1, max2 - min2);
    }
}
