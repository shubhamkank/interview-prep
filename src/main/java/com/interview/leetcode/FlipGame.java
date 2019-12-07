package com.interview.leetcode;

import java.util.*;

public class FlipGame {

    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();

        for(int i = 0; i < s.length() - 1; i++) {
            if(s.charAt(i) == '+' && s.charAt(i + 1) == '+') {
                String newS = s.substring(0, i) + "--" + s.substring(i + 2);
                result.add(newS);
            }
        }

        return result;
    }
}
