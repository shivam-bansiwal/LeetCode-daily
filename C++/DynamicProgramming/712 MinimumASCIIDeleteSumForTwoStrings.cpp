// LeetCode 712. Minimum ASCII Delete Sum for Two Strings
// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    int minimumDeleteSum(string s1, string s2) {
        int n = s1.length();
        int m = s2.length();

        vector<vector<int>> dp(n, vector<int>(m, -1));
        return solve(0, 0, s1, s2, n, m, dp);
    }

private:
    int solve(int i, int j, string& s1, string& s2,
              int n, int m, vector<vector<int>>& dp) {

        // Both strings finished
        if (i == n && j == m) {
            return 0;
        }

        // s1 finished → delete rest of s2
        if (i == n) {
            int sum = 0;
            for (int k = j; k < m; k++) sum += s2[k];
            return sum;
        }

        // s2 finished → delete rest of s1
        if (j == m) {
            int sum = 0;
            for (int k = i; k < n; k++) sum += s1[k];
            return sum;
        }

        if (dp[i][j] != -1) {
            return dp[i][j];
        }

        // Characters match
        if (s1[i] == s2[j]) {
            return dp[i][j] = solve(i + 1, j + 1, s1, s2, n, m, dp);
        }

        // Delete from either string
        int deleteFromS1 = s1[i] + solve(i + 1, j, s1, s2, n, m, dp);
        int deleteFromS2 = s2[j] + solve(i, j + 1, s1, s2, n, m, dp);

        return dp[i][j] = min(deleteFromS1, deleteFromS2);
    }
};
