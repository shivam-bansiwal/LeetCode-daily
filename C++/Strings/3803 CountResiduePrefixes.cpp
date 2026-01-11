// LeetCode 3803. Count Residue Prefixes
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    int residuePrefixes(string s) {
        vector<bool> seen(26, false);
        int distinct = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            // Stop if more than 3 distinct characters
            if (distinct > 3) {
                break;
            }

            int idx = s[i] - 'a';
            if (!seen[idx]) {
                seen[idx] = true;
                distinct++;
            }

            // Residue condition
            if (distinct == (i + 1) % 3) {
                count++;
            }
        }

        return count;
    }
};
