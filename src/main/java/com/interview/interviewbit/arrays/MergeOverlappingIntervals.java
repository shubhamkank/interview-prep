package com.interview.interviewbit.arrays;

import java.util.*;

public class MergeOverlappingIntervals {

    public ArrayList<Interval> merge(ArrayList<Interval> intervals) {
        ArrayList<Interval> result = new ArrayList<>();

        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        Interval curInterval = intervals.get(0);
        result.add(curInterval);

        for(int i = 1; i < intervals.size(); i++) {
            if(intervals.get(i).start <= curInterval.end) {
                curInterval.end = Math.max(curInterval.end, intervals.get(i).end);
            } else {
                curInterval = intervals.get(i);
                result.add(curInterval);
            }
        }
        return result;
    }
}
