package com.interview.leetcode;

import java.util.*;

//https://www.youtube.com/watch?v=GSBLe8cKu0s&feature=emb_logo
public class Skyline {

    //https://leetcode.com/problems/the-skyline-problem/discuss/61193/Short-Java-solution
    //Time complexity: O(n^2) as remove(Object) takes O(n) in java PQ implementation
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();

        List<int[]> height = new ArrayList<>();
        for(int[] b : buildings) {
            //Handle all corner cases via this -ve height for start edge of rectangle
            //a. if both of them are start/left coordinates. If so, consider the largest height. (That's why left coordinate heights are marked negative).
            //b. if both of them are end/right coordinates. If so, consider the shortest height.
            //c. If one of them is end/right and other is start/left then consider the start/left height.
            height.add(new int[]{b[0], -b[2]});
            height.add(new int[]{b[1], b[2]});
        }

        Collections.sort(height, (a, b) -> {
            if(a[0] != b[0])
                return a[0] - b[0];
            return a[1] - b[1];
        });

        Queue<Integer> pq = new PriorityQueue<>((a, b) -> (b - a));
        pq.add(0);
        int prev = 0;

        for(int[] h:height) {
            if(h[1] < 0) {
                pq.offer(-h[1]);
            } else {
                pq.remove(h[1]);
            }
            int cur = pq.peek();
            if(prev != cur) {
                result.add(Arrays.asList(h[0], cur));
                prev = cur;
            }
        }
        return result;
    }

    //Time complexity: O(nlogn) remove operation takes O(logn) in tree map (red-black tree implementation - self balancing binary search tree)
    //search, get, put and remove take logarithmic time O(log n) in TreeMap
    public static List<int[]> getSkyline2(int[][] buildings) {
        List<int[]> heights = new ArrayList<>();
        for (int[] b: buildings) {
            heights.add(new int[]{b[0], - b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0,1);
        int prevHeight = 0;
        List<int[]> skyLine = new LinkedList<>();
        for (int[] h: heights) {
            if (h[1] < 0) {
                Integer cnt = heightMap.get(-h[1]);
                cnt = ( cnt == null ) ? 1 : cnt + 1;
                heightMap.put(-h[1], cnt);
            } else {
                Integer cnt = heightMap.get(h[1]);
                //TreeMap cannot have duplicates unlike PQ, therefore need to compare the count of each key
                if (cnt == 1) {
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], cnt - 1);
                }
            }
            int currHeight = heightMap.firstKey();
            if (prevHeight != currHeight) {
                skyLine.add(new int[]{h[0], currHeight});
                prevHeight = currHeight;
            }
        }
        return skyLine;
    }

    //Divide and conquer (most intuitive solution)
    //Time complexity: O(nlogn)
    //https://www.geeksforgeeks.org/the-skyline-problem-using-divide-and-conquer-algorithm/
    public static List<int[]> getSkyline3(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private static LinkedList<int[]> recurSkyline(int[][] buildings, int p, int q) {
        if (p < q) {
            int mid = p + (q - p) / 2;
            return merge(recurSkyline(buildings, p, mid),
                    recurSkyline(buildings, mid + 1, q));
        } else {
            LinkedList<int[]> rs = new LinkedList<>();
            rs.add(new int[] { buildings[p][0], buildings[p][2] });
            rs.add(new int[] { buildings[p][1], 0 });
            return rs;
        }
    }

    private static LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> rs = new LinkedList<>();
        int h1 = 0, h2 = 0;
        while (l1.size() > 0 && l2.size() > 0) {
            int x = 0, h = 0;
            if (l1.getFirst()[0] < l2.getFirst()[0]) {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
            } else if (l1.getFirst()[0] > l2.getFirst()[0]) {
                x = l2.getFirst()[0];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l2.removeFirst();
            } else {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h2 = l2.getFirst()[1];
                h = Math.max(h1, h2);
                l1.removeFirst();
                l2.removeFirst();
            }
            //If prev height is the same ignore adding it to result
            if (rs.size() == 0 || h != rs.getLast()[1]) {
                rs.add(new int[] { x, h });
            }
        }
        //Add remaining skylines as it is
        rs.addAll(l1);
        rs.addAll(l2);
        return rs;
    }
}
