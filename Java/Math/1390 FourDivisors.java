// LeetCode 1390. Four Divisors
// Time Complexity: O(n * sqrt(m)), where m is the value of nums[i]
// Space Complexity: O(1)

class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += getFourDivisorsSum(num);
        }

        return totalSum;
    }

    private int getFourDivisorsSum(int num) {
        int divisorPairs = 0;
        int sum = num + 1; // 1 and the number itself

        // Check divisors from 2 to sqrt(num)
        for (int i = 2; i * i <= num; i++) {
            // Perfect square → odd number of divisors, not valid
            if (i * i == num) {
                return 0;
            }

            if (num % i == 0) {
                divisorPairs++;
                sum += i;
                sum += num / i;
            }

            // More than one divisor pair → more than 4 divisors
            if (divisorPairs > 1) {
                return 0;
            }
        }

        // Exactly one divisor pair → exactly four divisors
        return divisorPairs == 1 ? sum : 0;
    }
}
