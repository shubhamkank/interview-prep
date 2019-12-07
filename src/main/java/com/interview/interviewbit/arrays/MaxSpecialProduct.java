package com.interview.interviewbit.arrays;

import java.util.*;

//https://www.geeksforgeeks.org/maximum-product-of-indexes-of-next-greater-on-left-and-right/
public class MaxSpecialProduct {

    public int maxSpecialProduct(ArrayList<Integer> A) {
        int n = A.size();

        int[] rightIndex = new int[n];
        Stack<Integer> rs = new Stack<>();
        for(int i = 0; i < n; i++) {
            while(rs.size() != 0 && A.get(i) > A.get(rs.peek())) {
                int r = rs.peek();
                rs.pop();
                rightIndex[r] = i;
            }
            rs.push(i);
        }

        int[] leftIndex = new int[n];
        Stack<Integer> ls = new Stack<>();
        for(int i = n - 1; i >= 0; i--) {
            while(ls.size() != 0 && A.get(i) > A.get(ls.peek())) {
                int r = ls.peek();
                ls.pop();
                leftIndex[r] = i;
            }
            ls.push(i);
        }

        long result = 0;
        for(int i = 0; i < n; i++) {
            result = Math.max(result, 1L * leftIndex[i] * rightIndex[i]);
        }
        return (int)(result % 1000000007);
    }
}
