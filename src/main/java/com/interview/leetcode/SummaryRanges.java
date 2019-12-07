package com.interview.leetcode;

import java.util.*;

public class SummaryRanges {

    //Time complexity: O(n), Space complexity: O(1)
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();

        for(int i = 0; i < nums.length; i++) {
            int rangeStart = nums[i];
            int rangeEnd = nums[i];

            while(i + 1 < nums.length && nums[i + 1] - nums[i] == 1) {
                rangeEnd = nums[i + 1];
                i++;
            }

            if(rangeStart != rangeEnd) {
                result.add(rangeStart + "->" + rangeEnd);
            } else {
                result.add(rangeStart + "");
            }
        }
        return result;
    }
}
