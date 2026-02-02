// LeetCode 744. Find Smallest Letter Greater Than Target
// Time Complexity: O(log n)
// Space Complexity: O(1)

class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, r = letters.length - 1;
        int ans = 0; // default wraps around to first character

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (letters[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return letters[ans];
    }
}
