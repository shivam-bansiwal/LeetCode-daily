// LeetCode 36. Valid Sudoku
// Time Complexity: O(1)  (fixed 9x9 board)
// Space Complexity: O(1)

class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (board[i][j] == '.') continue;

                int num = board[i][j] - '1';
                int boxIdx = (i / 3) * 3 + (j / 3);

                // Check row, column, and 3x3 box
                if (row[i][num] || col[j][num] || box[boxIdx][num]) {
                    return false;
                }

                row[i][num] = true;
                col[j][num] = true;
                box[boxIdx][num] = true;
            }
        }

        return true;
    }
}
