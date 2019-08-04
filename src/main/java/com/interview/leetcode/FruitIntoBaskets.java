package com.interview.leetcode;

import java.util.HashMap;
import java.util.Map;

public class FruitIntoBaskets {

    //Sliding Window Approach
    //Time complexity: O(n), Space complexity: O(1)
    public static int totalFruit(int[] tree) {
        if(tree == null || tree.length == 0) {
            return 0;
        }

        int result = 1;
        int i = 0, j = 0;
        Map<Integer, Integer> map = new HashMap<>();

        while(j < tree.length) {
            map.put(tree[j], j);
            j++;

            if(map.size() >= 3) {
                int min = tree.length - 1;
                for(int value : map.values()) {
                    min = Math.min(min, value);
                }

                i = min + 1;
                map.remove(tree[min]);
            }

            result = Math.max(result, j - i);
        }
        return result;
    }

    //Without using hashmap
    //Time complexity: O(n), Space complexity: O(1)
    public static int totalFruit2(int[] tree) {
        int lastFruit = -1;
        int secondLastFruit = -1;
        int lastFruitCount = 0;
        int currMax = 0;
        int max = 0;

        for(int fruit : tree) {
            if(fruit == lastFruit || fruit == secondLastFruit) {
                currMax++;
            } else {
                currMax = lastFruitCount + 1;
            }

            if(fruit == lastFruit) {
                lastFruitCount++;
            } else {
                lastFruitCount = 1;
            }

            if(fruit != lastFruit) {
                secondLastFruit = lastFruit;
                lastFruit = fruit;
            }
            max = Math.max(max, currMax);
        }
        return max;
    }

    public static int totalFruit3(int[] tree) {
        Map<Integer, Integer> count = new HashMap<>();
        int res = 0, i = 0;
        for (int j = 0; j < tree.length; ++j) {
            count.put(tree[j], count.getOrDefault(tree[j], 0) + 1);
            while (count.size() > 2) {
                count.put(tree[i], count.get(tree[i]) - 1);
                if (count.get(tree[i]) == 0) count.remove(tree[i]);
                i++;
            }
            res = Math.max(res, j - i + 1);
        }
        return res;
    }

    public static int totalFruit4(int[] tree) {
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < tree.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int j = i; j < tree.length; j++) {
                if(map.size() < 2) {
                    if(!map.containsKey(tree[j])) {
                        map.put(tree[j], 1);
                    } else {
                        map.put(tree[j], map.get(tree[j]) + 1);
                    }
                } else if(map.size() == 2) {
                    if(!map.containsKey(tree[j])) {
                        break;
                    } else {
                        map.put(tree[j], map.get(tree[j]) + 1);
                    }
                }
            }
            int count = map.values().stream().reduce(0, Integer::sum);
            result = Math.max(result, count);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[] {1,2,1}));
        System.out.println(totalFruit(new int[] {0,1,2,2}));

        System.out.println(totalFruit(new int[] {1,2,3,2,2}));
        System.out.println(totalFruit(new int[] {3,3,3,1,2,1,1,2,3,3,4}));
        System.out.println(totalFruit(new int[] {0,1,6,6,4,4,6}));
    }
}
