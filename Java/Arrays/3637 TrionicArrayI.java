// LeetCode 3637. Trionic Array I
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;

        if (n < 4) return false;

        int i = 0;

        // Strictly increasing
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }

        // First peak must exist and not be too late
        if (i == 0 || i >= n - 2) return false;
        int p = i;

        // Strictly decreasing
        while (i < n - 1 && nums[i] > nums[i + 1]) {
            i++;
        }

        // Valley must exist
        if (i == p || i >= n - 1) return false;
        int q = i;

        // Strictly increasing again
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }

        // Must consume entire array
        return i == n - 1;
    }
}
