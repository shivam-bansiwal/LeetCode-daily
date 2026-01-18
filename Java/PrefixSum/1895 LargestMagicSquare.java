// LeetCode 1895. Largest Magic Square
// Time Complexity: O(n * m * min(n, m))
// Space Complexity: O(n * m)

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        long[][] row = new long[n][m];
        long[][] col = new long[n][m];
        long[][] diag1 = new long[n][m]; // top-left to bottom-right
        long[][] diag2 = new long[n][m]; // top-right to bottom-left

        buildPrefix(grid, row, col, diag1, diag2);

        int maxSide = Math.min(n, m);

        while (maxSide > 1) {
            if (existsMagicSquare(grid, row, col, diag1, diag2, maxSide)) {
                return maxSide;
            }
            maxSide--;
        }

        return 1;
    }

    private void buildPrefix(int[][] g, long[][] row, long[][] col,
                             long[][] d1, long[][] d2) {
        int n = g.length, m = g[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                row[i][j] = g[i][j] + (j > 0 ? row[i][j - 1] : 0);
                col[i][j] = g[i][j] + (i > 0 ? col[i - 1][j] : 0);
                d1[i][j] = g[i][j] + (i > 0 && j > 0 ? d1[i - 1][j - 1] : 0);
                d2[i][j] = g[i][j] + (i > 0 && j + 1 < m ? d2[i - 1][j + 1] : 0);
            }
        }
    }

    private boolean existsMagicSquare(int[][] g, long[][] row, long[][] col,
                                      long[][] d1, long[][] d2, int size) {
        int n = g.length, m = g[0].length;
        int len = size - 1;

        for (int i = 0; i + len < n; i++) {
            for (int j = 0; j + len < m; j++) {

                long target = row[i][j + len] - (j > 0 ? row[i][j - 1] : 0);

                // rows
                for (int r = i; r <= i + len; r++) {
                    long s = row[r][j + len] - (j > 0 ? row[r][j - 1] : 0);
                    if (s != target) return false;
                }

                // columns
                for (int c = j; c <= j + len; c++) {
                    long s = col[i + len][c] - (i > 0 ? col[i - 1][c] : 0);
                    if (s != target) return false;
                }

                // diagonals
                long diagMain = d1[i + len][j + len] -
                                (i > 0 && j > 0 ? d1[i - 1][j - 1] : 0);
                long diagAnti = d2[i + len][j] -
                                (i > 0 && j + len + 1 < m ? d2[i - 1][j + len + 1] : 0);

                if (diagMain == target && diagAnti == target) {
                    return true;
                }
            }
        }
        return false;
    }
}
