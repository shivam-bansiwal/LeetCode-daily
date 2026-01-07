// LeetCode 11. Container With Most Water
// Time Complexity:
//  - Approach 1 (Two Pointers): O(n)
//  - Approach 2 (Brute Force): O(n^2)
// Space Complexity: O(1)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    // --------------------------------------------------
    // Approach 1: Two Pointers (Optimal)
    // --------------------------------------------------
    int maxArea(vector<int>& height) {
        int left = 0, right = height.size() - 1;
        int maxArea = 0;

        while (left < right) {
            int width = right - left;
            int currArea = min(height[left], height[right]) * width;
            maxArea = max(maxArea, currArea);

            // Move pointer with smaller height
            if (height[left] > height[right]) {
                right--;
            } else {
                left++;
            }
        }

        return maxArea;
    }

    // --------------------------------------------------
    // Approach 2: Brute Force (TLE, for learning)
    // --------------------------------------------------
    int maxAreaBruteForce(vector<int>& height) {
        int maxArea = 0;
        int n = height.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int width = j - i;
                int currArea = min(height[i], height[j]) * width;
                maxArea = max(maxArea, currArea);
            }
        }

        return maxArea;
    }
};
