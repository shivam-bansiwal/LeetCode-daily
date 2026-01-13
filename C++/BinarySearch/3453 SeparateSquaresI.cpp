// LeetCode 3453. Separate Squares I
// Time Complexity: O(n * log(range / precision))
// Space Complexity: O(1)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
private:
    double getMinY(vector<vector<int>>& squares) {
        double minY = squares[0][1];
        for (auto& sq : squares) {
            minY = min(minY, (double)sq[1]);
        }
        return minY;
    }

    double getMaxY(vector<vector<int>>& squares) {
        double maxY = squares[0][1] + squares[0][2];
        for (auto& sq : squares) {
            maxY = max(maxY, (double)sq[1] + sq[2]);
        }
        return maxY;
    }

    bool isLowerHalfLarger(vector<vector<int>>& squares, double midY) {
        double lowerArea = 0.0;
        double upperArea = 0.0;

        for (auto& sq : squares) {
            double bottom = sq[1];
            double side = sq[2];
            double top = bottom + side;

            if (top <= midY) {
                lowerArea += side * side;
            }
            else if (bottom >= midY) {
                upperArea += side * side;
            }
            else {
                double below = midY - bottom;
                double above = top - midY;
                lowerArea += below * side;
                upperArea += above * side;
            }
        }

        return lowerArea >= upperArea;
    }

public:
    double separateSquares(vector<vector<int>>& squares) {
        double low = getMinY(squares);
        double high = getMaxY(squares);
        double eps = 1e-5;

        while (high - low > eps) {
            double mid = (low + high) / 2.0;
            if (isLowerHalfLarger(squares, mid)) {
                high = mid;
            } else {
                low = mid;
            }
        }

        return low;
    }
};
