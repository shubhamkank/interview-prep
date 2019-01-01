package com.interview.leetcode;

import java.util.Arrays;

public class PalindromicSubstrings {

    /* Dynamic Programming
       Time complexity: O(n^2), Space complexity: O(n^2) */
    public static int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        //Length = 1
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            count++;
        }

        //Length = 2
        for(int i = 0; i < n - 1; i++) {
            if(s.charAt(i) == s.charAt(i + 1)) {
                dp[i][i + 1] = true;
                count++;
            }
        }

        //Length >= 3
        for(int k = 3; k <= n; k++) {
            for(int i = 0; i < n - k + 1; i++) {
                int j = i + k - 1;
                if(dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    count++;
                }
            }
        }
        return count;
    }

    /* Expand Around Centre
       Time complexity: O(n^2), Space complexity: O(1) */
    public static int countSubstrings2(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            //Odd length palindromes
            count += countPalindromes(s, i, i);

            //Even length palindromes
            count += countPalindromes(s, i, i + 1);
        }
        return count;
    }

    private static int countPalindromes(String s, int left, int right) {
        int count = 0;
        while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    /* Manacher's Algorithm
       Time complexity: O(n), Space complexity: O(n)
       References:
       https://www.youtube.com/watch?v=nbTSfrEfo6M
       https://algs4.cs.princeton.edu/53substring/Manacher.java.html
       https://www.hackerearth.com/practice/algorithms/string-algorithm/manachars-algorithm/tutorial/ */
    public static int countSubstrings3(String s) {
        char[] t = preprocess(s);
        int[] p = new int[t.length];

        int center = 0, right = 0;
        for(int i = 1; i < t.length - 1; i++) {
            //Also, can be written as 2 * centre - i
            int mirror = center - (i - center);

            //If the next candidate for center lies within the right boundary of the current palindrome,
            //copy the mirror value. (r - i) is for the case where len[mirror] goes beyond the left boundary
            if(i < right) {
                p[i] = Math.min(right - i, p[mirror]);
            }

            //Attempt to expand palindrome centered at i
            while(t[i + (1 + p[i])] == t[i - (1 + p[i])]) {
                p[i]++;
            }

            //If palindrome centered at i expands past right, adjust center based on expanded palindrome
            if(i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        return (Arrays.stream(p).sum() + s.length()) / 2;
    }

    /* Transform s into t.
       For example, if s = "abba", then t = "$#a#b#b#a#@"
       the # are interleaved to avoid even/odd-length palindromes uniformly
       $ and @ are prepended and appended to each end to avoid bounds checking */
    private static char[] preprocess(String s) {
        char[] t = new char[s.length() * 2 + 3];
        t[0] = '$';
        t[s.length() * 2 + 2] = '@';

        for(int i = 0; i < s.length(); i++) {
            t[2 * i + 1] = '#';
            t[2 * i + 2] = s.charAt(i);
        }
        t[s.length() * 2 + 1] = '#';
        return t;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings3("abc"));
        System.out.println(countSubstrings3("aaa"));
        System.out.println(countSubstrings3("babab"));
    }
}
