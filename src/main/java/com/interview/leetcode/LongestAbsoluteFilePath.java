package com.interview.leetcode;

import java.util.Stack;

public class LongestAbsoluteFilePath {

    //Time complexity: O(n)
    public static int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
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

    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
    }
}
