package com.interview.leetcode;

import java.util.*;

public class TwoSumSmaller {

    //Time complexity: O(nlogn), Space complexity: O(1)
    public int twoSumLessThanK(int[] A, int K) {
        int result = -1;

        Arrays.sort(A);

        int low = 0;
        int high = A.length - 1;

        while(low < high) {
            int sum = A[low] + A[high];

            if(sum < K) {
                result = Math.max(result, sum);
                low++;
            } else {
                high--;
            }
        }

        return result;
    }
}
