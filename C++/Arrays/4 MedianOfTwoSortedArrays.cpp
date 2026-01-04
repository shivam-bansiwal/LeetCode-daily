// LeetCode 4. Median of Two Sorted Arrays
// Time Complexity: O(log(min(n1, n2)))
// Space Complexity: O(1)

#include <vector>
#include <climits>
using namespace std;

class Solution {
public:
    double findMedianSortedArrays(vector<int>& nums1, vector<int>& nums2) {
        int n1 = nums1.size();
        int n2 = nums2.size();

        // Ensure nums1 is the smaller array
        if (n1 > n2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int low = 0, high = n1;
        int totalLeft = (n1 + n2 + 1) >> 1;

        while (low <= high) {
            int mid1 = low + (high - low) / 2;
            int mid2 = totalLeft - mid1;

            int l1 = (mid1 == 0) ? INT_MIN : nums1[mid1 - 1];
            int l2 = (mid2 == 0) ? INT_MIN : nums2[mid2 - 1];
            int r1 = (mid1 == n1) ? INT_MAX : nums1[mid1];
            int r2 = (mid2 == n2) ? INT_MAX : nums2[mid2];

            // Correct partition
            if (l1 <= r2 && l2 <= r1) {
                if (((n1 + n2) & 1) == 1) {
                    return max(l1, l2);
                }
                return (max(l1, l2) + min(r1, r2)) / 2.0;
            }
            else if (l1 > r2) {
                high = mid1 - 1;
            }
            else {
                low = mid1 + 1;
            }
        }

        return 0.0; // unreachable
    }
};
