// LeetCode 1877. Minimize Maximum Pair Sum in Array
// Time Complexity: O(n log n) due to sorting
// Space Complexity: O(1) extra space (sorting in-place)

import java.util.Arrays;

class Solution {
    public int minPairSum(int[] nums) {
        // Sort the array to pair smallest with largest
        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        int maxPairSum = 0;

        // Pair elements from both ends and track the maximum pair sum
        while (left < right) {
            int currentPairSum = nums[left] + nums[right];
            maxPairSum = Math.max(maxPairSum, currentPairSum);

            left++;
            right--;
        }

        return maxPairSum;
    }
}
