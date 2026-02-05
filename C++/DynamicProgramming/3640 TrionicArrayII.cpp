// LeetCode 3640. Trionic Array II
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
    static constexpr long long NEG_INF = -100000000000000LL; // -1e14

public:
    long long maxSumTrionic(vector<int>& nums) {
        int n = (int)nums.size();
        vector<vector<long long>> dp(n + 1, vector<long long>(4, NEG_INF));

        dp[n][3] = 0;

        for (int i = n - 1; i >= 0; i--) {
            for (int slope = 0; slope < 4; slope++) {

                long long skip = NEG_INF;
                long long take = NEG_INF;

                if (slope == 0) {
                    skip = dp[i + 1][0];
                }

                if (slope == 3) {
                    take = (long long)nums[i] + dp[i + 1][3];
                }

                if (i + 1 < n) {
                    int curr = nums[i];
                    int next = nums[i + 1];

                    if (slope == 0) {
                        if (curr < next) take = max(take, (long long)curr + dp[i + 1][1]);
                    } else if (slope == 1) {
                        if (curr < next) take = max(take, (long long)curr + dp[i + 1][1]);
                        else if (curr > next) take = max(take, (long long)curr + dp[i + 1][2]);
                    } else if (slope == 2) {
                        if (curr > next) take = max(take, (long long)curr + dp[i + 1][2]);
                        else if (curr < next) take = max(take, (long long)curr + dp[i + 1][3]);
                    } else { // slope == 3
                        if (curr < next) take = max(take, (long long)curr + dp[i + 1][3]);
                    }
                }

                dp[i][slope] = max(skip, take);
            }
        }

        return dp[0][0];
    }
};
