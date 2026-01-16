// LeetCode 2975. Maximum Square Area by Removing Fences From a Field
// Time Complexity: O(H^2 + V^2)
// Space Complexity: O(H^2)

#include <vector>
#include <unordered_set>
#include <cmath>
using namespace std;

class Solution {
public:
    int maximizeSquareArea(int m, int n, vector<int>& hFences, vector<int>& vFences) {
        const int MOD = 1e9 + 7;

        vector<long long> hPoints(hFences.size() + 2);
        hPoints[0] = 1;
        hPoints.back() = m;
        for (int i = 0; i < hFences.size(); i++) {
            hPoints[i + 1] = hFences[i];
        }

        vector<long long> vPoints(vFences.size() + 2);
        vPoints[0] = 1;
        vPoints.back() = n;
        for (int i = 0; i < vFences.size(); i++) {
            vPoints[i + 1] = vFences[i];
        }

        unordered_set<long long> verticalDistances;
        for (int i = 0; i < hPoints.size(); i++) {
            for (int j = i + 1; j < hPoints.size(); j++) {
                verticalDistances.insert(llabs(hPoints[j] - hPoints[i]));
            }
        }

        long long maxSide = -1;
        for (int i = 0; i < vPoints.size(); i++) {
            for (int j = i + 1; j < vPoints.size(); j++) {
                long long side = llabs(vPoints[j] - vPoints[i]);
                if (verticalDistances.count(side)) {
                    maxSide = max(maxSide, side);
                }
            }
        }

        if (maxSide == -1) return -1;

        return (int) ((maxSide % MOD) * (maxSide % MOD) % MOD);
    }
};
