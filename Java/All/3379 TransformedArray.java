// LeetCode 3379. Transformed Array
// Time Complexity: O(n)
// Space Complexity: O(n)

class Solution {
    public int[] constructTransformedArray(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        for (int i = 0; i < n; i++) {
            int shift = nums[i] % n;           // can be negative in Java
            int j = (i + shift) % n;           // still can be negative
            if (j < 0) j += n;                 // normalise to [0, n-1]
            res[i] = nums[j];
        }

        return res;
    }
}
