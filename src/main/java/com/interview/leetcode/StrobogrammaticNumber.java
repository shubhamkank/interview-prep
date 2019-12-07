package com.interview.leetcode;

import java.util.*;

public class StrobogrammaticNumber {

    static Map<Character, Character> map = new HashMap<>();
    static {
        map.put('6', '9');
        map.put('8', '8');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
    }

    public boolean isStrobogrammatic(String num) {
        StringBuilder sb = new StringBuilder();
        for(char c : num.toCharArray()) {
            if(!map.containsKey(c)) {
                return false;
            }
            sb.append(map.get(c));
        }

        return num.equals(sb.reverse().toString());
    }

    public boolean isStrobogrammatic2(String num) {
        int i = 0;
        int j = num.length() - 1;
        while(i <= j) {
            if(!"00 11 88 69 96".contains(num.charAt(i) + "" + num.charAt(j))) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public boolean isStrobogrammatic3(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
   
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }

        return true;
    }
}
