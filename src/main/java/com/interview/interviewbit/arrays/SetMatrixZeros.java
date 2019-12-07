package com.interview.interviewbit.arrays;

import java.util.*;

public class SetMatrixZeros {

    //Time complexity: O(mn), Space complexity: O(m + n)
    public void setZeroes(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();

        boolean[] row = new boolean[m];
        boolean[] col = new boolean[n];

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(a.get(i).get(j) == 0) {
                    row[i] = true;
                    col[j] = true;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(row[i]) {
                    a.get(i).set(j, 0);
                }
                if(col[j]) {
                    a.get(i).set(j, 0);
                }
            }
        }
    }

    //https://leetcode.com/problems/set-matrix-zeroes/solution/
    //Time complexity: O(mn), Space complexity: O(1)
    public void setZeroes2(ArrayList<ArrayList<Integer>> a) {
        int m = a.size();
        int n = a.get(0).size();

        boolean isZeroCol = false;
        boolean isZeroRow = false;

        //check the first column
        for(int i = 0; i < m; i++) {
            if(a.get(i).get(0) == 0) {
                isZeroCol = true;
                break;
            }
        }

        //check the first row
        for(int i = 0; i < n; i++) {
            if(a.get(0).get(i) == 0) {
                isZeroRow = true;
                break;
            }
        }

        //check except the first row and column
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(a.get(i).get(j) == 0) {
                    a.get(i).set(0, 0);
                    a.get(0).set(j, 0);
                }
            }
        }

        //process except the first row and column
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(a.get(i).get(0) == 0 || a.get(0).get(j) == 0) {
                    a.get(i).set(j, 0);
                }
            }
        }

        //handle the first column
        if(isZeroCol) {
            for(int i = 0; i < m; i++) {
                a.get(i).set(0, 0);
            }
        }

        //handle the first row
        if(isZeroRow) {
            for(int i = 0; i < n; i++) {
                a.get(0).set(i, 0);
            }
        }
    }
}
