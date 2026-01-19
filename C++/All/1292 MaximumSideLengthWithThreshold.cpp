// LeetCode 1292. Maximum Side Length of a Square with Sum <= Threshold
// Time Complexity: O(n * m * log(min(n, m)))
// Space Complexity: O(n * m)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maxSideLength(vector<vector<int>>& mat, int threshold) {
        int n = mat.size();
        int m = mat[0].size();

        vector<vector<int>> prefix(n + 1, vector<int>(m + 1, 0));

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1]
                             - prefix[i - 1][j - 1] + mat[i - 1][j - 1];
            }
        }

        int left = 1, right = min(n, m);
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (existsSquare(prefix, n, m, mid, threshold)) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return ans;
    }

private:
    bool existsSquare(vector<vector<int>>& prefix, int n, int m,
                      int size, int threshold) {
        for (int i = size; i <= n; i++) {
            for (int j = size; j <= m; j++) {
                int sum = prefix[i][j]
                        - prefix[i - size][j]
                        - prefix[i][j - size]
                        + prefix[i - size][j - size];

                if (sum <= threshold) return true;
            }
        }
        return false;
    }
};
