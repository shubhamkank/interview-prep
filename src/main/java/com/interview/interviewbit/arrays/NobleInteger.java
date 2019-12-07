package com.interview.interviewbit.arrays;

import java.util.*;

public class NobleInteger {

    public static int solve(List<Integer> A) {
        int n = A.size();
        Collections.sort(A);

        for(int i = 0; i < n - 1; i++) {
            //Duplicate numbers, take only the rightmost
            if(A.get(i) == A.get(i + 1)) {
                continue;
            }

            int p = A.get(i);
            if(p == n - 1 - i) {
                return 1;
            }
        }
        //Check if last element is 0
        return A.get(n - 1) == 0 ? 1 : -1;
    }

    public static void main(String[] args) {
        System.out.println(solve(Arrays.asList(-4, -2, 0, -1, -6)));
    }
}
