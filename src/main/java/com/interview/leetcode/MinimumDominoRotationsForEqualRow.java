package com.interview.leetcode;

public class MinimumDominoRotationsForEqualRow {

    // Time complexity: O(n)
    public static int minDominoRotations(int[] A, int[] B) {
        // If one could make all elements in A or B equal to A[0]
        int rotations = minDominoRotations(A[0], A, B);

        if(rotations != -1) {
            return rotations;
        } else {
            // If one could make all elements in A or B equal to B[0]
            return minDominoRotations(B[0], A, B);
        }
    }

    /* Return min number of rotations if one could make all elements in A or B equal to x. Else return -1 */
    private static int minDominoRotations(int x, int[] A, int[] B) {
        int N = A.length;
        // how many rotations should be done
        // to have all elements in A equal to x
        // and to have all elements in B equal to x
        int rotationsA = 0, rotationsB = 0;

        for (int i = 0; i < N; i++) {
            if(A[i] != x && B[i] != x) {
                return -1;
            } else if(A[i] != x) {
                rotationsA++;
            } else if(B[i] != x) {
                rotationsB++;
            }
        }
        return Math.min(rotationsA, rotationsB);
    }

    // Time complexity: O(n)
    // 1. Count the frequency of each number in A and B, respectively;
    // 2. Count the frequency of A[i] if A[i] == B[i];
    // 3. If countA[i] + countB[i] - same[i] == A.length, we find a solution; otherwise, return -1;
    // 4. min(countA[i], countB[i]) - same[i] is the answer
    // countA[i] + countB[i] - same[i] is like finding the union of two set A and set B <=> A + B - (A & B)
    public static int minDominoRotations2(int[] A, int[] B) {
        if(A.length != B.length) {
            return -1;
        }

        int[] countA = new int[7];
        int[] countB = new int[7];
        int[] same = new int[7];

        for (int i = 0; i < A.length; i++) {
            ++countA[A[i]];
            ++countB[B[i]];
            if(A[i] == B[i]) {
                ++same[A[i]];
            }
        }

        for (int i = 1; i < 7; i++) {
            if(countA[i] + countB[i] - same[i] == A.length) {
                return Math.min(countA[i], countB[i]) - same[i];
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
