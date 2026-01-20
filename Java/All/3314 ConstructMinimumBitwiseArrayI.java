// LeetCode 3314. Construct the Minimum Bitwise Array I
// Time Complexity: O(n * 32) ≈ O(n)
// Space Complexity: O(n)

import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] ans = new int[nums.size()];
        int idx = 0;

        for (int num : nums) {

            // Special case
            if (num == 2) {
                ans[idx++] = -1;
                continue;
            }

            int bit = 0;

            // Find first 0 bit from LSB
            while (bit < 32 && ((num & (1 << bit)) != 0)) {
                bit++;
            }

            // Flip the previous bit (rightmost 1 before first 0)
            bit--;

            ans[idx++] = num ^ (1 << bit);
        }

        return ans;
    }
}
