package com.interview.leetcode;

public class LongestPalindromicSubstring {

    //Time Complexity: O(n^3)
    public static String longestPalindrome(String s) {
        int maxLen = Integer.MIN_VALUE;
        String longestString = "";

        for(int i = 0; i < s.length(); i++) {
            for(int j = i; j < s.length(); j++) {
                if(isPalindrome(s.substring(i, j+1)) && (j-i+1) > maxLen) {
                    maxLen = j-i+1;
                    longestString = s.substring(i, j+1);
                }
            }
        }
        return longestString;
    }

    private static boolean isPalindrome(String s) {
        String reverse = new StringBuffer(s).reverse().toString();
        return s.equals(reverse);
    }

    //Time complexity: O(n^2), Space Complexity: O(n^2)
    public static String longestPalindrome2(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }
        int n = s.length();

        boolean [][] table = new boolean[n][n];

        int maxLen = 1;
        for(int i = 0; i < n; i++) {
            table[i][i] = true;
        }

        int startIdx = 0;
        for(int i = 0; i < n-1; i++) {
            if(s.charAt(i) == s.charAt(i+1)) {
                table[i][i+1] = true;
                startIdx = i;
                maxLen = 2;
            }
        }

        for(int k = 3; k <= n; k++) {
            for(int i = 0; i < n-k+1; i++) {
                int j = i + k - 1;

                if(table[i + 1][j - 1] && s.charAt(i) == s.charAt(j)) {
                    table[i][j] = true;
                    if(k > maxLen) {
                        startIdx = i;
                        maxLen = k;
                    }
                }
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    //Expand Around Centre Method
    //Time complexity: O(n^2), Space Complexity: O(1)
    public static String longestPalindrome3(String s) {
        if(s == null || s.isEmpty()) {
            return "";
        }

        int n = s.length();
        int maxLen = 1, startIdx = 0;
        int low, high;

        for(int i = 1; i < s.length(); i++) {
            //Even length substring with centres at i-1 and i
            low = i-1;
            high = i;
            while(low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
                if(high-low+1 > maxLen) {
                    maxLen = high-low+1;
                    startIdx = low;
                }
                low--;
                high++;
            }

            //Odd length substring with centre at i
            low = i-1;
            high = i+1;
            while(low >= 0 && high < n && s.charAt(low) == s.charAt(high)) {
                if(high-low+1 > maxLen) {
                    maxLen = high-low+1;
                    startIdx = low;
                }
                low--;
                high++;
            }
        }

        return s.substring(startIdx, startIdx + maxLen);
    }

    private static String preProcess(String s) {
        int n = s.length();
        if(n == 0) {
            return "^$";
        }
        StringBuilder ret = new StringBuilder("^");
        for(int i = 0; i < n; i++) {
            ret.append("#").append(s.charAt(i));
        }
        ret.append("#$");
        return ret.toString();
    }

    /**
     * Manacher's Algorithm
     * Time complexity: O(n)
     * Reference: https://www.youtube.com/watch?v=nbTSfrEfo6M
     * https://articles.leetcode.com/longest-palindromic-substring-part-ii/
     * https://www.youtube.com/watch?v=V-sEwsca1ak
     */
    public static String longestPalindrome4(String s) {
        String newS = preProcess(s);
        int n = newS.length();

        int []p = new int[n];
        int c = 0, r = 0;

        for(int i = 1; i < n-1; i++) {
            int iMirror = 2 * c - i;
            p[i] = i < r ? Math.min(r - i, p[iMirror]) : 0;

            while (newS.charAt(i + 1 + p[i]) == newS.charAt(i - 1 - p[i])) {
                p[i]++;
            }

            if(i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }

        int maxLen = 0, centreIdx = 0;
        for(int i = 1; i < n-1; i++) {
            if(p[i] > maxLen) {
                maxLen = p[i];
                centreIdx = i;
            }
        }
        return s.substring((centreIdx - maxLen - 1)/2, (centreIdx - maxLen - 1)/2 + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome3("babad"));
        System.out.println(longestPalindrome3("cbbd"));
        System.out.println(longestPalindrome3(""));
        System.out.println(longestPalindrome3(null));

        System.out.println(longestPalindrome4("babcbabcbaccba"));
    }
}
