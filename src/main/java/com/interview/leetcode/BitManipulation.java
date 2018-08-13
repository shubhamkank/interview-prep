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

    public static void main(String[] args) {
        System.out.println(getSum(1, 2));
        System.out.println(getSum(5, 5));
        System.out.println(getSum(3, 3));
        System.out.println(getSum(-1, 3));
    }
}
