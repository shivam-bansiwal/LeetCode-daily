// LeetCode 1984. Minimum Difference Between Highest and Lowest of K Scores
// Time Complexity: O(n log n) due to sorting
// Space Complexity: O(1) extra space (sorting in-place)

import java.util.Arrays;

class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n = nums.length;

        // If only one score is selected, difference is always zero
        if (k == 1) {
            return 0;
        }

        // Sort the scores to evaluate consecutive windows of size k
        Arrays.sort(nums);

        int minDifference = Integer.MAX_VALUE;
        int left = 0;

        // Sliding window of size k over the sorted array
        for (int right = k - 1; right < n; right++) {
            minDifference = Math.min(minDifference, nums[right] - nums[left]);
            left++;

            // Early exit if minimum possible difference is found
            if (minDifference == 0) {
                break;
            }
        }

        return minDifference;
    }
}
