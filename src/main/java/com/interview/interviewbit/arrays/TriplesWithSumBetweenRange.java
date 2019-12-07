package com.interview.interviewbit.arrays;

import java.util.*;

//https://www.interviewbit.com/problems/triplets-with-sum-between-given-range/
//https://www.quora.com/Programming-Interviews/Given-n-positive-real-numbers-find-whether-there-exists-a-triplet-among-this-set-such-that-the-sum-of-the-triplet-is-in-the-range-1-2-Do-it-in-linear-time-and-constant-space
public class TriplesWithSumBetweenRange {

    public int solve(ArrayList<String> A) {
        float max1A, max2A, max3A, min1A, min2A, min1B, min2B, min1C;
        max1A = max2A = max3A = Integer.MIN_VALUE;
        min1A = min2A = min1B = min2B = min1C = Integer.MAX_VALUE;

        for(int i = 0; i < A.size(); i++) {
            float val = Float.valueOf(A.get(i));
            if(val <= 0.666) {
                if(val > max1A) {
                    max3A = max2A;
                    max2A = max1A;
                    max1A = val;
                } else if(val > max2A) {
                    max3A = max2A;
                    max2A = val;
                } else if(val > max3A) {
                    max3A = val;
                }
                if(val < min1A) {
                    min2A = min1A;
                    min1A = val;
                } else if(val < min2A) {
                    min2A = val;
                }
            } else if(val <= 1) {
                if(val < min1B) {
                    min2B = min1B;
                    min1B = val;
                } else if(val < min2B) {
                    min2B = val;
                }
            } else if(val < 2) {
                if(val < min1C) {
                    min1C = val;
                }
            }
        }

        float sumAAA = max1A + max2A + max3A;
        float sumAAB = max1A + max2A + min1B;
        float sumAAC = min1A + min2A + min1C;
        float sumABB = min1A + min1B + min2B;
        float sumABC = min1A + min1B + min1C;

        if((sumAAA > 1.0 && sumAAA < 2.0) || (sumAAB > 1.0 && sumAAB < 2.0) ||
                (sumAAC > 1.0 && sumAAC < 2.0) || (sumABB > 1.0 && sumABB < 2.0) ||
                (sumABC > 1.0 && sumABC < 2.0)) {
            return 1;
        }
        return 0;
    }

    public int solve2(ArrayList<String> A) {
        //take variables a,b,c and assign it with first 3 numbers
        double a = Double.parseDouble(A.get(0));
        double b = Double.parseDouble(A.get(1));
        double c = Double.parseDouble(A.get(2));
        // excute the loop from index 3 onwards
        for(int i = 3; i < A.size(); i ++){
            // check if sum fall in (1, 2)
            if((a+b+c) > 1 && (a+b+c) < 2){
                return 1;
            }
            // if not, then check is sum greater then 2
            // if so, then replace MAX(a,b,c) to new number
            else if((a+b+c) >= 2){
                if(a > b && a > c){
                    a = Double.parseDouble(A.get(i));
                }
                else if( b > c){
                    b = Double.parseDouble(A.get(i));
                }
                else{
                    c = Double.parseDouble(A.get(i));
                }
            }
            // else then sum must be less than 1
            // then replece MIN(a,b,c) to new number
            else{
                if(a < b && a < c){
                    a = Double.parseDouble(A.get(i));
                }
                else if( b < c){
                    b = Double.parseDouble(A.get(i));
                }
                else{
                    c = Double.parseDouble(A.get(i));
                }
            }
        }
        // check for last a, b, c  pair
        if((a+b+c) > 1 && (a+b+c) < 2){
            return 1;
        }
        else{
            return 0;
        }
    }
}
