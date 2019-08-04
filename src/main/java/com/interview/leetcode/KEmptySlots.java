package com.interview.leetcode;

import java.util.TreeSet;

public class KEmptySlots {

    //TreeSet - SortedSet Approach
    //Time complexity: O(nlogn) - add, search - log(n) for tree set
    //Space complexity: O(n) - the size of active
    public static int kEmptySlots(int[] bulbs, int K) {
        TreeSet<Integer> active = new TreeSet<>();
        int day = 0;

        for(int bulb : bulbs) {
            day++;
            active.add(bulb);

            Integer lower = active.lower(bulb);
            Integer higher = active.higher(bulb);

            if((lower != null && bulb - lower - 1 == K) ||
                    (higher != null && higher - bulb - 1 == K)) {
                return day;
            }
        }
        return -1;
    }

    //Sliding window approach
    public static int kEmptySlots2(int[] bulbs, int K) {
        int[] days = new int[bulbs.length];
        for(int i = 0; i < days.length; i++) {
            // day[i] is the day when the bulb at position i+1 is lit
            days[bulbs[i] - 1] = i + 1;
        }

        int left = 0;
        int right = K + 1;
        int result = Integer.MAX_VALUE;

        for(int i = 1; right < days.length; i++) {
            if(days[i] < days[left] && days[i] < days[right]) {
                continue;
            }

            if(i == right) {
                result = Math.min(result, Math.max(days[left], days[right]));
            }

            left = i;
            right = i + K + 1;
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    public static void main(String[] args) {

    }
}
