// LeetCode 1390. Four Divisors
// Time Complexity: O(n * sqrt(m)), where m is the value of nums[i]
// Space Complexity: O(1)

#include <vector>
using namespace std;

class Solution {
public:
    int sumFourDivisors(vector<int>& nums) {
        int totalSum = 0;

        for (int num : nums) {
            totalSum += getFourDivisorsSum(num);
        }

        return totalSum;
    }

private:
    int getFourDivisorsSum(int num) {
        int divisorPairs = 0;
        int sum = num + 1; // 1 and num

        // Check divisors from 2 to sqrt(num)
        for (int i = 2; i * i <= num; i++) {
            // Perfect square case
            if (i * i == num) {
                return 0;
            }

            if (num % i == 0) {
                divisorPairs++;
                sum += i;
                sum += num / i;
            }

            // More than one pair means >4 divisors
            if (divisorPairs > 1) {
                return 0;
            }
        }

        // Valid only if exactly one divisor pair exists
        return divisorPairs == 1 ? sum : 0;
    }
};
