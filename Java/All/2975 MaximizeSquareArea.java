// LeetCode 2975. Maximum Square Area by Removing Fences From a Field
// Time Complexity: O(H^2 + V^2)
// Space Complexity: O(H^2)

import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;

        // Include boundaries
        long[] hPoints = new long[hFences.length + 2];
        hPoints[0] = 1L;
        hPoints[hPoints.length - 1] = m;
        for (int i = 0; i < hFences.length; i++) {
            hPoints[i + 1] = hFences[i];
        }

        long[] vPoints = new long[vFences.length + 2];
        vPoints[0] = 1L;
        vPoints[vPoints.length - 1] = n;
        for (int i = 0; i < vFences.length; i++) {
            vPoints[i + 1] = vFences[i];
        }

        // Store all possible vertical distances
        Set<Long> verticalDistances = new HashSet<>();
        for (int i = 0; i < hPoints.length; i++) {
            for (int j = i + 1; j < hPoints.length; j++) {
                verticalDistances.add(Math.abs(hPoints[j] - hPoints[i]));
            }
        }

        long maxSide = -1;

        // Check matching horizontal distances
        for (int i = 0; i < vPoints.length; i++) {
            for (int j = i + 1; j < vPoints.length; j++) {
                long side = Math.abs(vPoints[j] - vPoints[i]);
                if (verticalDistances.contains(side)) {
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        if (maxSide == -1) return -1;

        return (int) ((maxSide % MOD) * (maxSide % MOD) % MOD);
    }
}
