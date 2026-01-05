// LeetCode 5. Longest Palindromic Substring
// Time Complexity: O(n^2)
// Space Complexity: O(1)

#include <string>
using namespace std;

class Solution {
public:
    string longestPalindrome(string s) {
        // maxLen = length of longest palindrome
        // start, end = indices of longest palindrome
        int maxLen = 0;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            // Odd-length palindromes
            expandFromCenter(s, i, i, maxLen, start, end);
            // Even-length palindromes
            expandFromCenter(s, i, i + 1, maxLen, start, end);
        }

        return s.substr(start, end - start + 1);
    }

private:
    void expandFromCenter(string& s, int left, int right,
                          int& maxLen, int& start, int& end) {
        int count = 0;

        while (left >= 0 && right < s.length() && s[left] == s[right]) {
            count += (left == right) ? 1 : 2;

            if (count > maxLen) {
                maxLen = count;
                start = left;
                end = right;
            }

            left--;
            right++;
        }
    }
};
