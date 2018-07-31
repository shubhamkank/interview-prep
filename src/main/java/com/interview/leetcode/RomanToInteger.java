package com.interview.leetcode;

import java.util.HashMap;

public class RomanToInteger {

    public static int romanToInt(String s) {
        int sum = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'I') {
                if(i+1 < s.length() && (s.charAt(i+1) == 'V' || s.charAt(i+1) == 'X')) {
                        sum += -1;
                } else {
                    sum += 1;
                }
            } else if(s.charAt(i) == 'V') {
               sum += 5;
            } else if(s.charAt(i) == 'X') {
                if(i+1 < s.length() && (s.charAt(i+1) == 'L' || s.charAt(i+1) == 'C')) {
                    sum += -10;
                } else {
                    sum += 10;
                }
            } else if(s.charAt(i) == 'L') {
                sum += 50;
            } else if(s.charAt(i) == 'C') {
                if(i+1 < s.length() && (s.charAt(i+1) == 'D' || s.charAt(i+1) == 'M')) {
                    sum += -100;
                } else {
                    sum += 100;
                }
            } else if(s.charAt(i) == 'D') {
                sum += 500;
            } else if(s.charAt(i) == 'M') {
                sum += 1000;
            }
        }

        return sum;
    }

    //O(n)
    public static int romanToInt2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int sum = getRomanCharToInt(s.charAt(s.length() - 1));

        for (int i = s.length() - 2; i >= 0; i--) {
            char first = s.charAt(i);
            char second = s.charAt(i + 1);
            if (getRomanCharToInt(first) < getRomanCharToInt(second)) {
                sum -= getRomanCharToInt(first);
            } else {
                sum += getRomanCharToInt(first);
            }
        }
        return sum;
    }

    public static int getRomanCharToInt(char c) {
        if(c == 'I') return 1;
        if(c == 'V') return 5;
        if(c == 'X') return 10;
        if(c == 'L') return 50;
        if(c == 'C') return 100;
        if(c == 'D') return 500;
        if(c == 'M') return 1000;
        return 0;
    }

    public static void main(String [] args) {
        System.out.println(romanToInt2("III"));
        System.out.println(romanToInt2("IV"));
        System.out.println(romanToInt2("IX"));
        System.out.println(romanToInt2("LVIII"));
        System.out.println(romanToInt2("MCMXCIV"));
    }
}
