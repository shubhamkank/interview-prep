package com.interview.leetcode;

public class DistinctSubsequences {

    /* Dynamic Programming - Bottom Up Approach
       Time complexity: O(mn), Space complexity: O(mn)
       dp[i][j] - number of distinct subsequences of substring S[0..i] and t[0..j]
       dp[i][j] = 0 if j > i
       dp[i][j] = 1 if j == 0
       dp[i][j] = dp[i - 1][j] if s[i] != t[j]
       dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j] if s[i] == t[j]
       Since dp[i][j] only depends on the previous rows values, outer loop: top to bottom and inner loop: left to right
     */
    public static int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();

        if(n > m) {
            return 0;
        }

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i <= m; i++) {
            dp[i][0] = 1;
        }

        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n && j <= i; j++) {
                if(s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    /* Dynamic Programming - Bottom Up Approach
       Time complexity: O(mn), Space complexity: O(m)
       Since dp[i][j] only depends on the previous rows values, outer loop: top to bottom and inner loop: right to left
       Trick: Inner loop iteration from right to left enables avoiding to use a temp variable for storing the diagonal value
     */
    public static int numDistinct2(String s, String t) {
        int m = s.length();
        int n = t.length();

        if(n > m) {
            return 0;
        }

        int[] dp = new int[n + 1];

        for(int i = 0; i <= m; i++) {
            for(int j = n; j >= 0; j--) {
                if(j == 0) {
                    dp[j] = 1;
                } else if(i == 0) {
                    dp[j] = 0;
                } else {
                    dp[j] = dp[j] + (s.charAt(i - 1) == t.charAt(j - 1) ? dp[j - 1] : 0);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct2("rabbbit", "rabbit"));
        System.out.println(numDistinct2("babgbag", "bag"));
    }
}
