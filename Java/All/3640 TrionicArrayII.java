// LeetCode 3640. Trionic Array II
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.Arrays;

class Solution {
    private static final long NEG_INF = (long) -1e14;

    /*
        slope:
        0 -> not started
        1 -> increasing
        2 -> decreasing
        3 -> last increasing
    */
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        long[][] dp = new long[n + 1][4];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], NEG_INF);
        }

        // Valid only if we already completed the pattern and are in slope 3
        dp[n][3] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int slope = 0; slope < 4; slope++) {

                long skip = NEG_INF;
                long take = NEG_INF;

                // Skip allowed only before starting
                if (slope == 0) {
                    skip = dp[i + 1][0];
                }

                // Once in slope 3, we can always keep taking (as long as comparisons allow below)
                if (slope == 3) {
                    take = nums[i] + dp[i + 1][3];
                }

                if (i + 1 < n) {
                    int curr = nums[i];
                    int next = nums[i + 1];

                    if (slope == 0) {
                        if (curr < next) take = Math.max(take, curr + dp[i + 1][1]);
                    } else if (slope == 1) {
                        if (curr < next) take = Math.max(take, curr + dp[i + 1][1]);
                        else if (curr > next) take = Math.max(take, curr + dp[i + 1][2]);
                    } else if (slope == 2) {
                        if (curr > next) take = Math.max(take, curr + dp[i + 1][2]);
                        else if (curr < next) take = Math.max(take, curr + dp[i + 1][3]);
                    } else { // slope == 3
                        if (curr < next) take = Math.max(take, curr + dp[i + 1][3]);
                    }
                }

                dp[i][slope] = Math.max(skip, take);
            }
        }

        return dp[0][0];
    }
}
