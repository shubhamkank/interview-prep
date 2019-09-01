package com.interview.leetcode;

public class RangeSumQuery2DMutable {

    public static void main(String[] args) {
        NumMatrix numMatrix = new NumMatrix(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));
        numMatrix.update(3, 2, 2);
        System.out.println(numMatrix.sumRegion(2, 1, 4, 3));

        NumMatrix2 numMatrix2 = new NumMatrix2(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));
        numMatrix2.update(3, 2, 2);
        System.out.println(numMatrix2.sumRegion(2, 1, 4, 3));

        NumMatrix3 numMatrix3 = new NumMatrix3(new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        });
        System.out.println(numMatrix3.sumRegion(2, 1, 4, 3));
        numMatrix3.update(3, 2, 2);
        System.out.println(numMatrix3.sumRegion(2, 1, 4, 3));
    }
}

class NumMatrix {

    int[][] tree;   //BIT: https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
    int[][] nums;   //Store copy of matrix in order to calculate the increase in value or diff from previous stored value
    int m;
    int n;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        m = matrix.length;
        n = matrix[0].length;

        tree = new int[m + 1][n + 1];
        nums = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                update(i, j, matrix[i][j]);
            }
        }
    }

    // Time complexity: O(logm * logn)
    // tree[i][j] saves the rangeSum of [i-(i&-i), i] x [j-(j&-j), j]
    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];
        nums[row][col] = val;
        for (int i = row + 1; i <= m; i += i & (-i)) {
            for (int j = col + 1; j <= n; j += j & (-j)) {
                tree[i][j] += diff;
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum(row2 + 1, col2 + 1) + sum(row1, col1) - sum(row1, col2 + 1) - sum(row2 + 1, col1);
    }

    //Time complexity: O(logm * logn)
    public int sum(int row, int col) {
        int sum = 0;
        for (int i = row; i > 0; i -= i & (-i)) {
            for (int j = col; j > 0; j -= j & (-j)) {
                sum += tree[i][j];
            }
        }
        return sum;
    }
}

class NumMatrix2 {

    int[][] rowSums;
    int[][] nums;
    int m;
    int n;

    public NumMatrix2(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return;
        }

        m = matrix.length;
        n = matrix[0].length;

        rowSums = new int[m][n + 1];
        nums = new int[m][n];

        // rowSums[i][j] = matrix[i][0] + matrix[i][1] + ... + matrix[i][j - 1]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowSums[i][j + 1] = rowSums[i][j] + matrix[i][j];
            }
        }
    }

    // Time complexity: O(n)
    public void update(int row, int col, int val) {
        int diff = val - nums[row][col];

        for (int j = col + 1; j <= n; j++) {
            rowSums[row][j] += diff;
        }
        nums[row][col] = val;
    }

    // Time complexity: O(m)
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        for (int i = row1; i <= row2 ; i++) {
            sum += rowSums[i][col2 + 1] - rowSums[i][col1];
        }
        return sum;
    }
}

class NumMatrix3 {

    TreeNode root;

    public NumMatrix3(int[][] matrix) {
        if(matrix.length == 0) {
            root = null;
        } else {
            root = buildTree(matrix, 0, 0, matrix.length - 1, matrix[0].length - 1);
        }
    }

    private TreeNode buildTree(int[][] matrix, int row1, int col1, int row2, int col2) {
        if(row2 < row1 || col2 < col1) {
            return null;
        }

        TreeNode node = new TreeNode(row1, col1, row2, col2);

        if(row1 == row2 && col1 == col2) {
            node.sum = matrix[row1][col1];
            return node;
        }

        int rowMid = (row1 + row2) / 2;
        int colMid = (col1 + col2) / 2;

        node.c1 = buildTree(matrix, row1, col1, rowMid, colMid);
        node.c2 = buildTree(matrix, row1, colMid + 1, rowMid, col2);
        node.c3 = buildTree(matrix, rowMid + 1, col1, row2, colMid);
        node.c4 = buildTree(matrix, rowMid + 1, colMid + 1, row2, col2);

        node.sum += node.c1 != null ? node.c1.sum : 0;
        node.sum += node.c2 != null ? node.c2.sum : 0;
        node.sum += node.c3 != null ? node.c3.sum : 0;
        node.sum += node.c4 != null ? node.c4.sum : 0;

        return node;
    }

    public void update(int row, int col, int val) {
        update(root, row, col, val);
    }

    private void update(TreeNode root, int row, int col, int val) {
        if(root.row1 == root.row2 && root.col1 == root.col2 && root.row1 == row && root.col1 == col) {
            root.sum = val;
            return;
        }

        int rowMid = (root.row1 + root.row2) / 2;
        int colMid = (root.col1 + root.col2) / 2;

        TreeNode next;

        if(row <= rowMid) {
            if(col <= colMid) {
                next = root.c1;
            } else {
                next = root.c2;
            }
        } else {
            if(col <= colMid) {
                next = root.c3;
            } else  {
                next = root.c4;
            }
        }
        root.sum -= next.sum;
        update(next, row, col, val);
        root.sum += next.sum;
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sumRegion(root, row1, col1, row2, col2);
    }

    private int sumRegion(TreeNode root, int row1, int col1, int row2, int col2) {
        if(root.row1 == row1 && root.col1 == col1 && root.row2 == row2 && root.col2 == col2) {
            return root.sum;
        }

        int rowMid = (root.row1 + root.row2) / 2;
        int colMid = (root.col1 + root.col2) / 2;

        if(rowMid >= row2) {
            if(colMid >= col2) {
                return sumRegion(root.c1, row1, col1, row2, col2);
            } else if(colMid + 1 <= col1) {
                return sumRegion(root.c2, row1, col1, row2, col2);
            } else {
                return sumRegion(root.c1, row1, col1, row2, colMid) + sumRegion(root.c2, row1, colMid + 1, row2, col2);
            }
        } else if(rowMid + 1 <= row1) {
            if(colMid >= col2) {
                return sumRegion(root.c3, row1, col1, row2, col2);
            } else if(colMid + 1 <= col1) {
                return sumRegion(root.c4, row1, col1, row2, col2);
            } else {
                return sumRegion(root.c3, row1, col1, row2, colMid) + sumRegion(root.c4, row1, colMid + 1, row2, col2);
            }
        } else {
            if(colMid >= col2) {
                return sumRegion(root.c1, row1, col1, rowMid, col2) + sumRegion(root.c3, rowMid + 1, col1, row2, col2);
            } else if(colMid + 1 <= col1) {
                return sumRegion(root.c2, row1, col1, rowMid, col2) + sumRegion(root.c4, rowMid + 1, col1, row2, col2);
            } else {
                return sumRegion(root.c1, row1, col1, rowMid, colMid) +
                        sumRegion(root.c2, row1, colMid + 1, rowMid, col2) +
                        sumRegion(root.c3, rowMid + 1, col1, row2, colMid) +
                        sumRegion(root.c4, rowMid + 1, colMid + 1, row2, col2);
            }
        }
    }
}

class TreeNode {
    int row1, row2, col1, col2, sum;
    TreeNode c1, c2, c3, c4;

    public TreeNode(int row1, int col1, int row2, int col2) {
        this.row1 = row1;
        this.col1 = col1;
        this.row2 = row2;
        this.col2 = col2;
        this.sum = 0;
    }
}
