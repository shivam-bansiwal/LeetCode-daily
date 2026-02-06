// LeetCode 3634. Minimum Removals to Balance Array
// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding sorting space)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minRemoval(vector<int>& nums, int k) {
        int n = nums.size();
        if (n == 1) return 0;

        sort(nums.begin(), nums.end());

        int maxSize = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {
            while ((long long)nums[r] > (long long)nums[l] * k) {
                l++;
            }
            maxSize = max(maxSize, r - l + 1);
        }

        return n - maxSize;
    }
};
