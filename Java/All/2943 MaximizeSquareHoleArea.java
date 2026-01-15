// LeetCode 2943. Maximize Area of Square Hole in Grid
// Time Complexity: O(h log h + v log v)
// Space Complexity: O(1) extra

import java.util.Arrays;

class Solution {

    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        // Sort bars to find longest consecutive segments
        Arrays.sort(hBars);
        Arrays.sort(vBars);

        // Max consecutive bars determine hole dimensions
        int maxHeight = longestConsecutive(hBars);
        int maxWidth  = longestConsecutive(vBars);

        // Square side is the smaller of width and height
        int side = Math.min(maxHeight, maxWidth);

        return side * side;
    }

    // Finds longest consecutive bar sequence + boundaries
    private int longestConsecutive(int[] bars) {
        int longest = 2; // minimum hole size
        int curr = 2;
        int prev = 1;

        for (int bar : bars) {
            if (bar - prev == 1) {
                curr++;
                longest = Math.max(longest, curr);
            } else {
                curr = 2;
            }
            prev = bar;
        }

        return longest;
    }
}
