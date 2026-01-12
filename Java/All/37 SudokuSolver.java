// LeetCode 37. Sudoku Solver
// Time Complexity: O(9^(n*n)) in worst case (backtracking)
// Space Complexity: O(n*n) recursion stack (implicit)

class Solution {
    public void solveSudoku(char[][] board) {
        solve(board);
    }

    private boolean solve(char[][] board) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {

                // Find empty cell
                if (board[row][col] == '.') {

                    // Try digits 1 to 9
                    for (char c = '1'; c <= '9'; c++) {
                        if (isValid(board, row, col, c)) {
                            board[row][col] = c;

                            // Recurse
                            if (solve(board)) return true;

                            // Backtrack
                            board[row][col] = '.';
                        }
                    }
                    return false; // no valid digit fits
                }
            }
        }
        return true; // solved
    }

    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i++) {

            // Check row
            if (board[row][i] == c) return false;

            // Check column
            if (board[i][col] == c) return false;

            // Check 3x3 sub-box
            int r = 3 * (row / 3) + i / 3;
            int cc = 3 * (col / 3) + i % 3;
            if (board[r][cc] == c) return false;
        }
        return true;
    }
}
