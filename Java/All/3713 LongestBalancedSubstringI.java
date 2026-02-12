// LeetCode 3713. Longest Balanced Substring I
// Time Complexity: O(n^2 * 26) ~= O(n^2)
// Space Complexity: O(26) ~= O(1)

class Solution {
    public int longestBalanced(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        int longestLen = 0;

        for (int i = 0; i < n; i++) {
            // Can't beat current best if remaining length is too small
            if (longestLen >= n - i) break;

            int[] freq = new int[26];

            for (int j = i; j < n; j++) {
                freq[arr[j] - 'a']++;

                if (isBalanced(freq)) {
                    longestLen = Math.max(longestLen, j - i + 1);
                }
            }
        }

        return longestLen;
    }

    // Balanced = all present letters have the same frequency
    private static boolean isBalanced(int[] freq) {
        int base = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            if (base == 0) base = freq[i];
            else if (freq[i] != base) return false;
        }

        return true;
    }
}
