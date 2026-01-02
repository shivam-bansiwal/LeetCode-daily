// LeetCode 1. Two Sum
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Map to store number -> index
        Map<Integer, Integer> numIndices = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];

            // If complement exists, we have found the pair
            if (numIndices.containsKey(complement)) {
                return new int[]{numIndices.get(complement), i};
            }

            // Store current number with its index
            numIndices.put(nums[i], i);
        }

        // As per problem constraints, this line is not expected to be reached
        return new int[]{-1, -1};
    }
}
