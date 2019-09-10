package com.interview.leetcode;

import java.util.Arrays;

public class KokoEatingBananas {

    //Time complexity: O(n * log max)
    public static int minEatingSpeed(int[] piles, int H) {
        int l = 1;
        int r = Arrays.stream(piles).max().getAsInt();

        while(l < r) {
            int mid = (l + r) / 2;
            if(canEatAll(piles, H, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    //Time complexity: O(n * log (max - ceil(B/H))) where B is total number of bananas
    public static int minEatingSpeed2(int[] piles, int H) {
        int max = 0, sum = 0;
        for (int i = 0; i < piles.length; i++) {
            sum += piles[i];
            max = Math.max(max, piles[i]);
        }

        //Optimization on lower bound: H * K >= B therefore, K >= B / H where B is total number of bananas
        int l = (int) Math.ceil((double) sum / H);
        int r = max;

        while(l < r) {
            int mid = (l + r) / 2;
            if(canEatAll(piles, H, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private static boolean canEatAll(int[] piles, int H, int K) {
        int countHour = 0;

        for (int i = 0; i < piles.length; i++) {
            countHour += Math.ceil((double)piles[i] / K);
        }
        return countHour <= H;
    }

    public static void main(String[] args) {
        System.out.println(minEatingSpeed2(new int[] {3,6,7,11}, 8));
        System.out.println(minEatingSpeed2(new int[] {30,11,23,4,20}, 5));
        System.out.println(minEatingSpeed2(new int[] {30,11,23,4,20}, 6));
    }
}
