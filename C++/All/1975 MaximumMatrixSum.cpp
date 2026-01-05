// LeetCode 1975. Maximum Matrix Sum
// Time Complexity: O(n^2)
// Space Complexity: O(1)

#include <vector>
#include <climits>
#include <cmath>
using namespace std;

class Solution {
public:
    long long maxMatrixSum(vector<vector<int>>& matrix) {
        int n = matrix.size();
        long long totalSum = 0;
        int smallestAbsValue = INT_MAX;
        int negativeCount = 0;
        bool hasZero = false;

        // Single pass through the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int val = matrix[i][j];
                totalSum += llabs(val);

                if (val == 0) {
                    hasZero = true;
                }
                if (val < 0) {
                    negativeCount++;
                }

                smallestAbsValue = min(smallestAbsValue, abs(val));
            }
        }

        // If even negatives or zero exists
        if (hasZero || negativeCount % 2 == 0) {
            return totalSum;
        }

        // Odd negatives: subtract twice the smallest absolute value
        return totalSum - 2LL * smallestAbsValue;
    }
};
