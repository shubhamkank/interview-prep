package com.interview.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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

    public static void main(String[] args) {
        System.out.println(wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog")));
        System.out.println(wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple")));
        System.out.println(wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
    }
}
