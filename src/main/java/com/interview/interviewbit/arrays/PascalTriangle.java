package com.interview.interviewbit.arrays;

import java.util.*;

public class PascalTriangle {

    public static ArrayList<ArrayList<Integer>> solve(int A) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(A < 1) {
            return result;
        }

        ArrayList<Integer> firstRow = new ArrayList<>();
        firstRow.add(1);
        result.add(firstRow);

        for(int i = 1; i < A; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for(int j = 0; j <= i; j++) {
                int x = j > 0 ? result.get(i - 1).get(j - 1) : 0;
                int y = j < i ? result.get(i - 1).get(j) : 0;
                row.add(x + y);
            }
            result.add(row);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solve(5));
    }
}
