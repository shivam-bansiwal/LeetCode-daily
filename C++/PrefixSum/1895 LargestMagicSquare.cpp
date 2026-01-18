// LeetCode 1895. Largest Magic Square
// Time Complexity: O(n * m * min(n, m))
// Space Complexity: O(n * m)

#include <vector>
#include <algorithm>
using namespace std;

class Solution {
public:
    int largestMagicSquare(vector<vector<int>>& grid) {
        int n = grid.size(), m = grid[0].size();

        vector<vector<long long>> row(n, vector<long long>(m));
        vector<vector<long long>> col(n, vector<long long>(m));
        vector<vector<long long>> d1(n, vector<long long>(m));
        vector<vector<long long>> d2(n, vector<long long>(m));

        buildPrefix(grid, row, col, d1, d2);

        for (int sz = min(n, m); sz > 1; sz--) {
            if (exists(grid, row, col, d1, d2, sz)) return sz;
        }
        return 1;
    }

private:
    void buildPrefix(vector<vector<int>>& g,
                     vector<vector<long long>>& row,
                     vector<vector<long long>>& col,
                     vector<vector<long long>>& d1,
                     vector<vector<long long>>& d2) {

        int n = g.size(), m = g[0].size();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                row[i][j] = g[i][j] + (j > 0 ? row[i][j - 1] : 0);
                col[i][j] = g[i][j] + (i > 0 ? col[i - 1][j] : 0);
                d1[i][j] = g[i][j] + (i > 0 && j > 0 ? d1[i - 1][j - 1] : 0);
                d2[i][j] = g[i][j] + (i > 0 && j + 1 < m ? d2[i - 1][j + 1] : 0);
            }
        }
    }

    bool exists(vector<vector<int>>& g,
                vector<vector<long long>>& row,
                vector<vector<long long>>& col,
                vector<vector<long long>>& d1,
                vector<vector<long long>>& d2,
                int sz) {

        int n = g.size(), m = g[0].size();
        int len = sz - 1;

        for (int i = 0; i + len < n; i++) {
            for (int j = 0; j + len < m; j++) {

                long long target =
                    row[i][j + len] - (j > 0 ? row[i][j - 1] : 0);

                for (int r = i; r <= i + len; r++) {
                    long long s =
                        row[r][j + len] - (j > 0 ? row[r][j - 1] : 0);
                    if (s != target) return false;
                }

                for (int c = j; c <= j + len; c++) {
                    long long s =
                        col[i + len][c] - (i > 0 ? col[i - 1][c] : 0);
                    if (s != target) return false;
                }

                long long diag1 =
                    d1[i + len][j + len] -
                    (i > 0 && j > 0 ? d1[i - 1][j - 1] : 0);

                long long diag2 =
                    d2[i + len][j] -
                    (i > 0 && j + len + 1 < m ? d2[i - 1][j + len + 1] : 0);

                if (diag1 == target && diag2 == target) return true;
            }
        }
        return false;
    }
};
