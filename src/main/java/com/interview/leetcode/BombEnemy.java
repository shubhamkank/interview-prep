package com.interview.leetcode;

public class BombEnemy {

    //Brute Force
    //Time complexity: O(mn * (m + n)), Space complexity: O(1)
    public int maxKilledEnemies(char[][] grid) {
        int max = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == '0') {
                    max = Math.max(max, calculateKills(grid, i, j));
                }
            }
        }
        return max;
    }

    private int calculateKills(char[][] grid, int x, int y) {
        int rowCount = 0, colCount = 0;

        int j = y + 1;
        while (j < grid[0].length && grid[x][j] != 'W') {
            if(grid[x][j] == 'E') {
                rowCount++;
            }
            j++;
        }

        j = y - 1;
        while (j >= 0 && grid[x][j] != 'W') {
            if(grid[x][j] == 'E') {
                rowCount++;
            }
            j--;
        }

        int i = x + 1;
        while(i < grid.length && grid[i][y] != 'W') {
            if(grid[i][y] == 'E') {
                colCount++;
            }
            i++;
        }

        i = x - 1;
        while(i >= 0 && grid[i][y] != 'W') {
            if(grid[i][y] == 'E') {
                colCount++;
            }
            i--;
        }

        return rowCount + colCount;
    }

    //Time complexity: O(mn), Space complexity: O(mn)
    public int maxKilledEnemies2(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int max = 0;
        int[][] count = new int[m][n];
        calculateRowKills(grid, count);
        calculateColKills(grid, count);

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '0') {
                    max = Math.max(max, count[i][j]);
                }
            }
        }
        return max;
    }

    private void calculateRowKills(char[][] grid, int[][] count) {
        int m = grid.length;
        int n = grid[0].length;

        for (int i = 0; i < m; i++) {
            int leftCount = 0;
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'W') {
                    leftCount = 0;
                } else if (grid[i][j] == 'E') {
                    leftCount++;
                } else {
                    count[i][j] += leftCount;
                }
            }

            int rightCount = 0;
            for (int j = n - 1; j >= 0; j--) {
                if (grid[i][j] == 'W') {
                    rightCount = 0;
                } else if (grid[i][j] == 'E') {
                    rightCount++;
                } else {
                    count[i][j] += rightCount;
                }
            }
        }
    }

    private void calculateColKills(char[][] grid, int[][] count) {
        int m = grid.length;
        int n = grid[0].length;

        for (int j = 0; j < n; j++) {
            int topCount = 0;
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 'W') {
                    topCount = 0;
                } else if (grid[i][j] == 'E') {
                    topCount++;
                } else {
                    count[i][j] += topCount;
                }
            }

            int bottomCount = 0;
            for (int i = m - 1; i >= 0; i--) {
                if (grid[i][j] == 'W') {
                    bottomCount = 0;
                } else if (grid[i][j] == 'E') {
                    bottomCount++;
                } else {
                    count[i][j] += bottomCount;
                }
            }
        }
    }

    //Time complexity: O(mn), Space complexity: O(n)
    //Note: Since the row and col hits are calculated only after a wall is reached, every element is traversed only once
    //throughout the execution
    //https://leetcode.com/problems/bomb-enemy/discuss/83387/Short-O(mn)-time-O(n)-space-solution
    public int maxKilledEnemies3(char[][] grid) {
        if(grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;

        int max = 0;
        int rowHits = 0;
        int[] colHits = new int[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(j == 0 || grid[i][j - 1] == 'W') {
                    // reset rowHits when hit wall, re-calculate enemy after
                    rowHits = 0;
                    for (int k = j; k < n && grid[i][k] != 'W'; k++) {
                        if (grid[i][k] == 'E') {
                            rowHits++;
                        }
                    }
                }

                if(i == 0 || grid[i - 1][j] == 'W') {
                    // reset colHits[j] when hit wall, re-calculate enemy after
                    colHits[j] = 0;
                    for (int k = i; k < m && grid[k][j] != 'W'; k++) {
                        if (grid[k][j] == 'E') {
                            colHits[j]++;
                        }
                    }
                }

                if (grid[i][j] == '0') {
                    max = Math.max(max, rowHits + colHits[j]);
                }
            }
        }
        return max;
    }
}
