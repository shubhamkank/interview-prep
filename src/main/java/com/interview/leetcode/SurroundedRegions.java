package com.interview.leetcode;

import java.util.LinkedList;

public class SurroundedRegions {

    /* Depth-first search
       Time complexity: O(m * n) */
    public static void solve(char[][] board) {
        if(board.length < 2 || board[0].length < 2) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        //Any 'O' connected to a boundary can't be turned to 'X'
        //Start from first and last column, turn 'O' to '*'
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                dfsUtil(board, i, 0);
            }
            if(board[i][n - 1] == 'O') {
                dfsUtil(board, i, n - 1);
            }
        }

        //Start from first and last row, turn '0' to '*'
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') {
                dfsUtil(board, 0, j);
            }
            if(board[m - 1][j] == 'O') {
                dfsUtil(board, m - 1, j);
            }
        }

        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void dfsUtil(char[][] board, int i, int j) {
        if(i < 0 || i > board.length - 1 || j < 0 || j > board[0].length - 1) {
            return;
        }

        if(board[i][j] == 'O') {
            board[i][j] = '*';
        }
        //i > 1, i < m - 2, j > 1, j < n - 2 avoid deep recursion and therefore stackoverflow
        if(i > 1 && board[i - 1][j] == 'O') {
            dfsUtil(board, i - 1, j);
        }
        if(i < board.length - 2 && board[i + 1][j] == 'O') {
            dfsUtil(board, i + 1, j);
        }
        if(j > 1 && board[i][j - 1] == 'O') {
            dfsUtil(board, i, j - 1);
        }
        if(j < board[i].length - 2 && board[i][j + 1] == 'O') {
            dfsUtil(board, i, j + 1);
        }
    }

    /* Union find
       Time complexity: O(m * n), Space complexity: O(m * n) */
    public static void solve2(char[][] board) {
        if(board.length < 2 || board[0].length < 2) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        UnionFind uf = new UnionFind(m * n + 1);
        int dummyNode = m * n;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                        uf.union(i * n + j, dummyNode);
                    } else {
                        if(i > 0 && board[i - 1][j] == 'O') {
                            uf.union((i - 1) * n + j, i * n + j);
                        }
                        if(i < m - 1 && board[i + 1][j] == 'O') {
                            uf.union((i + 1) * n + j, i * n + j);
                        }
                        if(j > 0 && board[i][j - 1] == 'O') {
                            uf.union(i * n + j - 1, i * n + j);
                        }
                        if(j < n - 1 && board[i][j + 1] == 'O') {
                            uf.union(i * n + j + 1, i * n + j);
                        }
                    }
                }
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(!uf.connected(i * n + j, dummyNode)) {
                    board[i][j] = 'X';
                }
            }
        }
    }

    static class UnionFind {
        int[] parent;
        int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for(int i = 0; i < n; i ++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        public void union(int i, int j) {
            int iRoot = find(i);
            int jRoot = find(j);

            if(iRoot == jRoot) {
                return;
            }

            if(rank[iRoot] < rank[jRoot]) {
                parent[iRoot] = jRoot;
            } else if(rank[jRoot] < rank[iRoot]) {
                parent[jRoot] = iRoot;
            } else {
                parent[jRoot] = iRoot;
                rank[iRoot]++;
            }
        }

        public boolean connected(int i, int j) {
            return find(i) == find(j);
        }

    }

    /* Breadth-first search
       Time complexity: O(m * n), Space complexity: O(m * n) */
    public static void solve3(char[][] board) {
        if(board.length < 2 || board[0].length < 2) {
            return;
        }

        int m = board.length;
        int n = board[0].length;

        //Any 'O' connected to a boundary can't be turned to 'X'
        //Start from first and last column, turn 'O' to '*'
        for(int i = 0; i < m; i++) {
            if(board[i][0] == 'O') {
                bfsUtil(board, i, 0);
            }
            if(board[i][n - 1] == 'O') {
                bfsUtil(board, i, n - 1);
            }
        }

        //Start from first and last row, turn '0' to '*'
        for(int j = 0; j < n; j++) {
            if(board[0][j] == 'O') {
                bfsUtil(board, 0, j);
            }
            if(board[m - 1][j] == 'O') {
                bfsUtil(board, m - 1, j);
            }
        }

        //post-prcessing, turn 'O' to 'X', '*' back to 'O', keep 'X' intact
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 'O') {
                    board[i][j] = 'X';
                } else if(board[i][j] == '*') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private static void bfsUtil(char[][] board, int row, int col) {
        LinkedList<Integer[]> queue = new LinkedList<>();
        queue.add(new Integer[] {row, col});
        board[row][col] = '*';
        while(!queue.isEmpty()) {
            Integer[] cell = queue.remove();
            int i = cell[0];
            int j = cell[1];

            if(i > 1 && board[i - 1][j] == 'O') {
                queue.add(new Integer[] {i - 1, j});
                board[i - 1][j] = '*';
            }
            if(i < board.length - 2 && board[i + 1][j] == 'O') {
                queue.add(new Integer[] {i + 1, j});
                board[i + 1][j] = '*';
            }
            if(j > 1 && board[i][j - 1] == 'O') {
                queue.add(new Integer[] {i, j - 1});
                board[i][j - 1] = '*';
            }
            if(j < board[i].length - 2 && board[i][j + 1] == 'O') {
                queue.add(new Integer[] {i, j + 1});
                board[i][j + 1] = '*';
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X','X','X','X'},{'X','O','O','X'},{'X','X','O','X'},{'X','O','X','X'}};
        solve3(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
