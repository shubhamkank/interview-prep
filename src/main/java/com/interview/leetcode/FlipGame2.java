package com.interview.leetcode;

import java.util.*;

public class FlipGame2 {

    //For the time complexity, here is what I thought, let's say the length of the input string s is n,
    //there are at most n - 1 ways to replace "++" to "--" (imagine s is all "+++..."), once we replace one "++",
    //there are at most (n - 2) - 1 ways to do the replacement, it's a little bit like solving the N-Queens problem,
    //the time complexity is (n - 1) x (n - 3) x (n - 5) x ..., so it's O(n!!), double factorial
    public static boolean canWin(String s) {
        int i = s.indexOf("++");
        if(i < 0) {
            return false;
        }

        while(i >= 0) {
            String newS = s.substring(0, i) + "--" + s.substring(i + 2);
            if(!canWin(newS)) {
                return true;
            }
            i = s.indexOf("++", i + 1);
        }
        return false;
    }

    public static boolean canWin2(String s) {
        if(s == null || s.length() < 2) return false;
        Map<String, Boolean> map = new HashMap<>();
        return canWin2(s, map);
    }

    private static boolean canWin2(String s, Map<String, Boolean> map) {
        if(map.containsKey(s)) return map.get(s);
        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String opponent = s.substring(0, i) + "--" + s.substring(i + 2);
                if(!canWin2(opponent, map)) {
                    map.put(s, true);
                    return true;
                }
            }
        }
        map.put(s, false);
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canWin2("++++"));
        System.out.println(canWin2("+++++++++"));
    }
}
