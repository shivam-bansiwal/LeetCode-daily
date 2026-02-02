// LeetCode 744. Find Smallest Letter Greater Than Target
// Time Complexity: O(log n)
// Space Complexity: O(1)

#include <vector>
using namespace std;

class Solution {
public:
    char nextGreatestLetter(vector<char>& letters, char target) {
        int l = 0, r = letters.size() - 1;
        int ans = 0; // wrap-around index

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (letters[m] > target) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }

        return letters[ans];
    }
};
