package com.interview.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class WordBreak2 {

    public static List<String> wordBreak(String s, List<String> wordDict) {
        ArrayList<String>[] dp = new ArrayList[s.length() + 1];
        ArrayList<String> initial = new ArrayList<>();
        initial.add("");
        dp[0] = initial;

        for(int i = 1; i <= s.length(); i++) {
            ArrayList<String> tempSentences = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(wordDict.contains(s.substring(j, i))) {
                    for(String tempSentence : dp[j]) {
                        tempSentences.add(tempSentence + (tempSentence.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = tempSentences;
        }
        return dp[s.length()];
    }

    /* Backtracking/DFS Approach
       Time complexity: O(len(wordDict) * len(s/minWordLenInDict))*/
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<>());
    }

    private static List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> mem) {
        if(mem.containsKey(s)) {
            return mem.get(s);
        }

        List<String> result = new ArrayList<>();
        for(String word : wordDict) {
            if(s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.length() == 0) {
                    result.add(word);
                } else {
                    for(String sub : backtrack(next, wordDict, mem)) {
                        result.add(word + " " + sub);
                    }
                }
            }
        }
        mem.put(s, result);
        return result;
    }

    //TODO: Check out https://leetcode.com/problems/word-break-ii/discuss/44243/Java-DP%2BDFS-Memoization%2BDFS-and-DP-Pruning-Solutions-with-Analysis

    public static void main(String[] args) {
        System.out.println(wordBreak2("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(wordBreak2("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(wordBreak2("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
