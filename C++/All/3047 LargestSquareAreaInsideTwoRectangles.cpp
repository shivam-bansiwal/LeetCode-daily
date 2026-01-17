// LeetCode 3047. Find the Largest Area of Square Inside Two Rectangles
// Time Complexity: O(n^2)
// Space Complexity: O(1)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    long long largestSquareArea(vector<vector<int>>& bottomLeft,
                                vector<vector<int>>& topRight) {
        int n = bottomLeft.size();
        long long maxSide = 0;

        for (int i = 0; i < n; i++) {
            int x1 = bottomLeft[i][0];
            int y1 = bottomLeft[i][1];
            int x2 = topRight[i][0];
            int y2 = topRight[i][1];

            for (int j = i + 1; j < n; j++) {
                int x3 = bottomLeft[j][0];
                int y3 = bottomLeft[j][1];
                int x4 = topRight[j][0];
                int y4 = topRight[j][1];

                long long width = min(x2, x4) - max(x1, x3);
                long long height = min(y2, y4) - max(y1, y3);

                if (width > 0 && height > 0) {
                    long long side = min(width, height);
                    maxSide = max(maxSide, side);
                }
            }
        }

        return maxSide * maxSide;
    }
};
