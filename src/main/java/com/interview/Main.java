package com.interview;

public class Main {

    public static void main(String [] args) {
        int a[][] = {
                {1,  2,  3,  4,  5,  6},
                {7,  8,  9,  10, 11, 12},
                {13, 14, 15, 16, 17, 18}
        };
        Matrix.printSpiralMatrix(3, 6, a);

        int b[] = {34, 23, 1, 54, 24, 9, 4, 2, 87};
        int c[] = {5, 4, 3, 2, 1};
        int d[] = {1};
        int e[] = {1, 2, 3, 4, 5};

        System.out.println();
        SortingUtil.quickSort(b, 0, b.length-1);
        for(int x : b) {
            System.out.print(x + " ");
        }

        System.out.println();
        SortingUtil.quickSort(c, 0, c.length-1);
        for(int x : c) {
            System.out.print(x + " ");
        }

        System.out.println();
        SortingUtil.quickSort(d, 0, d.length-1);
        for(int x : d) {
            System.out.print(x + " ");
        }

        System.out.println();
        SortingUtil.quickSort(e, 0, e.length-1);
        for(int x : e) {
            System.out.print(x + " ");
        }
    }
}
