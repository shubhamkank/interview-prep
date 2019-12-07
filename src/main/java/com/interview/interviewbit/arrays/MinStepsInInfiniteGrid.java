package com.interview.interviewbit.arrays;

import java.util.*;

public class MinStepsInInfiniteGrid {

    public int coverPoints(ArrayList<Integer> A, ArrayList<Integer> B) {
        int result = 0;
        for(int i = 0; i < A.size() - 1; i++) {
            int x1 = A.get(i);
            int y1 = B.get(i);
            int x2 = A.get(i + 1);
            int y2 = B.get(i + 1);

            result += Math.max(Math.abs(x2 - x1), Math.abs(y2 - y1));
        }
        return result;
    }
}
