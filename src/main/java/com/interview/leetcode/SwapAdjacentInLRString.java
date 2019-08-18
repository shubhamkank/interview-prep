package com.interview.leetcode;

public class SwapAdjacentInLRString {

    // Time complexity: O(n), Space complexity: O(1)
    // Invariants: 1) L and R cannot cross each other - relative position of L & R in start and end string would remain same
    // 2) If i, i' are positions of character 'L' in start and end respectively then i >= i'
    // If j, j' are positions of character 'R' in start and end respectively then j <= j'
    public static boolean canTransform(String start, String end) {
        if(start.length() != end.length()) {
            return false;
        }
        int i = 0, j = 0;
        int n = start.length();

        while(i < n && j < n) {
            while(i < n && start.charAt(i) == 'X') {
                i++;
            }
            while(j < n && end.charAt(j) == 'X') {
                j++;
            }

            if((i < n) ^ (j < n)) {
                return false;
            }

            if(i < n && j < n) {
                if(start.charAt(i) != end.charAt(j) ||
                        (start.charAt(i) == 'L' && i < j) ||
                        (start.charAt(i) == 'R' && i > j)) {
                    return false;
                }
            }
            i++;
            j++;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(canTransform("RXXLRXRXL", "XRLXXRRLX"));
    }
}
