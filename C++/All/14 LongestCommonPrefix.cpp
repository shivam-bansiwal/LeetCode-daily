// LeetCode 14. Longest Common Prefix
// Time Complexity:
//  - Approach 1 (Prefix Shrinking): O(n * m)
//  - Approach 2 (Sorting): O(n * m * log n)
// Space Complexity: O(1)

#include <vector>
#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    // --------------------------------------------------
    // Approach 1: Prefix Shrinking
    // --------------------------------------------------
    string longestCommonPrefix(vector<string>& strs) {
        if (strs.empty()) return "";

        string prefix = strs[0];
        int prefixLen = prefix.length();

        for (int i = 1; i < strs.size(); i++) {
            while (prefixLen > strs[i].length()
                   || prefix != strs[i].substr(0, prefixLen)) {

                prefixLen--;
                if (prefixLen == 0) {
                    return "";
                }
                prefix = prefix.substr(0, prefixLen);
            }
        }

        return prefix;
    }

    // --------------------------------------------------
    // Approach 2: Sorting
    // --------------------------------------------------
    string longestCommonPrefixSorting(vector<string>& strs) {
        if (strs.empty()) return "";

        sort(strs.begin(), strs.end());
        string first = strs.front();
        string last = strs.back();

        int minLen = min(first.length(), last.length());
        string result = "";

        for (int i = 0; i < minLen; i++) {
            if (first[i] != last[i]) break;
            result.push_back(first[i]);
        }

        return result;
    }
};
