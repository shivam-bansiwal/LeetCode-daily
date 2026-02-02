// LeetCode 3013. Divide an Array Into Subarrays With Minimum Cost II
// Time Complexity: O(n log k)
// Space Complexity: O(k)

import java.util.*;

class Solution {

    class Pair {
        int num;
        int idx;

        Pair(int n, int i) {
            num = n;
            idx = i;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair other = (Pair) o;
            return num == other.num && idx == other.idx;
        }

        @Override
        public int hashCode() {
            return 31 * num + idx;
        }
    }

    public long minimumCost(int[] nums, int k, int dist) {
        int n = nums.length;

        // We choose k-1 elements from the sliding window
        int K = k - 1;

        long minCost = Long.MAX_VALUE;
        long sum = 0;

        // maxHeap: stores the K smallest elements (largest among them on top)
        TreeSet<Pair> maxHeap = new TreeSet<>((a, b) -> {
            if (a.num == b.num) return Integer.compare(a.idx, b.idx);
            return Integer.compare(b.num, a.num);
        });

        // minHeapExtras: remaining elements in window
        TreeSet<Pair> minHeapExtras = new TreeSet<>((a, b) -> {
            if (a.num == b.num) return Integer.compare(a.idx, b.idx);
            return Integer.compare(a.num, b.num);
        });

        int left = 1, right = 1;

        // Initialize first window [1 .. dist + 1]
        while (right < n && right <= dist + 1) {
            Pair p = new Pair(nums[right], right);
            maxHeap.add(p);
            sum += nums[right];

            if (maxHeap.size() > K) {
                Pair largest = maxHeap.first();
                maxHeap.remove(largest);
                sum -= largest.num;
                minHeapExtras.add(largest);
            }
            right++;
        }

        minCost = Math.min(minCost, sum);

        // Slide the window
        while (right < n) {
            Pair incoming = new Pair(nums[right], right);

            if (maxHeap.size() < K) {
                maxHeap.add(incoming);
                sum += incoming.num;
            } else if (!maxHeap.isEmpty() && incoming.num < maxHeap.first().num) {
                Pair largest = maxHeap.first();
                maxHeap.remove(largest);
                sum -= largest.num;

                maxHeap.add(incoming);
                sum += incoming.num;

                minHeapExtras.add(largest);
            } else {
                minHeapExtras.add(incoming);
            }

            Pair outgoing = new Pair(nums[left], left);

            if (maxHeap.remove(outgoing)) {
                sum -= outgoing.num;
                if (!minHeapExtras.isEmpty()) {
                    Pair smallestExtra = minHeapExtras.first();
                    minHeapExtras.remove(smallestExtra);
                    maxHeap.add(smallestExtra);
                    sum += smallestExtra.num;
                }
            } else {
                minHeapExtras.remove(outgoing);
            }

            if (maxHeap.size() == K) {
                minCost = Math.min(minCost, sum);
            }

            left++;
            right++;
        }

        return nums[0] + minCost;
    }
}
