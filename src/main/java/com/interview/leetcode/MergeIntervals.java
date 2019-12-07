package com.interview.leetcode;

import java.util.*;

public class MergeIntervals {

    //Time complexity: O(nlogn), Space complexity: O(1)
    public int[][] merge(int[][] intervals) {
        if(intervals.length <= 1) {
            return intervals;
        }

        List<int[]> result = new ArrayList<>();

        // Sort by ascending starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        int[] newInterval = intervals[0];
        result.add(newInterval);

        for(int[] interval : intervals) {
            // Overlapping intervals, move the end if needed
            if(interval[0] <= newInterval[1]) {
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                // Disjoint intervals, add the new interval to the list
                newInterval = interval;
                result.add(newInterval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {
    }
}
