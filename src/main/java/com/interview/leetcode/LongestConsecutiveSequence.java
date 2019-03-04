package com.interview.leetcode;

import java.util.Arrays;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

public class LongestConsecutiveSequence {

    /* Sorting Approach
       Time complexity: O(nlogn), Space complexity: O(1) - if modifying the array is allowed otherwise O(n) */
    public static int longestConsecutive(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int length = 1, maxLength = 1;
        for(int i = 0; i < nums.length - 1; i++) {
            if(nums[i] == nums[i + 1] - 1) {
                length++;
            } else if(nums[i] == nums[i + 1]) {
                continue;
            } else {
                maxLength = Math.max(maxLength, length);
                length = 1;
            }
        }
        maxLength = Math.max(maxLength, length);
        return maxLength;
    }

    /* This optimized algorithm contains only two changes from the brute force approach: the numbers are stored in a
       HashSet to allow O(1) lookups, and we only attempt to build sequences from numbers that are not already part of a
       longer sequence.
       Time complexity: O(n), Space complexity: O(n)
     */
    public static int longestConsecutive2(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        Set<Integer> numSet = new HashSet<>();
        for(int num : nums) {
            numSet.add(num);
        }

        int maxLen = 0;
        for(int num : nums) {
            //If not part of a already longer sequence
            if(!numSet.contains(num - 1)) {
                int currentNum = num;
                int currentLen = 1;

                while(numSet.contains(currentNum + 1)) {
                    currentNum++;
                    currentLen++;
                }
                maxLen = Math.max(maxLen, currentLen);
            }
        }
        return maxLen;
    }

    static class UnionFind {
        int[] parent;
        int[] size;
        int maxSize;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            maxSize = 1;

            for(int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /* Time complexity: O(alpha(n)) - alpha extremely slow moving function - for numbers in the universe < 5
           Therefore, almost constant time */
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /* Time complexity: O(alpha(n)) - Inverse Ackermann Function */
        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot == jRoot) {
                return;
            }

            int localMaxSize = 1;
            if(size[iRoot] < size[jRoot]) {
                parent[iRoot] = jRoot;
                size[jRoot] += size[iRoot];
                localMaxSize = size[jRoot];
            } else {
                parent[jRoot] = iRoot;
                size[iRoot] += size[jRoot];
                localMaxSize = size[iRoot];
            }
            maxSize = Math.max(maxSize, localMaxSize);
        }

        public int getMaxSize() {
            return maxSize;
        }
    }

    /* Union Find Data Structure
       Time complexity: O(n), Space complexity: O(n) */
    public static int longestConsecutive3(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        UnionFind uf = new UnionFind(nums.length);
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                continue;
            }
            map.put(nums[i], i);
            if(map.containsKey(nums[i] - 1)) {
                uf.union(i, map.get(nums[i] - 1));
            }
            if(map.containsKey(nums[i] + 1)) {
                uf.union(i, map.get(nums[i] + 1));
            }
        }
        return uf.getMaxSize();
    }

    public static void main(String[] args) {
        System.out.println(longestConsecutive3(new int[] {100, 4, 200, 1, 3, 2}));
        System.out.println(longestConsecutive3(new int[] {100, 300, 200, 500, 31, 2}));
        System.out.println(longestConsecutive3(new int[] {0, -1}));
        System.out.println(longestConsecutive3(new int[] {1, 2, 0, 1}));
    }
}
