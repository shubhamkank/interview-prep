package com.interview.leetcode;

import java.util.Arrays;
import java.util.List;

public class WordBreak {

    public static boolean wordBreak(String s, List<String> wordDict) {
        int n = s.length();
        boolean [] dp = new boolean[n + 1];

        dp[n] = true;

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(wordDict.contains(s.substring(i, j + 1)) && dp[j + 1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }

    public static void main(String [] args) {
        System.out.println(wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
