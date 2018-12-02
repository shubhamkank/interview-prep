package com.interview.leetcode;

import java.util.Arrays;

public class CountingBits {

    /* Time complexity: O(n * sizeof(integer)), Space complexity: O(n) */
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

    /* Last Set Bit Approach
    Time complexity: O(n), Space complexity: O(n) */
    public static int[] countBits2(int num) {
        int[] result = new int[num+1];
        result[0] = 0;

        for(int i = 1; i <= num; i++) {
            result[i] = 1 + result[i & i-1];
        }
        return result;
    }

    /* Least Significant Bit Approach
    Time complexity: O(n), Space complexity: O(n) */
    public static int[] countBits3(int num) {
        int[] result = new int[num+1];

        for(int i = 1; i <= num; i++) {
            result[i] = result[i >> 1] + (i & 1);
        }
        return result;
    }

    /* Most Significant Bit Approach
    Time complexity: O(n), Space complexity: O(n) */
    public static int[] countBits4(int num) {
        int[] result = new int[num+1];
        int i = 0, b = 1;

        while(b <= num) {
            while(i < b && i + b <= num) {
                result[i + b] = result[i] + 1;
                i++;
            }
            i = 0;
            b <<= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Arrays.stream(countBits4(2)).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(countBits4(5)).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(countBits4(0)).forEach(x -> System.out.print(x + " "));
        System.out.println();
        Arrays.stream(countBits4(1)).forEach(x -> System.out.print(x + " "));
    }
}
