package com.interview.interviewbit.arrays;

import java.util.*;

//https://www.geeksforgeeks.org/find-a-repeating-and-a-missing-number/
public class RepeatedAndMissingNumberArray {

    public ArrayList<Integer> repeatedNumber(final List<Integer> A) {
        int[] arr = new int[A.size()];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = A.get(i);
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            int val = arr[Math.abs(arr[i]) - 1];
            if(val < 0) {
                result.add(Math.abs(arr[i]));
            } else {
                arr[Math.abs(arr[i]) - 1] = -1 * val;
            }
        }
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] > 0) {
                result.add(i + 1);
                break;
            }
        }
        return result;
    }

    public ArrayList<Integer> repeatedNumber2(final List<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();

        long sumActual = 0, sqSumActual = 0;

        for(int i = 0; i < A.size(); i++) {
            sumActual += (long) A.get(i);
            sqSumActual += (long) A.get(i) * (long) A.get(i);
        }

        long n = A.size();
        long sum = (n * (n + 1)) / 2;
        long sqSum = (n * (n + 1) * (2 * n + 1)) / 6;

        //A - B
        long diff = sumActual - sum;

        //A + B
        long sqDiff = (sqSumActual - sqSum) / diff;

        int a = (int) ((diff + sqDiff) / 2);
        int b = (int) (a - diff);
        result.add(a);
        result.add(b);
        return result;
    }
}
