// LeetCode 3510. Minimum Pair Removal to Sort Array II
// Time Complexity: O(n log n)
// Space Complexity: O(n)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    struct Pair {
        long long sum;
        int idx;

        bool operator<(const Pair& other) const {
            if (sum != other.sum) return sum < other.sum;
            return idx < other.idx;
        }
    };

    int minimumPairRemoval(vector<int>& nums) {
        int n = nums.size();

        vector<long long> temp(n);
        for (int i = 0; i < n; i++) {
            temp[i] = nums[i];
        }

        set<Pair> minPairSet;

        vector<int> nextIndex(n), prevIndex(n);
        for (int i = 0; i < n; i++) {
            nextIndex[i] = i + 1;
            prevIndex[i] = i - 1;
        }

        int badPairs = 0;
        for (int i = 0; i < n - 1; i++) {
            if (temp[i] > temp[i + 1]) badPairs++;
            minPairSet.insert({temp[i] + temp[i + 1], i});
        }

        int operations = 0;

        while (badPairs > 0) {
            auto cur = *minPairSet.begin();
            minPairSet.erase(minPairSet.begin());

            int first = cur.idx;
            int second = nextIndex[first];

            int firstLeft = prevIndex[first];
            int secondRight = nextIndex[second];

            if (temp[first] > temp[second]) {
                badPairs--;
            }

            if (firstLeft >= 0) {
                if (temp[firstLeft] > temp[first] &&
                    temp[firstLeft] <= temp[first] + temp[second]) {
                    badPairs--;
                } else if (temp[firstLeft] <= temp[first] &&
                           temp[firstLeft] > temp[first] + temp[second]) {
                    badPairs++;
                }
            }

            if (secondRight < n) {
                if (temp[secondRight] >= temp[second] &&
                    temp[secondRight] < temp[first] + temp[second]) {
                    badPairs++;
                } else if (temp[secondRight] < temp[second] &&
                           temp[secondRight] >= temp[first] + temp[second]) {
                    badPairs--;
                }
            }

            if (firstLeft >= 0) {
                minPairSet.erase({temp[firstLeft] + temp[first], firstLeft});
                minPairSet.insert({temp[firstLeft] + temp[first] + temp[second], firstLeft});
            }

            if (secondRight < n) {
                minPairSet.erase({temp[second] + temp[secondRight], second});
                minPairSet.insert({temp[first] + temp[second] + temp[secondRight], first});
                prevIndex[secondRight] = first;
            }

            nextIndex[first] = secondRight;
            temp[first] += temp[second];

            operations++;
        }

        return operations;
    }
};
