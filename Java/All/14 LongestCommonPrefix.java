// LeetCode 14. Longest Common Prefix
// Time Complexity:
//  - Approach 1 (Prefix Shrinking): O(n * m)
//  - Approach 2 (Sorting): O(n * m * log n)
// Space Complexity: O(1)

import java.util.Arrays;

class Solution {

    // --------------------------------------------------
    // Approach 1: Prefix Shrinking (Optimal & Intuitive)
    // --------------------------------------------------
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        String prefix = strs[0];
        int prefixLen = prefix.length();

        for (int i = 1; i < strs.length; i++) {
            String s = strs[i];

            // Shrink prefix until it matches current string
            while (prefixLen > s.length()
                    || !prefix.equals(s.substring(0, prefixLen))) {

                prefixLen--;
                if (prefixLen == 0) {
                    return "";
                }
                prefix = prefix.substring(0, prefixLen);
            }
        }

        return prefix;
    }

    // --------------------------------------------------
    // Approach 2: Sorting + Compare First & Last
    // --------------------------------------------------
    public String longestCommonPrefixSorting(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length - 1];

        int minLen = Math.min(first.length(), last.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < minLen; i++) {
            if (first.charAt(i) != last.charAt(i)) {
                break;
            }
            sb.append(first.charAt(i));
        }

        return sb.toString();
    }
}
