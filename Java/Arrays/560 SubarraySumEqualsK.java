// LeetCode 560. Subarray Sum Equals K
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        int prefixSum = 0;

        // Map to store prefixSum -> frequency
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // Base case: prefix sum 0 occurs once

        for (int num : nums) {
            prefixSum += num;

            // Check if there exists a prefixSum such that
            // currentSum - previousSum = k
            int required = prefixSum - k;
            count += map.getOrDefault(required, 0);

            // Store current prefix sum
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
