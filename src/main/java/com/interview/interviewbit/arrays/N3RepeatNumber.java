package com.interview.interviewbit.arrays;

import java.util.*;

//https://leetcode.com/problems/majority-element-ii/discuss/63537/My-understanding-of-Boyer-Moore-Majority-Vote
//https://leetcode.com/problems/majority-element-ii/discuss/63520/Boyer-Moore-Majority-Vote-algorithm-and-my-elaboration
//
public class N3RepeatNumber {

    public int repeatedNumber(final List<Integer> a) {
        int count1 = 0, count2 = 0;
        int candidate1 = 0, candidate2 = 0;

        for(int num : a) {
            if(candidate1 == num) {
                count1 += 1;
            } else if(candidate2 == num) {
                count2 += 1;
            } else if(count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } else if(count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } else {
                count1 -= 1;
                count2 -= 1;
            }
        }

        count1 = 0;
        count2 = 0;
        for(int num : a) {
            if(num == candidate1) {
                count1++;
            } else if(num == candidate2) {
                count2++;
            }
        }

        if(count1 > a.size() / 3) {
            return candidate1;
        } else if(count2 > a.size() / 3) {
            return candidate2;
        }
        return -1;
    }
}
