// LeetCode 3314. Construct the Minimum Bitwise Array I
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

            if (num == 2) {
                ans[idx++] = -1;
                continue;
            }

            int bit = 0;

            // Find first zero bit from LSB
            while (bit < 32 && (num & (1 << bit))) {
                bit++;
            }

            bit--; // flip the last 1 bit

            ans[idx++] = num ^ (1 << bit);
        }

        return ans;
    }
};
