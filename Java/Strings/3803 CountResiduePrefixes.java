// LeetCode 3803. Count Residue Prefixes
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public int residuePrefixes(String s) {
        boolean[] seen = new boolean[26];
        int distinct = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // More than 3 distinct characters cannot satisfy condition
            if (distinct > 3) {
                break;
            }

            int idx = s.charAt(i) - 'a';
            if (!seen[idx]) {
                seen[idx] = true;
                distinct++;
            }

            // Check residue condition
            if (distinct == (i + 1) % 3) {
                count++;
            }
        }

        return count;
    }
}
