// LeetCode 1975. Maximum Matrix Sum
// Time Complexity: O(n^2)
// Space Complexity: O(1)

class Solution {
    public long maxMatrixSum(int[][] matrix) {
        int n = matrix.length;
        long totalSum = 0;
        int smallestAbsValue = Integer.MAX_VALUE;
        int negativeCount = 0;
        boolean hasZero = false;

        // Single pass to compute absolute sum,
        // count negatives, and track smallest absolute value
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                totalSum += Math.abs(val);

                if (val == 0) {
                    hasZero = true;
                }
                if (val < 0) {
                    negativeCount++;
                }

                smallestAbsValue = Math.min(smallestAbsValue, Math.abs(val));
            }
        }

        // If negatives are even or a zero exists,
        // all values can be made non-negative
        if (hasZero || negativeCount % 2 == 0) {
            return totalSum;
        }

        // Otherwise, one value must remain negative
        return totalSum - 2L * smallestAbsValue;
    }
}
