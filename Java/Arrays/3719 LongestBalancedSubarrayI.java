// LeetCode 3719. Longest Balanced Subarray I
// Time Complexity: O(n^2)
// Space Complexity: O(n)

import java.util.HashSet;
import java.util.Set;

class Solution {
    public int longestBalanced(int[] nums) {
        int n = nums.length;
        int longest = 0;

        Set<Integer> seen = new HashSet<>();

        for (int l = 0; l < n; l++) {
            int oddCount = 0, evenCount = 0;
            seen.clear();

            for (int r = l; r < n; r++) {
                int num = nums[r];

                // Only count a number once within the current subarray scan
                if (seen.add(num)) {
                    if ((num & 1) == 1) oddCount++;
                    else evenCount++;
                }

                if (oddCount == evenCount) {
                    longest = Math.max(longest, r - l + 1);
                }
            }

            // Early exit: can't beat current best if remaining length <= longest
            if (n - l <= longest) break;
        }

        return longest;
    }
}
