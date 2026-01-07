// LeetCode 6. Zigzag Conversion
// Time Complexity: O(n)
// Space Complexity:
//  - Approach 1: O(1) extra (output excluded)
//  - Approach 2: O(n)

#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    // --------------------------------------------------
    // Approach 1: Cycle-based (Math / Index Jumping)
    // --------------------------------------------------
    string convert(string s, int numRows) {
        int n = s.length();

        if (numRows == 1 || numRows >= n) {
            return s;
        }

        string result;
        result.reserve(n);
        int cycle = (numRows - 1) * 2;

        for (int row = 0; row < numRows; row++) {
            for (int i = row; i < n; i += cycle) {
                result.push_back(s[i]);

                int diag = i + cycle - 2 * row;
                if (row > 0 && row < numRows - 1 && diag < n) {
                    result.push_back(s[diag]);
                }
            }
        }

        return result;
    }

    // --------------------------------------------------
    // Approach 2: Simulate Zigzag (Bounce / Direction)
    // --------------------------------------------------
    string convertBounce(string s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) {
            return s;
        }

        vector<string> rows(numRows);
        int row = 0;
        int dir = 1; // 1 = down, -1 = up

        for (char c : s) {
            rows[row].push_back(c);

            if (row == 0) {
                dir = 1;
            } else if (row == numRows - 1) {
                dir = -1;
            }

            row += dir;
        }

        string result;
        for (string& r : rows) {
            result += r;
        }

        return result;
    }
};
