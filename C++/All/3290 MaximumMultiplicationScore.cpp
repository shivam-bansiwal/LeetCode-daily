// Maximum Multiplication Score
// a.size() == 4

#include <vector>
#include <climits>
using namespace std;

class Solution {
public:

    // -----------------------------
    // Approach 1: Pure Recursion
    // -----------------------------
    long long maxScoreRecursion(vector<int>& a, vector<int>& b) {
        return solveRec(0, 0, a, b);
    }

    long long solveRec(int i, int j, vector<int>& a, vector<int>& b) {
        if (i == 4) return 0;
        if (j == b.size()) return LLONG_MIN;

        long long notPick = solveRec(i, j + 1, a, b);
        long long pick = solveRec(i + 1, j + 1, a, b);

        if (pick != LLONG_MIN) {
            pick += (long long) a[i] * b[j];
        }

        return max(notPick, pick);
    }

    // -----------------------------
    // Approach 2: Memoisation
    // -----------------------------
    long long maxScoreMemo(vector<int>& a, vector<int>& b) {
        int n = b.size();
        vector<vector<long long>> dp(4, vector<long long>(n, LLONG_MIN));
        return solveMemo(0, 0, a, b, dp);
    }

    long long solveMemo(int i, int j, vector<int>& a, vector<int>& b,
                        vector<vector<long long>>& dp) {
        if (i == 4) return 0;
        if (j == b.size()) return LLONG_MIN;

        if (dp[i][j] != LLONG_MIN) return dp[i][j];

        long long notPick = solveMemo(i, j + 1, a, b, dp);
        long long pick = solveMemo(i + 1, j + 1, a, b, dp);

        if (pick != LLONG_MIN) {
            pick += (long long) a[i] * b[j];
        }

        return dp[i][j] = max(notPick, pick);
    }

    // -----------------------------
    // Approach 3 + 4: Space Optimised DP
    // -----------------------------
    long long maxScore(vector<int>& a, vector<int>& b) {
        int n = b.size();
        vector<long long> prev(n + 1, 0);

        for (int i = 1; i <= 4; i++) {
            vector<long long> curr(n + 1, LLONG_MIN);

            for (int j = 1; j <= n; j++) {
                long long notPick = curr[j - 1];
                long long pick = prev[j - 1];

                if (pick != LLONG_MIN) {
                    pick += (long long) a[i - 1] * b[j - 1];
                }

                curr[j] = max(notPick, pick);
            }
            prev = curr;
        }

        return prev[n];
    }
};
