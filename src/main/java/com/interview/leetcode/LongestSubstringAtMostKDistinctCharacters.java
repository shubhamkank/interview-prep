package com.interview.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LongestSubstringAtMostKDistinctCharacters {

    //Sliding window using hashmap
    //Time complexity: O(nk), Space complexity: O(k)
    //Best case: O(n) when there are <= k distinct characters in the string
    //Worst case: all n elements are distinct - O(k) everytime to find min value in the hasmap -> O(nk)
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        if(k == 0) {
            return 0;
        }

        int i = 0, j = 0;
        int result = 1;
        Map<Character, Integer> map = new HashMap<>();

        while(j < s.length()) {
            map.put(s.charAt(j), j);
            j++;

            if(map.size() > k) {
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

    //Sliding window using linked hashmap
    //Time complexity: O(n), Space complexity: O(k)
    public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
        if(s == null || s.length() == 0) {
            return 0;
        }

        if(k == 0) {
            return 0;
        }

        int i = 0, j = 0;
        int result = 1;
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();

        while(j < s.length()) {
            //if character is already in the hashmap - delete it, so that after insert it becomes
            //the rightmost element in the hashmap
            if(map.containsKey(s.charAt(j))) {
                map.remove(s.charAt(j));
            }
            map.put(s.charAt(j), j);
            j++;

            if(map.size() > k) {
                Map.Entry<Character, Integer> leftMost = map.entrySet().iterator().next();
                map.remove(leftMost.getKey());
                i = leftMost.getValue() + 1;
            }

            result = Math.max(result, j - i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct2("eceba", 2));
        System.out.println(lengthOfLongestSubstringKDistinct2("aa", 1));
    }
}
