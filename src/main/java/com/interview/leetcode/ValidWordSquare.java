package com.interview.leetcode;

import java.util.*;

public class ValidWordSquare {

    public boolean validWordSquare(List<String> words) {
        if(words == null || words.size() == 0) {
            return true;
        }

        int n = words.size();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < words.get(i).length(); j++) {
                //horizontal word too long || too short || chars don't match
                if(j >= n|| i >= words.get(j).length() || words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
