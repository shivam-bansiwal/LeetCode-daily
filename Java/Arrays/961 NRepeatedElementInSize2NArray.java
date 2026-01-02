// LeetCode 961. N-Repeated Element in Size 2N Array
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;

        // Starting from index 2, compare current element
        // with previous two elements.
        // In a 2N array with one element repeated N times,
        // the repeated element must appear within distance <= 2.
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }

        // Edge case: if not found in loop,
        // the repeated element must be the last one
        return nums[n - 1];
    }
}
