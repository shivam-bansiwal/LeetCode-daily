// LeetCode 1411. Number of Ways to Paint N × 3 Grid
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int numOfWays(int n) {
        final int MOD = 1_000_000_007;

        // For the first row:
        // twoColor: patterns using exactly 2 colours (ABA type)
        // threeColor: patterns using 3 different colours (ABC type)
        long twoColor = 6;
        long threeColor = 6;

        // Build row by row using transitions
        for (int row = 2; row <= n; row++) {
            long newTwoColor = (twoColor * 3 + threeColor * 2) % MOD;
            long newThreeColor = (twoColor * 2 + threeColor * 2) % MOD;

            twoColor = newTwoColor;
            threeColor = newThreeColor;
        }

        return (int) ((twoColor + threeColor) % MOD);
    }
}
