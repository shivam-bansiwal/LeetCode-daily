// LeetCode 3315. Construct the Minimum Bitwise Array II
// Time Complexity: O(n * 32) ≈ O(n)
// Space Complexity: O(n)

#include <vector>
using namespace std;

class Solution {
public:
    vector<int> minBitwiseArray(vector<int>& nums) {
        vector<int> ans(nums.size());
        int idx = 0;

        for (int num : nums) {

            // Special case
            if (num == 2) {
                ans[idx++] = -1;
                continue;
            }

            int bitPos = 0;

            // Find first zero bit
            while (bitPos < 32 && (num & (1 << bitPos))) {
                bitPos++;
            }

            bitPos--; // flip the last 1 bit
            ans[idx++] = num ^ (1 << bitPos);
        }

        return ans;
    }
};
