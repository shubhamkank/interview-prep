package com.interview.interviewbit.arrays;

import java.util.*;

public class Flips {

    public ArrayList<Integer> flip(String A) {
        int maxDiff = 0;
        int diff = 0;
        int start = 0;

        int l = -1, r = -1;

        for(int i = 0; i < A.length(); i++) {
            diff += A.charAt(i) == '0' ? 1 : -1;

            if(diff < 0) {
                diff = 0;
                start = i + 1;
                continue;
            }

            if(diff > maxDiff) {
                maxDiff = diff;
                l = start;
                r = i;
            }
        }

        if(l == -1 && r == -1) {
            return new ArrayList<>();
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(l + 1);
        result.add(r + 1);
        return result;
    }
}
