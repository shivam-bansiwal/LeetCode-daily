// LeetCode 16. 3Sum Closest
// Time Complexity: O(n^2)
// Space Complexity: O(1) extra (sorting aside)

import java.util.Arrays;

class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int n = nums.length;

        // Sort array for two-pointer technique
        Arrays.sort(nums);

        // Initialise with a large value
        int closestSum = nums[0] + nums[1] + nums[2];

        for (int k = 0; k < n - 2; k++) {
            int i = k + 1;
            int j = n - 1;

            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];

                // Update closest sum if this is better
                if (Math.abs(target - sum) < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    i++;
                } else if (sum > target) {
                    j--;
                } else {
                    // Exact match
                    return sum;
                }
            }
        }

        return closestSum;
    }
}
