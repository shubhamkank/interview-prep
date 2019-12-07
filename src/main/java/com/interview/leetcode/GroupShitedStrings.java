package com.interview.leetcode;

import java.util.*;

public class GroupShitedStrings {

    public static List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for(String s : strings) {
            String key = getKey(s);
            List<String> group = map.getOrDefault(key, new ArrayList<>());
            group.add(s);
            map.put(key, group);
        }
        return new ArrayList<>(map.values());
    }

    //Difference between adjacent chars will remain the same on shifting (adding 1 to each char).
    //Therefore that can used to form a key in the hashmap
    private static String getKey(String s) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < s.length() - 1; i++) {
            int diff = s.charAt(i + 1) - s.charAt(i);
            //Add 26 in order to handle negative cases az = 0#25# ba = 0#-1#
            sb.append((diff + 26) % 26);
            sb.append("#");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(groupStrings(new String[]{"abc","bcd","acef","xyz","az","ba","a","z"}));
    }
}
