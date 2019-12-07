package com.interview.leetcode;

// https://leetcode.com/problems/sentence-screen-fitting/discuss/90845/21ms-18-lines-Java-solution
// https://leetcode.com/problems/sentence-screen-fitting/discuss/90874/12ms-Java-solution-using-DP
// https://leetcode.com/problems/sentence-screen-fitting/discuss/90846/JAVA-optimized-solution-17ms
public class SentenceScreenFitting {

    //Time complexity: O(n * maxWordLen) where n = rows
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0;
        int len = s.length();

        for (int i = 0; i < rows; i++) {
            start += cols;
            if(s.charAt(start % len) == ' ') {
                start++;
            } else {
                while (start > 0 && s.charAt((start - 1) % len) != ' ') {
                    start--;
                }
            }
        }
        return start / len;
    }

    //Time complexity: O(n + m) where n = rows, m = length of sentence
    //Space complexity: O(n + m)
    public int wordsTyping2(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int start = 0;
        int len = s.length();

        int[] map = new int[len];
        for (int i = 1; i < len; i++) {
            map[i] = s.charAt(i) == ' ' ? 1 : map[i - 1] - 1;
        }

        for (int i = 0; i < rows; i++) {
            start += cols;
            start += map[start % len];
        }
        return start / len;
    }

    //Dynamic programming
    public int wordsTyping3(String[] sentence, int rows, int cols) {
        int n = sentence.length;
        int[] dp = new int[n]; //dp[i] = number of words in a row when the row starts at ith word

        for (int i = 0; i < n; i++) {
            int index = i;
            int length = 0, words = 0;

            while (length + sentence[index % n].length() <= cols) {
                length += sentence[index % n].length();
                length += 1; //for space
                index++;
                words++;
            }
            dp[i] = words;
        }

        int words = 0;
        for (int i = 0, j = 0; i < rows; i++) {
            words += dp[j];
            j = (j + dp[j]) % n;
        }
        return words / n;
    }

    public static void main(String[] args) {

    }
}
