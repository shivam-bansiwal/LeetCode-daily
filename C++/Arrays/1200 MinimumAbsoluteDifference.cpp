// LeetCode 1200. Minimum Absolute Difference
// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding output)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    vector<vector<int>> minimumAbsDifference(vector<int>& arr) {
        sort(arr.begin(), arr.end());
        vector<vector<int>> res;

        int n = arr.size();
        int diff = INT_MAX;

        // Find minimum absolute difference
        for (int i = 1; i < n; i++) {
            diff = min(diff, arr[i] - arr[i - 1]);
        }

        // Collect all valid pairs
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] == diff) {
                res.push_back({arr[i - 1], arr[i]});
            }
        }

        return res;
    }
};
