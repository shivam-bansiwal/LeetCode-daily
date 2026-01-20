// LeetCode 239. Sliding Window Maximum
// Time Complexity: O(n)
// Space Complexity: O(k)

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> dq = new ArrayDeque<>(); // stores indices
        int left = 0;

        for (int right = 0; right < n; right++) {

            // Remove indices outside the window
            if (!dq.isEmpty() && dq.peekFirst() < right - k + 1) {
                dq.pollFirst();
            }

            // Maintain decreasing order in deque
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[right]) {
                dq.pollLast();
            }

            dq.offerLast(right);

            // Record answer once the first window is formed
            if (right >= k - 1) {
                res[left++] = nums[dq.peekFirst()];
            }
        }

        return res;
    }
}
