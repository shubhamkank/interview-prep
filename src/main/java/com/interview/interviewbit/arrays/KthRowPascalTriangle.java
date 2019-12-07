package com.interview.interviewbit.arrays;

public class KthRowPascalTriangle {

    public int[] getRow(int A) {
        int[] result = new int[A + 1];
        result[0] = 1;

        for(int i = 1; i <= A; i++) {
            int[] temp = new int[A + 1];
            for(int j = 0; j <= i; j++) {
                temp[j] = (j > 0 ? result[j - 1] : 0) + result[j];
            }
            for(int j = 0; j <= A; j++) {
                result[j] = temp[j];
            }
        }
        return result;
    }

    //Time complexity: O(n^2), Space complexity: O(n)
    public int[] getRow2(int A) {
        int[] result = new int[A + 1];
        result[0] = 1;

        for(int i = 1; i <= A; i++) {
            for(int j = i; j >= 1; j--) {
                result[j] += result[j - 1];
            }
        }
        return result;
    }
}
