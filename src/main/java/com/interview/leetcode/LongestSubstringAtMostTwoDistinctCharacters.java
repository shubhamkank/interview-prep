package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringAtMostTwoDistinctCharacters {

    //Sliding window using hashmap
    //Time complexity: O(n), Space complexity: O(1)
    public static int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s == null || s.length() == 0) {
            return 0;
        }
        int i = 0, j = 0;
        int result = 1;
        Map<Character, Integer> map = new HashMap<>();

        while(j < s.length()) {
            map.put(s.charAt(j), j);
            j++;

            if(map.size() > 2) {
                int min = s.length() - 1;
                for(int value : map.values()) {
                    min = Math.min(min, value);
                }
                map.remove(s.charAt(min));
                i = min + 1;
            }

            result = Math.max(result, j - i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(lengthOfLongestSubstringTwoDistinct("ccaabbb"));
    }
}
