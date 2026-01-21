// LeetCode 2858. Minimum Edge Reversals So Every Node Is Reachable
// Approach: Tree DP + DFS
// Time: O(n), Space: O(n)

#include <vector>
using namespace std;

class Solution {
public:
    vector<int> minEdgeReversals(int n, vector<vector<int>>& edges) {

        vector<vector<pair<int,int>>> adj(n);
        int startNode = 0;

        for (auto &e : edges) {
            int u = e[0], v = e[1];
            adj[u].push_back({v, 0});
            adj[v].push_back({u, 1});

            if (adj[u].size() == 1) startNode = u;
            if (adj[v].size() == 1) startNode = v;
        }

        vector<bool> vis(n, false);
        vector<int> depth(n, 0), cost(n, 0);

        int totalCost = dfs(startNode, 0, adj, vis, depth, cost);

        vector<int> ans(n);
        ans[startNode] = totalCost;

        for (int i = 0; i < n; i++) {
            if (i == startNode) continue;
            int costToStart = depth[i] - cost[i];
            int costToOthers = totalCost - cost[i];
            ans[i] = costToStart + costToOthers;
        }

        return ans;
    }

private:
    int dfs(int node, int d,
            vector<vector<pair<int,int>>>& adj,
            vector<bool>& vis,
            vector<int>& depth,
            vector<int>& cost) {

        vis[node] = true;
        depth[node] = d;
        int total = 0;

        for (auto &[next, w] : adj[node]) {
            if (!vis[next]) {
                cost[next] = cost[node] + w;
                total += w + dfs(next, d + 1, adj, vis, depth, cost);
            }
        }
        return total;
    }
};
