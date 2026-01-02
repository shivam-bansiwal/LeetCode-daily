// LeetCode 1. Two Sum
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <vector>
#include <unordered_map>
using namespace std;

class Solution {
public:
    vector<int> twoSum(vector<int>& nums, int target) {
        // Map to store number -> index
        unordered_map<int, int> numIndices;

        for (int i = 0; i < nums.size(); i++) {
            int complement = target - nums[i];

            // If complement exists, return the indices
            if (numIndices.find(complement) != numIndices.end()) {
                return {numIndices[complement], i};
            }

            // Store current number with its index
            numIndices[nums[i]] = i;
        }

        // As per problem constraints, this line is not expected to be reached
        return {-1, -1};
    }
};
