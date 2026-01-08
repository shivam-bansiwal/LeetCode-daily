// LeetCode 17. Letter Combinations of a Phone Number
// Time Complexity: O(4^n)
// Space Complexity: O(n) — recursion stack

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        if (digits == null || digits.length() == 0) {
            return result;
        }

        String[] mapping = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
        };

        backtrack(result, new StringBuilder(), digits, 0, mapping);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current,
                           String digits, int index, String[] mapping) {

        // Base case: formed one complete combination
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        int digit = digits.charAt(index) - '0';

        for (char c : mapping[digit].toCharArray()) {
            current.append(c);
            backtrack(result, current, digits, index + 1, mapping);
            current.deleteCharAt(current.length() - 1); // backtrack
        }
    }
}
