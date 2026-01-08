// LeetCode 17. Letter Combinations of a Phone Number
// Time Complexity: O(4^n)
// Space Complexity: O(n) — recursion stack

#include <vector>
#include <string>
using namespace std;

class Solution {
public:
    vector<string> letterCombinations(string digits) {
        vector<string> result;

        if (digits.empty()) {
            return result;
        }

        vector<string> mapping = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        string current;
        backtrack(result, current, digits, 0, mapping);
        return result;
    }

private:
    void backtrack(vector<string>& result, string& current,
                   string& digits, int index, vector<string>& mapping) {

        // Base case
        if (index == digits.length()) {
            result.push_back(current);
            return;
        }

        int digit = digits[index] - '0';

        for (char c : mapping[digit]) {
            current.push_back(c);
            backtrack(result, current, digits, index + 1, mapping);
            current.pop_back(); // backtrack
        }
    }
};
