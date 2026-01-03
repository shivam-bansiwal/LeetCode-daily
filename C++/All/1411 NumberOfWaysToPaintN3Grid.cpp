// LeetCode 1411. Number of Ways to Paint N × 3 Grid
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
public:
    int numOfWays(int n) {
        const int MOD = 1'000'000'007;

        // Base case for the first row
        long long twoColor = 6;    // ABA patterns
        long long threeColor = 6;  // ABC patterns

        // Build row by row
        for (int row = 2; row <= n; row++) {
            long long newTwoColor = (twoColor * 3 + threeColor * 2) % MOD;
            long long newThreeColor = (twoColor * 2 + threeColor * 2) % MOD;

            twoColor = newTwoColor;
            threeColor = newThreeColor;
        }

        return (int)((twoColor + threeColor) % MOD);
    }
};
