// LeetCode 3637. Trionic Array I
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <vector>
using namespace std;

class Solution {
public:
    bool isTrionic(vector<int>& nums) {
        int n = nums.size();
        if (n < 4) return false;

        int i = 0;

        // Strictly increasing
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }

        if (i == 0 || i >= n - 2) return false;
        int p = i;

        // Strictly decreasing
        while (i < n - 1 && nums[i] > nums[i + 1]) {
            i++;
        }

        if (i == p || i >= n - 1) return false;

        // Strictly increasing again
        while (i < n - 1 && nums[i] < nums[i + 1]) {
            i++;
        }

        return i == n - 1;
    }
};
