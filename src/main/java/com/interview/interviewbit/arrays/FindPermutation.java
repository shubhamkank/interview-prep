package com.interview.interviewbit.arrays;

import java.util.ArrayList;

public class FindPermutation {

    public ArrayList<Integer> findPerm(final String A, int B) {
        int min = 1, max = B;
        ArrayList<Integer> result = new ArrayList<>();

        for(char c : A.toCharArray()) {
            if(c == 'I') {
                result.add(min);
                min++;
            } else {
                result.add(max);
                max--;
            }
        }
        result.add(min);
        return result;
    }

    //Follow-up: Find lowest such permutation
    //https://leetcode.com/problems/find-permutation/discuss/96663/Greedy-O(n)-JAVA-solution-with-explanation
    //https://leetcode.com/problems/find-permutation/solution/
}
