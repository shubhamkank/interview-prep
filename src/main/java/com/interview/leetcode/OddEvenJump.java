package com.interview.leetcode;

import java.util.Map;
import java.util.TreeMap;

public class OddEvenJump {

    //Dynamic Programming + Tree Map
    //Time complexity: O(nlogn), Space complexity: O(n)
    public static int oddEvenJumps(int[] A) {
        int n = A.length;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        boolean[] higher = new boolean[n];
        boolean[] lower = new boolean[n];

        higher[n - 1] = true;
        lower[n - 1] = true;

        map.put(A[n - 1], n - 1);

        int result = 1;

        for (int i = n - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> hi = map.ceilingEntry(A[i]);
            Map.Entry<Integer, Integer> lo = map.floorEntry(A[i]);

            if(hi != null) {
                higher[i] = lower[hi.getValue()];
            }

            if(lo != null) {
                lower[i] = higher[lo.getValue()];
            }

            if(higher[i]) {
                result++;
            }
            map.put(A[i], i);
        }
        return result;
    }

    //https://leetcode.com/problems/odd-even-jump/discuss/217981/JavaC%2B%2BPython-DP-idea-Using-TreeMap-or-Stack

    public static void main(String[] args) {
        System.out.println(oddEvenJumps(new int[] {10,13,12,14,15}));
        System.out.println(oddEvenJumps(new int[] {2,3,1,1,4}));
        System.out.println(oddEvenJumps(new int[] {5,1,3,4,2}));
    }
}
