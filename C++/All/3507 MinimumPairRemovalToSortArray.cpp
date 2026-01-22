// LeetCode 3507. Minimum Pair Removal to Sort Array I
// Time Complexity: O(n^2) (worst case)
// Space Complexity: O(n)

#include <vector>
using namespace std;

class Solution {
public:
    int minimumPairRemoval(vector<int>& nums) {
        vector<int> arr(nums.begin(), nums.end());
        int ops = 0;

        while (!isSorted(arr)) {
            int pos = minPairPosition(arr);
            arr[pos] = arr[pos] + arr[pos + 1];
            arr.erase(arr.begin() + pos + 1);
            ops++;
        }

        return ops;
    }

private:
    int minPairPosition(vector<int>& arr) {
        int minSum = 1e9;
        int pos = -1;

        for (int i = 0; i < arr.size() - 1; i++) {
            int sum = arr[i] + arr[i + 1];
            if (sum < minSum) {
                minSum = sum;
                pos = i;
            }
        }
        return pos;
    }

    bool isSorted(vector<int>& arr) {
        for (int i = 1; i < arr.size(); i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }
};
