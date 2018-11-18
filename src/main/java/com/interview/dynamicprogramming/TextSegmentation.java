package com.interview.dynamicprogramming;

import java.util.Map;
import java.util.HashMap;

public class TextSegmentation {

    static boolean canSplit(String word) {
        int n = word.length();
        boolean [] dp = new boolean[n + 1];
        dp[n] = true;

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(isWord(word.substring(i, j + 1)) && dp[j + 1]) {
                    dp[i] = true;
                }
            }
        }
        return dp[0];
    }

    static boolean isWord(String word) {
        Map<String, Integer> dictionary = new HashMap<>();
        dictionary.put("mobile", 0);
        dictionary.put("samsung", 0);
        dictionary.put("sam", 0);
        dictionary.put("sung", 0);
        dictionary.put("man", 0);
        dictionary.put("mango", 0);
        dictionary.put("icecream", 0);
        dictionary.put("and", 0);
        dictionary.put("go", 0);
        dictionary.put("i", 0);
        dictionary.put("like", 0);
        dictionary.put("ice", 0);
        dictionary.put("cream", 0);

        return dictionary.containsKey(word);
    }

    public static void main(String[] args) {
        System.out.println(canSplit("ilikesamsung"));
        System.out.println(canSplit("iiiiiiii"));
        System.out.println(canSplit(""));
        System.out.println(canSplit("ilikelikeimangoiii"));
        System.out.println(canSplit("samsungandmango"));
        System.out.println(canSplit("samsungandmangok"));
    }
}
