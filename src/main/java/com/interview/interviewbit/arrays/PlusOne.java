package com.interview.interviewbit.arrays;

import java.util.*;

public class PlusOne {

    public ArrayList<Integer> plusOne(ArrayList<Integer> A) {
        ArrayList<Integer> result = new ArrayList<>();

        int j = 0;
        while(j < A.size() - 1 && A.get(j) == 0) {
            A.remove(j);
        }

        int carry = 0;
        int i = A.size() - 1;
        while(i >= 0) {
            int val = i == A.size() - 1 ? (A.get(i) + 1) : (A.get(i) + carry);
            carry = val / 10;
            result.add(val % 10);
            i--;
            if(carry == 0) {
                break;
            }
        }

        while(i >= 0) {
            result.add(A.get(i));
            i--;
        }

        if(carry == 1) {
            result.add(1);
        }

        Collections.reverse(result);
        return result;
    }
}
