// LeetCode 3. Longest Substring Without Repeating Characters
// Time Complexity: O(n)
// Space Complexity: O(1) — fixed size ASCII array

import java.util.Arrays;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[256]; // ASCII characters
        Arrays.fill(lastSeen, -1);

        int left = 0;
        int maxLen = 0;

        // Sliding window with two pointers
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // If character was seen inside the current window,
            // move the left pointer
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }

            lastSeen[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}
