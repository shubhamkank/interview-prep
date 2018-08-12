package com.interview.leetcode;

public class ReverseInteger {

    public static int reverse(int x) {
        int reverseX = 0;
        boolean isNegative = false;

        if(x < 0) {
            isNegative = true;
            x = -1 * x;
        }

        while (x > 0) {
            int digit = x % 10;
            try {
                reverseX = Math.addExact(Math.multiplyExact(reverseX, 10), digit);
            } catch (ArithmeticException e) {
                return 0;
            }
            x = x / 10;
        }
        return isNegative ? -1 * reverseX : reverseX;
    }

    public static int reverse2(int x) {
        int reverseX = 0;

        while (x != 0) {
            int digit = x % 10;
            x = x / 10;
            if(reverseX > Integer.MAX_VALUE / 10 || (reverseX == Integer.MAX_VALUE && digit > 7))
                return 0;
            if(reverseX < Integer.MIN_VALUE / 10 || (reverseX == Integer.MIN_VALUE && digit < -8))
                return 0;
            reverseX = reverseX * 10 + digit;
        }
        return reverseX;
    }

    public static int reverse3(int x) {
        int reverseX = 0;

        while (x != 0) {
            int digit = x % 10;
            x = x / 10;
            int temp = reverseX * 10 + digit;
            if((temp - digit) / 10 != reverseX)
                return 0;
            reverseX = temp;
        }
        return reverseX;
    }

    public static void main(String[] args) {
        System.out.println(reverse3(123));
        System.out.println(reverse3(-123));
        System.out.println(reverse3(120));
        System.out.println(reverse3(1000000));
        System.out.println(reverse3(9));
        System.out.println(reverse3(1534236469));
    }
}
