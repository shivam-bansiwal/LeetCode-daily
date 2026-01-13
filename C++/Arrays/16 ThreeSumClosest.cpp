// LeetCode 16. 3Sum Closest
// Time Complexity: O(n^2)
// Space Complexity: O(1) extra (sorting aside)

#include <vector>
#include <algorithm>
#include <cstdlib>
using namespace std;

class Solution {
public:
    int threeSumClosest(vector<int>& nums, int target) {
        int n = nums.size();

        sort(nums.begin(), nums.end());

        int closestSum = nums[0] + nums[1] + nums[2];

        for (int k = 0; k < n - 2; k++) {
            int i = k + 1;
            int j = n - 1;

            while (i < j) {
                int sum = nums[k] + nums[i] + nums[j];

                if (abs(target - sum) < abs(target - closestSum)) {
                    closestSum = sum;
                }

                if (sum < target) {
                    i++;
                } else if (sum > target) {
                    j--;
                } else {
                    return sum; // exact match
                }
            }
        }

        return closestSum;
    }
};
