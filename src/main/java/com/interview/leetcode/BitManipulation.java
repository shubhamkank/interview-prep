package com.interview.leetcode;

public class BitManipulation {

    public static int countOne(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n-1);
            count++;
        }
        return count;
    }

    public static int getSum(int a, int b) {
        if(b == 0)
            return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return getSum(sum, carry);
    }

    public static int getSubtract(int a, int b) {
        if(b == 0)
            return a;
        int sum = a ^ b;
        int borrow = (~a & b) << 1;
        return getSum(sum, borrow);
    }

    public static boolean isPowerOfFour(int n) {
        return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) == n;
    }

    public static boolean isPowerOfTwo(int n) {
        return (n & (n - 1)) == 0;
    }

    public static int missingNumber(int [] num) {
        int result = 0;
        for(int i = 0; i < num.length; i++) {
            result ^= i;
            result ^= num[i];
        }
        return result ^ num.length;
    }

    public static int reverseBits(int n) {
        int rev = 0;
        while(n > 0) {
            rev <<= 1;

            if((int)(n & 1) == 1) {
                rev ^= 1;
            }

            n >>= 1;
        }
        return rev;
    }

    public static int largestPower(int n) {
        n = n | (n >> 1);
        n = n | (n >> 2);
        n = n | (n >> 4);
        n = n | (n >> 8);
        n = n | (n >> 12);

        return (n+1) >> 1;
    }

    public static int bitwiseRangeAnd(int m, int n) {
        int a = 0;
        while(m != n) {
            m >>= 1;
            n >>= 1;
            a++;
        }
        return m << a;
    }

    public static void main(String[] args) {
        System.out.println(largestPower(3));
        System.out.println(largestPower(1));
        System.out.println(largestPower(15));
        System.out.println(largestPower(255));
        System.out.println(largestPower(-3));

        System.out.println(reverseBits(11));

        System.out.println(bitwiseRangeAnd(5, 7));
        System.out.println(bitwiseRangeAnd(7, 15));

        System.out.println(getSubtract(7, 4));
    }
}
