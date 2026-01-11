// LeetCode 85. Maximal Rectangle
// Time Complexity: O(n * m)
// Space Complexity: O(m)

#include <vector>
#include <stack>
#include <algorithm>
using namespace std;

class Solution {
public:
    int maximalRectangle(vector<vector<char>>& matrix) {
        int n = matrix.size();
        int m = matrix[0].size();

        vector<int> heights(m, 0);
        int maxArea = 0;

        // Build histogram row by row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }

            maxArea = max(maxArea, largestRectangleArea(heights));
        }

        return maxArea;
    }

private:
    int largestRectangleArea(vector<int>& heights) {
        stack<int> st;
        int maxArea = 0;
        int n = heights.size();

        for (int i = 0; i < n; i++) {
            while (!st.empty() && heights[st.top()] > heights[i]) {
                int height = heights[st.top()];
                st.pop();
                int right = i;
                int left = st.empty() ? -1 : st.top();
                maxArea = max(maxArea, height * (right - left - 1));
            }
            st.push(i);
        }

        while (!st.empty()) {
            int height = heights[st.top()];
            st.pop();
            int right = n;
            int left = st.empty() ? -1 : st.top();
            maxArea = max(maxArea, height * (right - left - 1));
        }

        return maxArea;
    }
};
