package com.interview.interviewbit.arrays;

import java.util.*;

public class MaxNonNegativeSubarray {

    public static ArrayList<Integer> maxset(List<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();

        long maxSum = 0; int maxStart = -1, maxEnd = -1;

        long sum = 0; int start = -1, end = -1;
        for(int i = 0; i < A.size(); i++) {
            if(A.get(i) < 0) {
                start = -1;
                end = -1;
                sum = 0;
            } else {
                sum += A.get(i);
                if(start == -1) {
                    start = i;
                }
                end = i;
                if(sum > maxSum) {
                    maxSum = sum;
                    maxStart = start;
                    maxEnd = end;
                } else if(sum == maxSum) {
                    if((end - start + 1) > (maxEnd - maxStart + 1)) {
                        maxSum = sum;
                        maxStart = start;
                        maxEnd = end;
                    }
                }
            }
        }

        if(maxStart == -1 && maxEnd == -1) {
            return result;
        }

        for(int i = maxStart; i <= maxEnd; i++) {
            result.add(A.get(i));
        }
        return result;
    }

    public ArrayList<Integer> maxset2(ArrayList<Integer> a) {
        long maxSum = 0;
        long newSum = 0;
        ArrayList<Integer> maxArray = new ArrayList<>();
        ArrayList<Integer> newArray = new ArrayList<>();
        for (Integer i : a) {
            if (i >= 0) {
                newSum += i;
                newArray.add(i);
            } else {
                newSum = 0;
                newArray = new ArrayList<>();
            }
            if ((maxSum < newSum) || ((maxSum == newSum) && (newArray.size() > maxArray.size()))) {
                maxSum = newSum;
                maxArray = newArray;
            }
        }
        return maxArray;
    }

    public static void main(String[] args) {
        System.out.println(maxset(Arrays.asList(756898537, -1973594324, -2038664370, -184803526, 1424268980)));
    }
}
