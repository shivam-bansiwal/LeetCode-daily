// LeetCode 66. Plus One
// Time Complexity: O(n)
// Space Complexity: O(n) in worst case (when all digits are 9)

class Solution {
public:
    vector<int> plusOne(vector<int>& digits) {
        int n = digits.size();
        int carry = 1; // initial increment

        // Traverse from last digit to first
        for (int i = n - 1; i >= 0; i--) {
            // If digit is 9, it becomes 0 and carry continues
            if (digits[i] == 9) {
                digits[i] = 0;
            }
            // Otherwise, increment digit and stop
            else {
                digits[i]++;
                carry = 0;
                break;
            }
        }

        // If no carry remains, return the same array
        if (carry == 0) {
            return digits;
        }

        // If all digits were 9, create a new array
        vector<int> result(n + 1, 0);
        result[0] = 1;
        return result;
    }
};
