package com.interview.leetcode;

public class LicenseKeyFormatting {

    //Time complexity: O(n)
    public static String licenseKeyFormatting(String S, int K) {
        StringBuilder sb = new StringBuilder();
        int count = 1;

        for(int i = S.length() - 1; i >= 0; i--) {
            if(S.charAt(i) != '-') {
                if(count % (K + 1) == 0) {
                    sb.append("-");
                    count++;
                }
                sb.append(Character.toUpperCase(S.charAt(i)));
                count++;
            }
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(licenseKeyFormatting("5F3Z-2e-9-w", 4));
        System.out.println(licenseKeyFormatting("2-5g-3-J", 2));
        System.out.println(licenseKeyFormatting("--a-a-a-a--", 2));
    }
}
