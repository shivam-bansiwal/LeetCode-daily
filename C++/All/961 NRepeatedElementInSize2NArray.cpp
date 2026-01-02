// LeetCode 961. N-Repeated Element in Size 2N Array
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
public:
    int repeatedNTimes(vector<int>& nums) {
        int n = nums.size();

        // Starting from index 2, compare current element
        // with the previous two elements.
        // The element repeated N times must appear within distance <= 2.
        for (int i = 2; i < n; i++) {
            if (nums[i] == nums[i - 1] || nums[i] == nums[i - 2]) {
                return nums[i];
            }
        }

        // Edge case: if not found in the loop,
        // the repeated element is the last element
        return nums[n - 1];
    }
};
