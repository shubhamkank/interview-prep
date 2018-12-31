package com.interview.leetcode;

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

    //Manacher's Algorithm
    public static int countSubstrings3(String s) {
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(countSubstrings("abc"));
        System.out.println(countSubstrings("aaa"));
        System.out.println(countSubstrings("babab"));
    }
}
