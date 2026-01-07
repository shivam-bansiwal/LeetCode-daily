// LeetCode 11. Container With Most Water
// Time Complexity:
//  - Approach 1 (Two Pointers): O(n)
//  - Approach 2 (Brute Force): O(n^2)
// Space Complexity: O(1)

class Solution {

    // --------------------------------------------------
    // Approach 1: Two Pointers (Optimal)
    // --------------------------------------------------
    public int maxArea(int[] height) {
        int n = height.length;
        int left = 0, right = n - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int currArea = Math.min(height[left], height[right]) * width;
            maxArea = Math.max(maxArea, currArea);

            // Move the pointer with smaller height
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxArea;
    }

    // --------------------------------------------------
    // Approach 2: Brute Force (TLE, for understanding)
    // --------------------------------------------------
    public int maxAreaBruteForce(int[] height) {
        int maxArea = 0;

        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int width = j - i;
                int currArea = Math.min(height[i], height[j]) * width;
                maxArea = Math.max(maxArea, currArea);
            }
        }

        return maxArea;
    }
}
