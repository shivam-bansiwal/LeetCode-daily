// LeetCode 560. Subarray Sum Equals K
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    int subarraySum(vector<int>& nums, int k) {
        int count = 0;
        int prefixSum = 0;

        // prefixSum -> frequency
        unordered_map<int, int> mp;
        mp[0] = 1;

        for (int num : nums) {
            prefixSum += num;

            int required = prefixSum - k;
            if (mp.find(required) != mp.end()) {
                count += mp[required];
            }

            mp[prefixSum]++;
        }

        return count;
    }
};
