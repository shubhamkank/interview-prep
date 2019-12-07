package com.interview.interviewbit.arrays;

import java.util.*;

//https://www.geeksforgeeks.org/minimum-length-unsorted-subarray-sorting-which-makes-the-complete-array-sorted/
public class MaxUnsortedSubarray {

    public ArrayList<Integer> subUnsort(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();

        int i = 0;
        while(i < A.size() - 1 && A.get(i) <= A.get(i + 1)) {
            i++;
        }
        int start = i;

        if(start == A.size() - 1) {
            result.add(-1);
            return result;
        }

        int j = A.size() - 1;
        while(j >= 0 && A.get(j - 1) <= A.get(j)) {
            j--;
        }
        int end = j;

        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for(int k = start; k <= end; k++) {
            max = Math.max(max, A.get(k));
            min = Math.min(min, A.get(k));
        }

        for(int k = 0; k < start; k++) {
            if(A.get(k) > min) {
                start = k;
                break;
            }
        }

        for(int k = A.size() - 1; k > end; k--) {
            if(A.get(k) < max) {
                end = k;
                break;
            }
        }

        result.add(start);
        result.add(end);
        return result;
    }
}
