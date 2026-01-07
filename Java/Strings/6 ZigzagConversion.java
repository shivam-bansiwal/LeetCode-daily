// LeetCode 6. Zigzag Conversion
// Time Complexity: O(n)
// Space Complexity:
//  - Approach 1: O(1) extra (output excluded)
//  - Approach 2: O(n)

class Solution {

    // --------------------------------------------------
    // Approach 1: Cycle-based (Math / Index Jumping)
    // --------------------------------------------------
    public String convert(String s, int numRows) {
        int n = s.length();

        // Edge cases
        if (numRows == 1 || numRows >= n) {
            return s;
        }

        StringBuilder sb = new StringBuilder(n);
        int cycle = (numRows - 1) * 2;

        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < n; i += cycle) {
                sb.append(s.charAt(i));

                // Middle rows have diagonal characters
                int diag = i + cycle - 2 * row;
                if (row > 0 && row < numRows - 1 && diag < n) {
                    sb.append(s.charAt(diag));
                }
            }
        }

        return sb.toString();
    }

    // --------------------------------------------------
    // Approach 2: Simulate Zigzag (Bounce / Direction)
    // --------------------------------------------------
    public String convertBounce(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int row = 0;
        int dir = 1; // 1 = down, -1 = up

        for (char c : s.toCharArray()) {
            rows[row].append(c);

            // Change direction at top or bottom
            if (row == 0) {
                dir = 1;
            } else if (row == numRows - 1) {
                dir = -1;
            }

            row += dir;
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder sb : rows) {
            result.append(sb);
        }

        return result.toString();
    }
}
