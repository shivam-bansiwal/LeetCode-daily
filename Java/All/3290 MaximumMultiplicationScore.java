// Maximum Multiplication Score
// Pick exactly 4 elements from b in order to maximise sum(a[i] * picked[i])
// a.length == 4

import java.util.Arrays;

class Solution {

    // -----------------------------
    // Approach 1: Pure Recursion
    // Time: Exponential
    // Space: O(n) recursion stack
    // -----------------------------
    public long maxScoreRecursion(int[] a, int[] b) {
        return solveRec(0, 0, a, b, b.length);
    }

    private long solveRec(int i, int j, int[] a, int[] b, int n) {
        if (i == 4) return 0;
        if (j == n) return Long.MIN_VALUE;

        long notPick = solveRec(i, j + 1, a, b, n);
        long pick = solveRec(i + 1, j + 1, a, b, n);

        if (pick != Long.MIN_VALUE) {
            pick += (long) a[i] * b[j];
        }

        return Math.max(notPick, pick);
    }

    // -----------------------------
    // Approach 2: Recursion + Memo
    // Time: O(4 * n)
    // Space: O(4 * n)
    // -----------------------------
    public long maxScoreMemo(int[] a, int[] b) {
        int n = b.length;
        long[][] dp = new long[4][n];

        for (int i = 0; i < 4; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE);
        }

        return solveMemo(0, 0, a, b, n, dp);
    }

    private long solveMemo(int i, int j, int[] a, int[] b, int n, long[][] dp) {
        if (i == 4) return 0;
        if (j == n) return Long.MIN_VALUE;

        if (dp[i][j] != Long.MIN_VALUE) return dp[i][j];

        long notPick = solveMemo(i, j + 1, a, b, n, dp);
        long pick = solveMemo(i + 1, j + 1, a, b, n, dp);

        if (pick != Long.MIN_VALUE) {
            pick += (long) a[i] * b[j];
        }

        return dp[i][j] = Math.max(notPick, pick);
    }

    // -----------------------------
    // Approach 3: Tabulation
    // Time: O(4 * n)
    // Space: O(4 * n)
    // -----------------------------
    public long maxScoreTabulation(int[] a, int[] b) {
        int n = b.length;
        long[][] dp = new long[5][n + 1];

        for (int i = 1; i <= 4; i++) {
            Arrays.fill(dp[i], Long.MIN_VALUE);
        }

        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= n; j++) {
                long notPick = dp[i][j - 1];
                long pick = dp[i - 1][j - 1];

                if (pick != Long.MIN_VALUE) {
                    pick += (long) a[i - 1] * b[j - 1];
                }

                dp[i][j] = Math.max(notPick, pick);
            }
        }

        return dp[4][n];
    }

    // -----------------------------
    // Approach 4: Space Optimised DP
    // Time: O(4 * n)
    // Space: O(n)
    // -----------------------------
    public long maxScore(int[] a, int[] b) {
        int n = b.length;
        long[] prev = new long[n + 1];

        for (int i = 1; i <= 4; i++) {
            long[] curr = new long[n + 1];
            Arrays.fill(curr, Long.MIN_VALUE);

            for (int j = 1; j <= n; j++) {
                long notPick = curr[j - 1];
                long pick = prev[j - 1];

                if (pick != Long.MIN_VALUE) {
                    pick += (long) a[i - 1] * b[j - 1];
                }

                curr[j] = Math.max(notPick, pick);
            }
            prev = curr;
        }

        return prev[n];
    }
}
