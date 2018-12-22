package com.interview.leetcode;

public class UniqueBinarySearchTrees {

    /* Dynamic Programming Approach I
       Time complexity: O(n^3), Space complexity: O(n^2)
       UBST(i, j) = sum(UBST(i, r-1) * UBST(r+1, j)) r = [i, j]
     */
    public static int numTrees(int n) {
        int[][] dp = new int[n][n];

        for(int i = 0; i < n; i ++) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = 1;
            }
        }

        for(int i = n-2; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                int num = 0;
                for(int r = i; r <= j; r++) {
                    num += ((r-1) < 0 ? 1 : dp[i][r-1]) * ((r+1 >= n) ? 1 : dp[r+1][j]);
                }
                dp[i][j] = num;
            }
        }

        return dp[0][n-1];
    }

    /* Dynamic Programming Approach II
       Time complexity: O(n^2) Space complexity: O(n)
       G(n) = # of unique BST of length n
       F(i,n) = # of unique BST with root i  i=1..n
       G(n) = sum(F(i, n))  i=1..n
       F(i, n) = G(i-1) * G(n-i)
       G(n) = sum(G(i-1) * G(n-i)) i=1..n
       G(0) = 1, G(1) = 1
     */
    public static int numTrees2(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }

    /* Catalan Numbers
        C(0) = 1, C(n+1) = (2 * (2n + 1) * C(n)/(n + 2))
        Time complexity: O(n) Space complexity: O(1)
     */
    public static int numTrees3(int n) {
        long c = 1;
        for(int i = 0; i < n; i++) {
            c = (c * 2 * (2 * i + 1))/(i + 2);
        }
        return (int) c;
    }

    public static void main(String[] args) {
        System.out.println(numTrees3(1));
        System.out.println(numTrees3(2));
        System.out.println(numTrees3(3));
        System.out.println(numTrees3(4));
    }
}
