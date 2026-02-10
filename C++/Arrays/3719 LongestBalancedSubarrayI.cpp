// LeetCode 3719. Longest Balanced Subarray I
// Time Complexity: O(n^2)
// Space Complexity: O(n)

#include <vector>
#include <unordered_set>
using namespace std;

class Solution {
public:
    int longestBalanced(vector<int>& nums) {
        int n = (int)nums.size();
        int longest = 0;

        unordered_set<int> seen;

        for (int l = 0; l < n; l++) {
            int oddCount = 0, evenCount = 0;
            seen.clear();

            for (int r = l; r < n; r++) {
                int num = nums[r];

                if (seen.insert(num).second) {
                    if (num & 1) oddCount++;
                    else evenCount++;
                }

                if (oddCount == evenCount) {
                    longest = max(longest, r - l + 1);
                }
            }

            if (n - l <= longest) break;
        }

        return longest;
    }
};
