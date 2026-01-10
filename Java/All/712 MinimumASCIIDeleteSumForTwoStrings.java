// LeetCode 712. Minimum ASCII Delete Sum for Two Strings
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

import java.util.Arrays;

class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int n = s1.length();
        int m = s2.length();

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        return solve(0, 0, s1, s2, n, m, dp);
    }

    private int solve(int i, int j, String s1, String s2,
                      int n, int m, int[][] dp) {

        // Both strings fully processed
        if (i == n && j == m) {
            return 0;
        }

        // s1 finished → delete remaining characters of s2
        if (i == n) {
            return s2.substring(j).chars().sum();
        }

        // s2 finished → delete remaining characters of s1
        if (j == m) {
            return s1.substring(i).chars().sum();
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Characters match → no deletion cost
        if (s1.charAt(i) == s2.charAt(j)) {
            return dp[i][j] = solve(i + 1, j + 1, s1, s2, n, m, dp);
        }

        // Delete from s1 or s2
        int deleteFromS1 = s1.charAt(i) + solve(i + 1, j, s1, s2, n, m, dp);
        int deleteFromS2 = s2.charAt(j) + solve(i, j + 1, s1, s2, n, m, dp);

        return dp[i][j] = Math.min(deleteFromS1, deleteFromS2);
    }
}
