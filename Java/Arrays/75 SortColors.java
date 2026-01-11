// LeetCode 75. Sort Colors
// Time Complexity: O(n)
// Space Complexity: O(1)

class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;

        int low = 0, mid = 0, high = n - 1;

        // Dutch National Flag Algorithm
        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } 
            else if (nums[mid] == 1) {
                mid++;
            } 
            else { // nums[mid] == 2
                swap(nums, mid, high);
                high--;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
