// LeetCode 3713. Longest Balanced Substring I
// Time Complexity: O(n^2 * 26) ~= O(n^2)
// Space Complexity: O(26) ~= O(1)

#include <string>
#include <algorithm>
using namespace std;

class Solution {
public:
    int longestBalanced(string s) {
        int n = (int)s.size();
        int longestLen = 0;

        for (int i = 0; i < n; i++) {
            if (longestLen >= n - i) break;

            int freq[26] = {0};

            for (int j = i; j < n; j++) {
                freq[s[j] - 'a']++;

                if (isBalanced(freq)) {
                    longestLen = max(longestLen, j - i + 1);
                }
            }
        }

        return longestLen;
    }

private:
    bool isBalanced(int freq[26]) {
        int base = 0;

        for (int i = 0; i < 26; i++) {
            if (freq[i] == 0) continue;

            if (base == 0) base = freq[i];
            else if (freq[i] != base) return false;
        }

        return true;
    }
};
