package com.interview.leetcode;

import java.util.Stack;

public class LongestValidParenthesis {

    /* Brute Force Approach
       Time complexity: O(n^3), Space complexity: O(n)
       For every even length substring check if it is valid or not and calculate the max length. */
    public static int longestValidParentheses(String s) {
        int maxLen = 0;

        for(int i = 0; i < s.length(); i++) {
            for(int j = i + 2; j <= s.length(); j += 2) {
                if(isValid(s.substring(i, j))) {
                    maxLen = Math.max(maxLen, j - i);
                }
            }
        }
        return maxLen;
    }

    private static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push('(');
            } else if(!stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            } else {
                return false;
            }
        }
        return stack.isEmpty();
    }

    /* Dynamic Programming Approach
       Time complexity: O(n), Space complexity: O(n)
       dp[i] => length of longest valid substring ending index i (included)
       if str[i] == '(' => dp[i] = 0
       if str[i] == ')' =>
       1) if str[i] == '(' => dp[i] = dp[i-2] + 2
       2) if str[i] == ')' => if str[i - dp[i-1] - 1] == '(' => dp[i] = dp[i-1] + dp[i - dp[i-1] - 2] + 2   ....(substr)
     */
    public static int longestValidParentheses2(String s) {
        int maxLen = 0;
        int[] dp = new int[s.length()];

        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    /* Stack Approach
       Time complexity: O(n), Space complexity: O(n)
    */
    public static int longestValidParentheses3(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }

        return maxLen;
    }

    /* Stack Approach
       Time complexity: O(n), Space complexity: O(n) */
    public static int longestValidParentheses4(String s) {
        int maxLen = 0;
        Stack<Integer> stack = new Stack<>();
        int mark = -1;

        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            } else {
                if(stack.isEmpty()) {
                    mark = i;
                } else {
                    stack.pop();
                    int len = i - (stack.isEmpty() ? mark : stack.peek());
                    maxLen = Math.max(maxLen, len);
                }
            }
        }

        return maxLen;
    }

    /* Left '(' and Right ')' counters approach
       Time complexity: O(n), Space complexity: O(1)
       When scanning from left to right, you might end up with a positive value for 'left'. Say, your left ends up with
       4 and right ends up with 3. This means there could be 3 valid pairs giving an answer of 6. But you never got the
       chance to calculate that because your "left" has always managed to stay more than "right". Example: "(((()))" */
    public static int longestValidParentheses5(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right >= left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left >= right) {
                left = right = 0;
            }
        }
        return maxlength;
    }

    public static void main(String[] args) {
        System.out.println(longestValidParentheses5("(()"));
        System.out.println(longestValidParentheses5(")()())"));
        System.out.println(longestValidParentheses5("(())"));
        System.out.println(longestValidParentheses5("(("));
        System.out.println(longestValidParentheses5("))(()())))"));
    }
}
