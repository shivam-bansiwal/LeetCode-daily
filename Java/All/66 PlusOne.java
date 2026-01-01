// LeetCode 66. Plus One
// Time Complexity: O(n)
// Space Complexity: O(n) in worst case (when all digits are 9)

class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1; // we need to add 1 initially

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

        // If no carry remains, return modified array
        if (carry == 0) {
            return digits;
        }

        // If carry still exists (all digits were 9),
        // create a new array with leading 1
        int[] result = new int[n + 1];
        result[0] = 1;
        return result;
    }
}
