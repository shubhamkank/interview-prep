package com.interview.leetcode;

import java.util.LinkedList;

public class NumberOfIslands {

    /* Depth First Search
       Time complexity: O(m * n), Space complexity: O(m * n) */
    public static int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
    	int m = grid.length;
    	int n = grid[0].length;
    	int numOfIslands = 0;

    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '1') {
    				numOfIslands++;
    				dfsUtil(grid, i, j);
    			}
    		}
    	}
        return numOfIslands;
    }

    /* Breadth First Search
       Time complexity: O(m * n), Space complexity: O(m * n)?? */
    public static int numIslands2(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
    	int m = grid.length;
    	int n = grid[0].length;
    	int numOfIslands = 0;

    	for(int i = 0; i < m; i++) {
    		for(int j = 0; j < n; j++) {
    			if(grid[i][j] == '1') {
    				numOfIslands++;
    				grid[i][j] = '0';
                    LinkedList<Integer> queue = new LinkedList<>();
                    queue.add(i * n + j);
                    while(!queue.isEmpty()) {
                        int id = queue.remove();
                        int row = id / n;
                        int col = id % n;

                        if(row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.add((row - 1) * n + col);
                            grid[row - 1][col] = '0';
                        }
                        if(row + 1 < m && grid[row + 1][col] == '1') {
                            queue.add((row + 1) * n + col);
                            grid[row + 1][col] = '0';
                        }
                        if(col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.add(row * n + col - 1);
                            grid[row][col - 1] = '0';
                        }
                        if(col + 1 < n && grid[row][col + 1] == '1') {
                            queue.add(row * n + col + 1);
                            grid[row][col + 1] = '0';
                        }
                    }
    			}
    		}
    	}
        return numOfIslands;
    }

    private static void dfsUtil(char[][] grid, int i, int j) {
        int m = grid.length;
        int n = grid[0].length;

        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') {
            return;
        }

        grid[i][j] = '0';
        dfsUtil(grid, i - 1, j);
        dfsUtil(grid, i + 1, j);
        dfsUtil(grid, i, j - 1);
        dfsUtil(grid, i, j + 1);
    }

    static class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];

            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if(grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        /* Time complexity: O(alpha(n)) - alpha extremely slow moving function - for numbers in the universe < 5
           Therefore, almost constant time */
        public int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        /* Time complexity: O(alpha(n)) - Inverse Ackermann Function */
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
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    /* Union Find Data Structure
       Time complexity: O(m * n), Space complexity: O(m * n) */
    public static int numIslands3(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;

        UnionFind uf = new UnionFind(grid);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == '1') {
                    grid[i][j] = 0;
                    if(i - 1 >= 0 && grid[i - 1][j] == '1') {
                        uf.union(i * n + j, (i - 1) * n + j);
                    }
                    if(i + 1 < m && grid[i + 1][j] == '1') {
                        uf.union(i * n + j, (i + 1) * n + j);
                    }
                    if(j - 1 >= 0 && grid[i][j - 1] == '1') {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                    if(j + 1 < n && grid[i][j + 1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }
        return uf.getCount();
    }

    public static void main(String[] args) {
        char[][] grid = {
                            {'1', '1', '1'},
                            {'0', '1', '0'},
                            {'1', '0', '0'},
                            {'1', '0', '1'}
                        };
        //System.out.println(numIslands(grid));
        //System.out.println(numIslands2(grid));
        System.out.println(numIslands3(grid));
    }
}
