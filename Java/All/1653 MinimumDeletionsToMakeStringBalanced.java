// LeetCode 1653. Minimum Deletions to Make String Balanced
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();

        int aCount = 0;
        int bSeen = 0;
        int minDel = n;

        // Count total 'a'
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') aCount++;
        }

        // Try split point at each index
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'a') {
                aCount--; // 'a' moves from right side to left processed side
            }

            // Delete remaining 'a' on right + delete 'b' on left
            minDel = Math.min(minDel, aCount + bSeen);

            if (s.charAt(i) == 'b') {
                bSeen++;
            }
        }

        return minDel;
    }
}
