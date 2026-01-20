// LeetCode 239. Sliding Window Maximum
// Time Complexity: O(n)
// Space Complexity: O(k)

#include <vector>
#include <deque>
using namespace std;

class Solution {
public:
    vector<int> maxSlidingWindow(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> res(n - k + 1);
        deque<int> dq; // stores indices
        int left = 0;

        for (int right = 0; right < n; right++) {

            // Remove indices outside the window
            if (!dq.empty() && dq.front() < right - k + 1) {
                dq.pop_front();
            }

            // Maintain decreasing order
            while (!dq.empty() && nums[dq.back()] <= nums[right]) {
                dq.pop_back();
            }

            dq.push_back(right);

            // Record result once window is valid
            if (right >= k - 1) {
                res[left++] = nums[dq.front()];
            }
        }

        return res;
    }
};
