package com.interview.interviewbit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpiralOrderMatrix {

    public static ArrayList<Integer> spiralOrder(final List<ArrayList<Integer>> A) {
        int m = A.size();
        int n = A.get(0).size();

        ArrayList<Integer> result = new ArrayList<>();

        int top = 0, bottom = m - 1, left = 0, right = n - 1;
        int direction = 0;

        while(top <= bottom && left <= right) {
            if(direction == 0) {
                for(int i = left; i <= right; i++) {
                    result.add(A.get(top).get(i));
                }
                top++;
            } else if(direction == 1) {
                for(int i = top; i <= bottom; i++) {
                    result.add(A.get(i).get(right));
                }
                right--;
            } else if(direction == 2) {
                for(int i = right; i >= left; i--) {
                    result.add(A.get(bottom).get(i));
                }
                bottom--;
            } else if(direction == 3) {
                for(int i = bottom; i >= top; i--) {
                    result.add(A.get(i).get(left));
                }
                left++;
            }
            direction = (direction + 1) % 4;
        }

        return result;
    }

    public static void main(String[] args) {
        List<ArrayList<Integer>> A = new ArrayList<>();
        ArrayList<Integer> a1 = new ArrayList<>(Arrays.asList(1, 2));
        ArrayList<Integer> a2 = new ArrayList<>(Arrays.asList(3, 4));
        ArrayList<Integer> a3 = new ArrayList<>(Arrays.asList(5, 6));
        A.add(a1);
        A.add(a2);
        A.add(a3);
        System.out.println(spiralOrder(A));
    }
}
