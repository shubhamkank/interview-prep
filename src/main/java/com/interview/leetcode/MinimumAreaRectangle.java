package com.interview.leetcode;

import java.util.*;

public class MinimumAreaRectangle {

    //Time complexity: O(n^2), Space complexity: O(n^2) - if all points have same x coordinate - map will store all pairs of y coordinates
    public static int minAreaRect(int[][] points) {
        //Group points by x coordinate in a sorted order using TreeMap
        Map<Integer, List<Integer>> rowsMap = new TreeMap<>();
        for(int[] point : points) {
            rowsMap.computeIfAbsent(point[0], x -> new ArrayList<>()).add(point[1]);
        }

        int minArea = Integer.MAX_VALUE;
        Map<Integer, Integer> map = new HashMap<>();

        //Consider every x as rightmost edge in the rectange and compute area
        for(int x : rowsMap.keySet()) {
            List<Integer> rows = rowsMap.get(x);
            Collections.sort(rows);
            for (int i = 0; i < rows.size(); i++) {
                for (int j = i + 1; j < rows.size(); j++) {
                    int y1 = rows.get(i);
                    int y2 = rows.get(j);

                    int hashcode = 40001 * y1 + y2;
                    if(map.containsKey(hashcode)) {
                        minArea = Math.min(minArea, (x - map.get(hashcode)) * (y2 - y1));
                    }
                    map.put(hashcode, x);
                }
            }
        }
        return minArea == Integer.MAX_VALUE ? 0 : minArea;
    }

    //Time complexity: O(n^2), Space complexity: O(n)
    public static int minAreaRect2(int[][] points) {
        Set<Integer> set = new HashSet<>();
        for(int[] point : points) {
            set.add(40001 * point[0] + point[1]);
        }

        int minArea = Integer.MAX_VALUE;

        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                //If the points are diagonals - (x1, y1), (x2, y2) then (x1, y2) and (x2, y1) must be present
                if(points[i][0] != points[j][0] && points[i][1] != points[j][1]) {
                    if(set.contains(40001 * points[i][0] + points[j][1]) && set.contains(40001 * points[j][0] + points[i][1])) {
                        minArea = Math.min(minArea, Math.abs(points[j][0] - points[i][0]) * Math.abs(points[j][1] - points[i][1]));
                    }
                }
            }
        }
        return minArea < Integer.MAX_VALUE ? minArea : 0;
    }

    public static void main(String[] args) {
        System.out.println(minAreaRect(new int[][] {{1,1},{1,3},{3,1},{3,3},{2,2}}));
        System.out.println(minAreaRect(new int[][] {{1,1},{1,3},{3,1},{3,3},{2,2},{4,1},{4,3}}));
    }
}
