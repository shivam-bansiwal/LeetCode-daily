// LeetCode 3315. Construct the Minimum Bitwise Array II
// Time Complexity: O(n * 32) ≈ O(n)
// Space Complexity: O(n)

import java.util.List;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int[] ans = new int[nums.size()];
        int idx = 0;

        for (int num : nums) {

            // Special case: no valid answer
            if (num == 2) {
                ans[idx++] = -1;
                continue;
            }

            int bitPos = 0;

            // Find first 0 bit from LSB
            while (bitPos < 32 && (num & (1 << bitPos)) != 0) {
                bitPos++;
            }

            // Move back to the last 1 bit
            bitPos--;

            // Flip that bit to get minimum possible value
            ans[idx++] = num ^ (1 << bitPos);
        }

        return ans;
    }
}
