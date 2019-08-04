package com.interview.leetcode;

import java.util.HashMap;
import java.util.Stack;

public class LongestAbsoluteFilePath {

    //Time complexity: O(n) since each for the dir or subdir only enter and exit the stack once
    //Space complexity: O(n) because it generates new strings on the heap by calling split() and stack can have n lengths
    //if it is completely nested
    public static int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        //dummy len
        stack.push(0);
        int maxLen = 0;

        for(String s : input.split("\n")) {
            int level = s.lastIndexOf('\t') + 1;
            //If stack already contains length for higher levels which means we are moving back and therefore we should
            //remove the previous calculated lengths of higher levels
            while(stack.size() > level + 1) {
                stack.pop();
            }
            // - level is for removing tabs and +1 is for adding / in the absolute path
            int len = stack.peek() + s.length() - level + 1;
            stack.push(len);

            //If it is a file, calculate max len till now
            if(s.contains(".")) {
                maxLen = Math.max(maxLen, len - 1);
            }
        }
        return maxLen;
    }

    //Time complexity: O(n)
    public static int lengthLongestPath2(String input) {
        String[] paths = input.split("\n");
        int[] stack = new int[paths.length + 1];

        int maxLen = 0;
        for (String s : paths) {
            int lev = s.lastIndexOf("\t") + 1;
            stack[lev + 1] = stack[lev] + s.length() - lev + 1;
            if (s.contains(".")) {
                maxLen = Math.max(maxLen, stack[lev + 1] - 1);
            }
        }

        return maxLen;
    }

    //Using HashMap
    public static int lengthLongestPath3(String input) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int maxLen = 0;

        for(String s : input.split("\n")) {
            int level = s.lastIndexOf("\t") + 1;
            if(s.contains(".")) {
                maxLen = Math.max(maxLen, map.get(level) + s.length() - level);
            } else {
                map.put(level + 1, map.get(level) + s.length() - level + 1);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }
}
