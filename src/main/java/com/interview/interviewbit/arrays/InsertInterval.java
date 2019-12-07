package com.interview.interviewbit.arrays;

import java.util.*;

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
 }

public class InsertInterval {

    public ArrayList<Interval> insert(ArrayList<Interval> intervals, Interval newInterval) {
        ArrayList<Interval> result = new ArrayList<>();

        if(intervals == null || intervals.size() == 0) {
            result.add(newInterval);
            return result;
        }

        if(newInterval.end < intervals.get(0).start ||
                newInterval.start > intervals.get(intervals.size() - 1).end) {
            if(newInterval.end < intervals.get(0).start) {
                result.add(newInterval);
            }

            for(int i = 0; i < intervals.size(); i++) {
                result.add(intervals.get(i));
            }

            if(newInterval.start > intervals.get(intervals.size() - 1).end) {
                result.add(newInterval);
            }
            return result;
        }


        if(newInterval.start <= intervals.get(0).start &&
                newInterval.end >= intervals.get(intervals.size() - 1).end) {
            result.add(newInterval);
            return result;
        }

        for(int i = 0; i < intervals.size(); i++) {
            boolean overlap = doesOverlap(intervals.get(i), newInterval);
            if(!overlap) {
                result.add(intervals.get(i));

                if(i < intervals.size() &&
                        newInterval.start > intervals.get(i).end &&
                        newInterval.end < intervals.get(i + 1).start) {
                    result.add(newInterval);
                }
                continue;
            }

            Interval temp = new Interval();
            temp.start = Math.min(newInterval.start, intervals.get(i).start);

            while(i < intervals.size() && overlap) {
                temp.end = Math.max(newInterval.end, intervals.get(i).end);

                if(i == intervals.size() - 1) {
                    overlap = false;
                } else {
                    overlap = doesOverlap(intervals.get(i + 1), newInterval);
                }
                i++;
            }

            i--;
            result.add(temp);
        }
        return result;
    }

    private boolean doesOverlap(Interval i1, Interval i2) {
        return Math.max(i1.start, i2.start) <= Math.min(i1.end, i2.end);
    }

    public ArrayList<Interval> insert2(ArrayList<Interval> intervals, Interval newInterval) {
        Interval current = newInterval;
        int i = 0;
        while(i < intervals.size()){
            Interval in = intervals.get(i);
            if(in.end < current.start){
                i++;
            }else if(in.start > current.end){
                intervals.add(i, current);
                break;
            }else{
                current.start = Math.min(in.start, current.start);
                current.end = Math.max(in.end, current.end);
                intervals.remove(i);
            }
        }
        if(i == intervals.size()){
            intervals.add(current);
        }
        return intervals;
    }
}
