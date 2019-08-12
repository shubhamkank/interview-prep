package com.interview.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinCostToHireKWorkers {

    //Time complexity: O(N^2logN), Space complexity: O(N)
    public static double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        int n = quality.length;
        double result = Double.MAX_VALUE;


        for(int captain = 0; captain < n; captain++) {
            // Must pay at least wage[captain] / quality[captain] per qual
            double factor = (double) wage[captain] / quality[captain];
            double[] prices = new double[n];
            int i = 0;
            for(int worker = 0; worker < n; worker++) {
                double price = factor * quality[worker];
                if(price < wage[worker]) {
                    continue;
                }
                prices[i] = price;
                i++;
            }

            if(i < K) {
                continue;
            }

            //Sort to find lowest K prices
            Arrays.sort(prices, 0, i);
            double cost = 0;
            for (int j = 0; j < K; j++) {
                cost += prices[j];
            }
            result = Math.min(result, cost);
        }
        return result;
    }


    //Time complexity: O(NlogK), Space complexity: O(N + K)
    //https://leetcode.com/problems/minimum-cost-to-hire-k-workers/discuss/141768/Detailed-explanation-O(NlogN)
    //https://leetcode.com/problems/minimum-cost-to-hire-k-workers/solution/
    public double mincostToHireWorkers2(int[] quality, int[] wage, int K) {
        double[][] workers = new double[quality.length][2];
        for (int i = 0; i < quality.length; i++) {
            workers[i][0] = (double) wage[i] / quality[i];
            workers[i][1] = (double) quality[i];
        }

        Arrays.sort(workers, Comparator.comparingDouble(a -> a[0]));
        PriorityQueue<Double> pq = new PriorityQueue<>((a, b) -> (int) (b - a));

        double result = Double.MAX_VALUE;
        double qualitySum = 0;

        for(double[] worker : workers) {
            qualitySum += worker[1];
            pq.add(worker[1]);

            if(pq.size() > K) {
                qualitySum -= pq.poll();
            }

            if(pq.size() == K) {
                result = Math.min(result, worker[0] * qualitySum);
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
