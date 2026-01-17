// LeetCode 3047. Find the Largest Area of Square Inside Two Rectangles
// Time Complexity: O(n^2)
// Space Complexity: O(1)

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long maxSide = 0;

        for (int i = 0; i < n; i++) {
            int x1 = bottomLeft[i][0];
            int y1 = bottomLeft[i][1];
            int x2 = topRight[i][0];
            int y2 = topRight[i][1];

            for (int j = i + 1; j < n; j++) {
                int x3 = bottomLeft[j][0];
                int y3 = bottomLeft[j][1];
                int x4 = topRight[j][0];
                int y4 = topRight[j][1];

                // Overlapping width
                long width = Math.min(x2, x4) - Math.max(x1, x3);

                // Overlapping height
                long height = Math.min(y2, y4) - Math.max(y1, y3);

                if (width > 0 && height > 0) {
                    long side = Math.min(width, height);
                    maxSide = Math.max(maxSide, side);
                }
            }
        }

        return maxSide * maxSide;
    }
}
