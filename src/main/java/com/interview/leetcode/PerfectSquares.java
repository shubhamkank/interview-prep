package com.interview.leetcode;

public class PerfectSquares {

    /* Dynamic Programming - Bottom-up approach
       Time complexity: O(n * sqrt(n)), Space complexity: O(n)
       dp[n] = min(1 + dp(n - k^2)) k = 1 to sqrt(n)
       Similar to Knapsack problem.
     */
    public static int numSquares(int n) {
        int[] dp = new int[n+1];
        dp[0] = 0;

        for(int i = 1; i <= n; i++) {
            int sqrtN = (int) Math.sqrt(i);
            int answer = Integer.MAX_VALUE;
            for(int k = 1; k <= sqrtN; k++) {
                answer = Math.min(answer, 1 + dp[i - k*k]);
            }
            dp[i] = answer;
        }
        return dp[n];
    }

    public static int numSquares2(int n) {
        if(isSquare(n)) {
            return 1;
        }

        // The result is 4 if and only if n can be written in the
        // form of 4^k*(8*m + 7). Please refer to Legendre's three-square theorem.
        while ((n & 3) == 0) { // n%4 == 0
            n >>= 2;
        }
        if ((n & 7) == 7) {  // n%8 == 7
            return 4;
        }

        // Check whether 2 is the result
        int sqrtN = (int)(Math.sqrt(n));
        for(int i = 1; i <= sqrtN; i++) {
            if (isSquare(n - i*i)) {
                return 2;
            }
        }

        return 3;
    }

    private static boolean isSquare(int n) {
        int sqrtN = (int) Math.sqrt(n);
        return sqrtN * sqrtN == n;
    }

    //TODO: Implement BFS and DFS solution

    public static void main(String[] args) {
        System.out.println(numSquares2(0));
        System.out.println(numSquares2(1));
        System.out.println(numSquares2(2));
        System.out.println(numSquares2(3));
        System.out.println(numSquares2(4));
        System.out.println(numSquares2(12));
        System.out.println(numSquares2(13));
    }
}
