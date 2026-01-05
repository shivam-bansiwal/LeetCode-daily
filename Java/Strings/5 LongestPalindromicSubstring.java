// LeetCode 5. Longest Palindromic Substring
// Time Complexity: O(n^2)
// Space Complexity: O(1)

class Solution {
    public String longestPalindrome(String s) {
        // max[0] = length of longest palindrome found
        // max[1] = left index
        // max[2] = right index
        int[] max = new int[3];

        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindromes (center at i)
            expandFromCenter(s, i, i, max);
            // Even-length palindromes (center between i and i+1)
            expandFromCenter(s, i, i + 1, max);
        }

        return s.substring(max[1], max[2] + 1);
    }

    private void expandFromCenter(String s, int left, int right, int[] max) {
        int count = 0;

        // Expand while valid palindrome
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count += (left == right) ? 1 : 2;

            if (count > max[0]) {
                max[0] = count;
                max[1] = left;
                max[2] = right;
            }

            left--;
            right++;
        }
    }
}
