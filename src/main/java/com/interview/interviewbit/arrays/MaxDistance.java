package com.interview.interviewbit.arrays;

import java.util.*;

//geeksforgeeks.org/given-an-array-arr-find-the-maximum-j-i-such-that-arrj-arri/
public class MaxDistance {

    //Time complexity: O(n), Space complexity: O(n)
    public int maximumGap(final List<Integer> A) {
        int[] leftMin = new int[A.size()];
        int[] rightMax = new int[A.size()];

        for(int i = 0; i < leftMin.length; i++) {
            if (i == 0) {
                leftMin[i] = A.get(i);
                continue;
            }
            leftMin[i] = Math.min(A.get(i), leftMin[i - 1]);
        }

        for(int i = rightMax.length - 1; i >= 0; i--) {
            if(i == rightMax.length - 1) {
                rightMax[i] = A.get(i);
                continue;
            }
            rightMax[i] = Math.max(A.get(i), rightMax[i + 1]);
        }

        int i = 0, j = 0;
        int maxGap = 0;

        while(i < leftMin.length && j < rightMax.length) {
            if(leftMin[i] <= rightMax[j]) {
                maxGap = Math.max(maxGap, j - i);
                j++;
            } else {
                i++;
            }
        }
        return maxGap;
    }
}
