package com.interview;

public class Matrix {

    //O(m*n)
    public static void printSpiralMatrix(int m, int n, int [][] a) {
        int k = 0, l = 0;

        /*
        k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        */

        while(k < m && l < n) {
            //Move to the right
            for(int i = l; i < n; i++) {
                System.out.print(a[k][i] + " ");
            }
            k++;

            //Move down
            for(int i = k; i < m; i++) {
                System.out.print(a[i][n-1] + " ");
            }
            n--;

            if(k < m) {
                //Move left
                for (int i = n - 1; i >= l; i--) {
                    System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            if(l < n) {
                //Move up
                for (int i = m-1; i >= k; i--) {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }
    }

}
