// LeetCode 3010. Divide an Array Into Subarrays With Minimum Cost I
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int minimumCost(int[] nums) {
        int n = nums.length;

        // If only three elements, take all
        if (n == 3) {
            return nums[0] + nums[1] + nums[2];
        }

        int first = nums[0];

        // Track two smallest values from index 1 onward
        int second = Math.min(nums[1], nums[2]);
        int third  = Math.max(nums[1], nums[2]);

        for (int i = 3; i < n; i++) {
            if (nums[i] <= second) {
                third = second;
                second = nums[i];
            } else if (nums[i] < third) {
                third = nums[i];
            }
        }

        return first + second + third;
    }
}
