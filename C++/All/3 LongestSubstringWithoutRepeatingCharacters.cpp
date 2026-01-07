// LeetCode 3. Longest Substring Without Repeating Characters
// Time Complexity: O(n)
// Space Complexity: O(1) — fixed size ASCII array

#include <string>
#include <vector>
using namespace std;

class Solution {
public:
    int lengthOfLongestSubstring(string s) {
        vector<int> lastSeen(256, -1); // ASCII characters

        int left = 0;
        int maxLen = 0;

        // Sliding window
        for (int right = 0; right < s.length(); right++) {
            char c = s[right];

            // If character repeats within current window,
            // move left pointer
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }

            lastSeen[c] = right;
            maxLen = max(maxLen, right - left + 1);
        }

        return maxLen;
    }
};
