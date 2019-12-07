package com.interview.interviewbit.arrays;

import java.util.*;

//https://www.geeksforgeeks.org/maximum-adjacent-difference-array-sorted-form/
public class MaxConsecutiveGap {

    public static int maximumGap(final List<Integer> A) {
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        for(int i = 0; i < A.size(); i++) {
            maxVal = Math.max(maxVal, A.get(i));
            minVal = Math.min(minVal, A.get(i));
        }

        int[] minBucket = new int[A.size() - 1];
        int[] maxBucket = new int[A.size() - 1];

        Arrays.fill(minBucket, Integer.MAX_VALUE);
        Arrays.fill(maxBucket, Integer.MIN_VALUE);

        float delta = (float) (maxVal - minVal) / (float) (A.size() - 1);

        for(int i = 0; i < A.size(); i++) {
            if(A.get(i) == minVal || A.get(i) == maxVal) {
                continue;
            }

            int index = (int) Math.floor((A.get(i) - minVal) / delta);

            if(minBucket[index] == Integer.MAX_VALUE) {
                minBucket[index] = A.get(i);
            } else {
                minBucket[index] = Math.min(minBucket[index], A.get(i));
            }

            if(maxBucket[index] == Integer.MIN_VALUE) {
                maxBucket[index] = A.get(i);
            } else {
                maxBucket[index] = Math.max(maxBucket[index], A.get(i));
            }
        }

        int prevVal = minVal;
        int maxGap = 0;

        for(int i = 0; i < A.size() - 1; i++) {
            if(minBucket[i] == Integer.MAX_VALUE) {
                continue;
            }
            maxGap = Math.max(maxGap, minBucket[i] - prevVal);
            prevVal = maxBucket[i];
        }
        maxGap = Math.max(maxGap, maxVal - prevVal);
        return maxGap;
    }
}
