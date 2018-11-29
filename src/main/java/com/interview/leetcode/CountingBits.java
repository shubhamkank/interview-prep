package com.interview.leetcode;

import java.util.Arrays;

public class CountingBits {

    /* Time complexity: O(n * sizeof(integer)) */
    public static int[] countBits(int num) {
        int[] result = new int[num+1];

        for(int i = 0; i <= num; i++) {
            result[i] = countOneBits(i);
        }
        return result;
    }

    private static int countOneBits(int num) {
        int count = 0;
        while(num != 0) {
            count++;
            num = num & (num-1);
        }
        return count;
    }

    public static void main(String[] args) {
        Arrays.stream(countBits(2)).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(countBits(5)).forEach(x -> System.out.print(x + " "));
    }
}
