// LeetCode 3013. Divide an Array Into Subarrays With Minimum Cost II
// Time Complexity: O(n log k)
// Space Complexity: O(k)

#include <vector>
#include <set>
using namespace std;

class Solution {
    struct Pair {
        int num, idx;
        bool operator<(const Pair& other) const {
            if (num == other.num) return idx < other.idx;
            return num < other.num;
        }
    };

public:
    long long minimumCost(vector<int>& nums, int k, int dist) {
        int n = nums.size();
        int K = k - 1;

        long long minCost = LLONG_MAX;
        long long sum = 0;

        // maxHeap: K smallest elements (largest among them at end)
        multiset<Pair> maxHeap;
        // minHeapExtras: remaining elements
        multiset<Pair> minHeapExtras;

        int left = 1, right = 1;

        // Initial window
        while (right < n && right <= dist + 1) {
            Pair p{nums[right], right};
            maxHeap.insert(p);
            sum += p.num;

            if ((int)maxHeap.size() > K) {
                auto it = prev(maxHeap.end());
                sum -= it->num;
                minHeapExtras.insert(*it);
                maxHeap.erase(it);
            }
            right++;
        }

        minCost = min(minCost, sum);

        // Sliding window
        while (right < n) {
            Pair incoming{nums[right], right};

            if ((int)maxHeap.size() < K) {
                maxHeap.insert(incoming);
                sum += incoming.num;
            } else {
                auto it = prev(maxHeap.end());
                if (incoming.num < it->num) {
                    sum -= it->num;
                    minHeapExtras.insert(*it);
                    maxHeap.erase(it);

                    maxHeap.insert(incoming);
                    sum += incoming.num;
                } else {
                    minHeapExtras.insert(incoming);
                }
            }

            Pair outgoing{nums[left], left};

            auto itMax = maxHeap.find(outgoing);
            if (itMax != maxHeap.end()) {
                sum -= itMax->num;
                maxHeap.erase(itMax);

                if (!minHeapExtras.empty()) {
                    auto itMin = minHeapExtras.begin();
                    maxHeap.insert(*itMin);
                    sum += itMin->num;
                    minHeapExtras.erase(itMin);
                }
            } else {
                minHeapExtras.erase(minHeapExtras.find(outgoing));
            }

            if ((int)maxHeap.size() == K) {
                minCost = min(minCost, sum);
            }

            left++;
            right++;
        }

        return nums[0] + minCost;
    }
};
