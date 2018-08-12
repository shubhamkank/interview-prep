package com.interview.leetcode;

public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else if (strs.length == 1) {
            return strs[0];
        }

        StringBuilder prefix = new StringBuilder("");
        String firstString = strs[0];

        for (int i = 0; i < firstString.length(); i++) {
            boolean flag = false;
            for (int j = 1; j < strs.length; j++) {
                if (i < strs[j].length() && firstString.charAt(i) == strs[j].charAt(i)) {
                    flag = true;
                } else {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                prefix.append(firstString.charAt(i));
            } else {
                break;
            }
        }

        return prefix.toString();
    }

    /**
     * Horizontal Scanning: LCP(S​1​​…S​n​​)=LCP(LCP(LCP(S​1​​,S​2​​),S​3​​),…S​n​​)
     * Time complexity: O(S) - S is the sum of all characters in all strings
     * Space complexity: O(1)
     * Performs badly when shorter string is at the end
     */
    public static String longestCommonPrefix2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        for(int i = 1; i < strs.length; i++) {
            while(strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if(prefix.isEmpty()) {
                    return "";
                }
            }
        }
        return prefix;
    }

    /**
     * Vertical Scanning
     * Time complexity: O(S) - S is the sum of all characters in all strings
     * Space complexity: O(1)
     * Worst case - equal strings with equal length m*n
     * Best case - n * minLen
     */
    public static String longestCommonPrefix3(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        for(int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);

            for(int j = 1; j < strs.length; j++) {
                if(i == strs[j].length() || strs[j].charAt(i) != c)
                    return strs[0].substring(0, i);
            }
        }

        return strs[0];
    }

    /**
     * Divide and conquer - LCP(S​1​​…S​n​​)=LCP(LCP(S​1​​…S​k​​),LCP(S​k+1​​…S​n​​))
     * Time complexity: O(S) - S is the sum of all characters in all strings
     * Space complexity: O(1)
     * Worst case - equal strings with equal length m*n
     * Best case - n * minLen
     */
    public static String longestCommonPrefix4(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0, strs.length - 1);
    }

    private static String longestCommonPrefix(String [] strs, int l, int r) {
        if(l == r) {
            return strs[l];
        } else {
            int mid = (l + r)/2;
            String lcpLeft = longestCommonPrefix(strs, l, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, r);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    private static String commonPrefix(String left, String right) {
        int min = Math.min(left.length(), right.length());
        for(int i = 0; i < min; i++) {
            if(left.charAt(i) != right.charAt(i)) {
                return left.substring(0, i);
            }
        }
        return left.substring(0, min);
    }

    public static String longestCommonPrefix5(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        int minLen = Integer.MAX_VALUE;
        for(String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        int low = 1;
        int high = minLen;

        while(low <= high) {
            int middle = (low + high)/2;
            if(isCommonPrefix(strs, middle)) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }
        return strs[0].substring(0, (low + high)/2);
    }

    private static boolean isCommonPrefix(String[] strs, int len) {
        String str1 = strs[0].substring(0, len);
        for(int i = 1; i < strs.length; i++) {
            if(!strs[i].startsWith(str1)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String [] args) {
        System.out.println(longestCommonPrefix5(new String[] {"flower","flow","fish"}));
        System.out.println(longestCommonPrefix5(new String[] {"dog","racecar","car"}));
        System.out.println(longestCommonPrefix5(new String[] {"dog"}));
    }
}
