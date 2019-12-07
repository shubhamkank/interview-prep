package com.interview.leetcode;

public class UTF8Validation {

    //Time complexity: O(N), Space complexity: O(1)
    public static boolean validUtf8(int[] data) {
        int count = 0;

        for(int i = 0; i < data.length; i++) {
            String binary = toBinaryString(data[i]);

            if(count == 0) {
                while(count < 8 && binary.charAt(count) == '1') {
                    count++;
                }

                if(count == 0) {
                    continue;
                }

                if(count > 4 || count == 1) {
                    return false;
                }
            } else {
                if(!(binary.charAt(0) == '1' && binary.charAt(1) == '0')) {
                    return false;
                }
            }
            count--;
        }
        return count == 0;
    }

    private static String toBinaryString(int x) {
        StringBuilder sb = new StringBuilder();
        int len = 0;
        while(x > 0) {
            int remainder = x % 2;
            sb.insert(0, remainder);
            len++;
            x = x / 2;
        }

        while(len < 8) {
            sb.insert(0, "0");
            len++;
        }

        return sb.toString();
    }

    //Bit manipulation
    //Time complexity: O(N), Space complexity: O(1)
    public boolean validUtf82(int[] data) {
        //Number of bytes in the current utf8 character
        int count = 0;

        //Mask to check two most significant bits in a byte
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        for(int i = 0; i < data.length; i++) {

            //If starting a new utf8 character
            if(count == 0) {
                int mask = 1 << 7;
                while((data[i] & mask) != 0) {
                    count++;
                    mask = mask >> 1;
                }

                // 1 byte characters
                if(count == 0) {
                    continue;
                }

                //Invalid scenario according to utf8 definition
                if(count > 4 || count == 1) {
                    return false;
                }
            } else {
                //Existing byte in utf8 character should have '10' as the most significant bits
                if(!((data[i] & mask1) != 0 && (data[i] & mask2) == 0)) {
                    return false;
                }
            }
            count--;
        }
        //If do not have complete data for a utf8 character
        return count == 0;
    }

    public static void main(String[] args) {
        System.out.println(validUtf8(new int[] {197,130,1}));
        System.out.println(validUtf8(new int[] {255}));
    }
}
