package com.interview.interviewbit.arrays;

import java.util.*;

public class LargestNumber {

    //the length of input array is n,
    //average length of Strings in s_num is k,
    //Then, compare 2 strings will take O(k).
    //Sorting will take O(nklgn)
    //Appending to StringBuilder takes O(n).
    //So total will be O(nklgn) + O(n) = O(nklgn)
    //Space: O(n).
    public String largestNumber(final List<Integer> A) {
        String[] strNums = new String[A.size()];

        for(int i = 0; i < A.size(); i++) {
            strNums[i] = String.valueOf(A.get(i));
        }

        Arrays.sort(strNums, (a, b) -> {
            String r1 = a + b;
            String r2 = b + a;
            return r2.compareTo(r1);
        });


        StringBuilder sb = new StringBuilder();
        for(String x : strNums) {
            sb.append(x);
        }

        String result = sb.toString();
        return result.startsWith("0") ? "0" : result;
    }
}
