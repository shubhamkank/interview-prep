package com.interview.leetcode;

import java.util.*;

public class MinimumWindowSubstring {


    //Sliding window approach
    //Time complexity: O(s + t), Space complexity: O(t)
    public String minWindow(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : t.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        int counter = map.size();
        int begin = 0, end = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;

        while (end < s.length()) {
            char c = s.charAt(end);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) - 1);
                if (map.get(c) == 0) {
                    counter--;
                }
            }

            end++;

            while (counter == 0) {
                char tempc = s.charAt(begin);
                if (map.containsKey(tempc)) {
                    map.put(tempc, map.get(tempc) + 1);
                    if (map.get(tempc) > 0) {
                        counter++;
                    }
                }

                if (end - begin < len) {
                    len = end - begin;
                    start = begin;
                }
                begin++;
            }
        }

        if (len == Integer.MAX_VALUE) {
            return "";
        }
        return s.substring(start, start + len);
    }

    public static void main(String[] args) {

    }
}
