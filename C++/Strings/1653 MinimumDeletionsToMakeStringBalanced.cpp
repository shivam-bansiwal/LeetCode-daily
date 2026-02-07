// LeetCode 1653. Minimum Deletions to Make String Balanced
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <string>
using namespace std;

class Solution {
public:
    int minimumDeletions(string s) {
        int n = s.size();

        int aCount = 0;
        int bSeen = 0;
        int minDel = n;

        for (char c : s) {
            if (c == 'a') aCount++;
        }

        for (char c : s) {
            if (c == 'a') aCount--;

            minDel = min(minDel, aCount + bSeen);

            if (c == 'b') bSeen++;
        }

        return minDel;
    }
};
