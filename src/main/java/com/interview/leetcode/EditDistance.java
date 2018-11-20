package com.interview.leetcode;

public class EditDistance {

    /* Edit(i, j) >> min edit distance for word1[1...i] and word2[1...j]
       Time complexity: O(m * n)
       Space complexity: O(m * n)
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int [][] editDistance = new int[m+1][n+1];

        for(int j = 0; j <= n; j++) {
            editDistance[0][j] = j;
        }

        for(int i = 1; i <= m; i++) {
            editDistance[i][0] = i;
            for(int j = 1; j <= n; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    editDistance[i][j] = editDistance[i-1][j-1];
                } else {
                    editDistance[i][j] = Math.min(editDistance[i-1][j] + 1, Math.min(editDistance[i][j-1] + 1, editDistance[i-1][j-1] + 1));
                }
            }
        }
        return editDistance[m][n];
    }

    /* Edit(i, j) >> min edit distance for word1[1...i] and word2[1...j]
       Time complexity: O(m * n)
       Space complexity: O(n)
     */
    public static int minDistance2(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int [] editDistance = new int[n+1];

        for(int j = 0; j <= n; j++) {
            editDistance[j] = j;
        }

        for(int i = 1; i <= m; i++) {
            int prev = i;
            for(int j = 1; j <= n; j++) {
                int cur;
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    cur  = editDistance[j-1];
                } else {
                    cur = Math.min(editDistance[j], Math.min(editDistance[j-1], prev)) + 1;
                }
                editDistance[j-1] = prev;
                prev = cur;
            }
            editDistance[n] = prev;
        }
        return editDistance[n];
    }

    public static void main(String [] args) {
        System.out.println(minDistance2("horse", "ros"));
        System.out.println(minDistance2("intention", "execution"));
    }
}
