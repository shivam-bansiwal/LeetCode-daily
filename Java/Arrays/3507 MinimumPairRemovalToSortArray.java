// LeetCode 3507. Minimum Pair Removal to Sort Array I
// Time Complexity: O(n^2) (worst case)
// Space Complexity: O(n)

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int minimumPairRemoval(int[] nums) {
        int n = nums.length;
        int ops = 0;

        List<Integer> ls = new ArrayList<>();
        for (int num : nums) {
            ls.add(num);
        }

        while (!isSorted(ls)) {
            int pos = minPairPosition(ls);
            ls.set(pos, ls.get(pos) + ls.get(pos + 1));
            ls.remove(pos + 1);
            ops++;
        }

        return ops;
    }

    private int minPairPosition(List<Integer> arr) {
        int minSum = (int) 1e9;
        int pos = -1;

        for (int i = 0; i < arr.size() - 1; i++) {
            int sum = arr.get(i) + arr.get(i + 1);
            if (sum < minSum) {
                minSum = sum;
                pos = i;
            }
        }
        return pos;
    }

    private static boolean isSorted(List<Integer> arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr.get(i - 1) > arr.get(i)) {
                return false;
            }
        }
        return true;
    }
}
