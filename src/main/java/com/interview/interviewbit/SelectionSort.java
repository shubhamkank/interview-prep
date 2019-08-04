package com.interview.interviewbit;

import java.util.Arrays;

public class SelectionSort {

    public static void sort(int[] a) {
        int n = a.length;
        for (int i = 0; i <= n - 2; i++) {
            int iMin = i;
            for (int j = i+1; j < n; j++) {
                if(a[j] < a[iMin]) {
                    iMin = j;
                }
            }
            int temp = a[i];
            a[i] = a[iMin];
            a[iMin] = temp;
        }
        Arrays.stream(a).forEach(System.out::println);
        String s = "$o: AlbusDroolsFact(\n" +
                "\tcontext[\"SELLER_STATE\"] == null ||\n" +
                "\tcontext[\"SELLER_STATE\"] in (\"ONHOLD\", \"DELETED\")\n" +
                ")";
    }

    public static void main(String[] args) {
        sort(new int[] {2, 7, 4, 1, 5, 3});
    }
}
