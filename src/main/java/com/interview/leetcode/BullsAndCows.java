package com.interview.leetcode;

public class BullsAndCows {

    public static String getHint(String secret, String guess) {
        int[] secretDigitCount = new int[10];
        int[] guessDigitCount = new int[10];

        int countA = 0, countB = 0;

        for (int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) != guess.charAt(i)) {
                secretDigitCount[secret.charAt(i) - '0']++;
                guessDigitCount[guess.charAt(i) - '0']++;
            } else {
                countA++;
            }
        }

        for (int i = 0; i <= 9; i++) {
            countB += Math.min(secretDigitCount[i], guessDigitCount[i]);
        }
        return countA + "A" + countB + "B";
    }

    public static String getHint2(String secret, String guess) {
        int[] count = new int[10];

        int countA = 0, countB = 0;

        for (int i = 0; i < secret.length(); i++) {
            int a = secret.charAt(i) - '0';
            int b = guess.charAt(i) - '0';

            if(a != b) {

                //if prev part of guess has curr digit in secret
                //then we found a pair that has same digit with different position
                if(count[a] < 0) {
                    countB++;
                }

                //if prev part of secret has curr digit in guess
                //then we found a pair that has same digit with different position
                if(count[b] > 0) {
                    countB++;
                }

                //digit from secret will increase the counter
                count[a]++;
                //digit from guess will decrease the counter
                count[b]--;
            } else {
                //accurate match (same digit with same position)
                countA++;
            }
        }

        return countA + "A" + countB + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint2("1807", "7810"));
        System.out.println(getHint2("1123", "0111"));
    }
}
