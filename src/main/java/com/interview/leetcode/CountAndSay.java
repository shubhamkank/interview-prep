package com.interview.leetcode;

public class CountAndSay {

    public static String countAndSay(int n) {
        if(n < 1) {
            return null;
        }
        String str = "1";
        for(int i = 1; i < n; i++) {
            str = getCountString(str);
        }
        return str;
    }

    private static String getCountString(String str) {
        StringBuilder sb = new StringBuilder();
        char candidate = str.charAt(0);
        int count = 1;

        for(int i = 1; i < str.length(); i++) {
            if(str.charAt(i) == candidate) {
                count++;
            } else {
                sb.append(count);
                sb.append(candidate);
                candidate = str.charAt(i);
                count = 1;
            }
        }

        sb.append(count);
        sb.append(candidate);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(1));
        System.out.println(countAndSay(4));
        System.out.println(countAndSay(5));
        System.out.println(countAndSay(10));
    }
}
