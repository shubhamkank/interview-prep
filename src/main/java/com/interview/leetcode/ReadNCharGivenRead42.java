package com.interview.leetcode;

public class ReadNCharGivenRead42 {

    class Solution extends Reader4 {

        int bytesRead = 0;
        int totalBytes = 0;
        char[] buf4 = new char[4];

        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            int total = 0;

            while(total < n) {

                if(bytesRead == 0) {
                    totalBytes = read4(buf4);
                }

                //EOF reached
                if(totalBytes == 0) {
                    break;
                }

                //Equivalent to min(n - total, totalBytes - bytesRead)
                //Copy from temp buffer to buf array
                while(total < n && bytesRead < totalBytes) {
                    buf[total++] = buf4[bytesRead++];
                }

                //If read all the chars from temp buff, reset the temp buff
                if(bytesRead == totalBytes) {
                    bytesRead = 0;
                }
            }
            return total;
        }
    }

    class Solution2 extends Reader4 {

        int bytesRead = 0;
        int totalBytes = 0;
        char[] buf4 = new char[4];

        /**
         * @param buf Destination buffer
         * @param n   Number of characters to read
         * @return    The number of actual characters read
         */
        public int read(char[] buf, int n) {
            boolean eof = false;
            int total = 0;

            while(!eof) {

                if(totalBytes - bytesRead == 0) {
                    int count = read4(buf4);
                    if(count == 0) {
                        eof = true;
                        break;
                    }
                    totalBytes = count;
                    bytesRead = 0;
                }

                System.out.println("Total Bytes: " + totalBytes + " Bytes Read: " + bytesRead);
                System.out.println("Remaining: " + (n - total));

                int k = Math.min(totalBytes - bytesRead, n - total);
                if(k == 0) {
                    break;
                }

                System.out.println("Min: " + k);
                System.out.println("BytesRead + K: " + (bytesRead + k));

                int l = bytesRead + k;
                for(int i = bytesRead; i < l; i++) {
                    System.out.println("i: " + i);
                    buf[total++] = buf4[i];
                    bytesRead++;
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
