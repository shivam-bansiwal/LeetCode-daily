// LeetCode 3379. Transformed Array
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <vector>
using namespace std;

class Solution {
public:
    vector<int> constructTransformedArray(vector<int>& nums) {
        int n = nums.size();
        vector<int> res(n);

        for (int i = 0; i < n; i++) {
            int shift = nums[i] % n;
            int j = (i + shift) % n;
            if (j < 0) j += n;
            res[i] = nums[j];
        }

        return res;
    }
};
