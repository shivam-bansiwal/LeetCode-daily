// LeetCode 3010. Divide an Array Into Subarrays With Minimum Cost I
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int minimumCost(vector<int>& nums) {
        int n = nums.size();

        if (n == 3) {
            return nums[0] + nums[1] + nums[2];
        }

        int first = nums[0];

        int second = min(nums[1], nums[2]);
        int third  = max(nums[1], nums[2]);

        for (int i = 3; i < n; i++) {
            if (nums[i] <= second) {
                third = second;
                second = nums[i];
            } else if (nums[i] < third) {
                third = nums[i];
            }
        }

        return first + second + third;
    }
};
