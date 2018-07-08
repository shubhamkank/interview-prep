package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingChars {

    //Time: O(n) Space: O(min(m, n)) m -> size of charset
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        int maxLength = 0;

        for(int j = 0, i = 0; j < s.length(); j++) {
            if(charMap.containsKey(s.charAt(j))) {
                i = Math.max(charMap.get(s.charAt(j)), i);
            }

            maxLength = Math.max(maxLength, j-i+1);
            charMap.put(s.charAt(j), j+1);
        }
        return maxLength;
    }

    public static void main(String [] args) {
        System.out.println(lengthOfLongestSubstring("c"));
    }
}
