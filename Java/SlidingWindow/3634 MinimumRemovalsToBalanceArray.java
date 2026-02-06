// LeetCode 3634. Minimum Removals to Balance Array
// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding sorting space)

import java.util.Arrays;

class Solution {
    public int minRemoval(int[] nums, int k) {
        int n = nums.length;
        if (n == 1) return 0;

        Arrays.sort(nums);

        int maxSize = 0;
        int l = 0;

        for (int r = 0; r < n; r++) {
            // Maintain nums[r] <= nums[l] * k
            while ((long) nums[r] > (long) nums[l] * k) {
                l++;
            }
            maxSize = Math.max(maxSize, r - l + 1);
        }

        return n - maxSize;
    }
}
