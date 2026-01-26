// LeetCode 1200. Minimum Absolute Difference
// Time Complexity: O(n log n)
// Space Complexity: O(1) (excluding output)

import java.util.*;

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> res = new ArrayList<>();

        int n = arr.length;
        int diff = Integer.MAX_VALUE;

        // Find minimum absolute difference
        for (int i = 1; i < n; i++) {
            diff = Math.min(diff, arr[i] - arr[i - 1]);
        }

        // Collect all pairs with that difference
        for (int i = 1; i < n; i++) {
            if (arr[i] - arr[i - 1] == diff) {
                res.add(List.of(arr[i - 1], arr[i]));
            }
        }

        return res;
    }
}
