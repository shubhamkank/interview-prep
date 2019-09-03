package com.interview.leetcode;

public class ReadNCharGivenRead4 {

    /**
     * The read4 API is defined in the parent class Reader4.
     *     int read4(char[] buf);
     */
    class Solution extends Reader4 {
        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            int total = 0;
            boolean eof = false;

            while(!eof) {
                char[] buf4 = new char[4];
                int count = read4(buf4);

                if(count < 4) {
                    eof = true;
                }

                // corner case where buf = "abcdef", n = 5. The last iteration within the while loop gets count = 2,
                // while we only need 1 last character. This is why we need to compare "count" with "n - total"
                count = Math.min(count, n - total);
                for(int i = 0; i < count; i++) {
                    buf[total] = buf4[i];
                    total++;
                }
            }
            return total;
        }
    }

    abstract class Reader4 {
        int read4(char[] buf) {
            return 0;
        }
    }
}
