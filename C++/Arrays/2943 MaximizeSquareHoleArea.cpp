// LeetCode 2943. Maximize Area of Square Hole in Grid
// Time Complexity: O(h log h + v log v)
// Space Complexity: O(1) extra

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximizeSquareHoleArea(int n, int m, vector<int>& hBars, vector<int>& vBars) {
        sort(hBars.begin(), hBars.end());
        sort(vBars.begin(), vBars.end());

        int maxHeight = longestConsecutive(hBars);
        int maxWidth  = longestConsecutive(vBars);

        int side = min(maxHeight, maxWidth);
        return side * side;
    }

private:
    int longestConsecutive(vector<int>& bars) {
        int longest = 2;
        int curr = 2;
        int prev = 1;

        for (int bar : bars) {
            if (bar - prev == 1) {
                curr++;
                longest = max(longest, curr);
            } else {
                curr = 2;
            }
            prev = bar;
        }

        return longest;
    }
};
