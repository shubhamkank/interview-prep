package com.interview.leetcode;

public class NumberOfOneBits {

    // Time complexity: O(1), Space complexity: O(1)
    public static int hammingWeight(int n) {
        int mask = 1;
        int count = 0;

        for(int i = 0; i < 32; i++) {
            if((n & mask) != 0) {
                count++;
            }
            mask <<= 1;
        }
        return count;
    }

    // Time complexity: O(1), Space complexity: O(1)
    public static int hammingWeight2(int n) {
        int count = 0;

        while (n != 0) {
            count++;
            //Anding n with n-1 flips the least significant 1-bit in n to 0
            n &= (n - 1);
        }
        return count;
    }

    /* Right shift n (n >>>= 1) and AND with 1 till n != 0
       >>> for unsigned shift. We are asked to treat the integer as unsigned. Therefore,
       1. We can't use condition as n > 0 - this will fail on Integer.MAX_VALUE + 1
       2. We can't use signed right shift operation - this will cause infinite loop for Integer.MAX_VALUE + 1 */
    public static int hammingWeight3(int n) {
        int count = 0;
        while (n != 0) {
            count += (n & 1);
            n >>>= 1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight3(11));
        System.out.println(hammingWeight3(15));
        System.out.println(hammingWeight3(128));
    }
}
