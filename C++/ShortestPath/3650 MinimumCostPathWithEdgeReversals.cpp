// LeetCode 3650. Minimum Cost Path with Edge Reversals
// Time Complexity: O((n + m) log n)
// Space Complexity: O(n + m)

#include <vector>
#include <queue>
#include <climits>
using namespace std;

class Solution {
public:
    int minCost(int n, vector<vector<int>>& edges) {

        vector<vector<pair<int,int>>> adj(n);

        for (auto& e : edges) {
            int u = e[0], v = e[1], w = e[2];
            // Forward edge
            adj[u].push_back({v, w});
            // Reverse edge with double cost
            adj[v].push_back({u, w * 2});
        }

        priority_queue<pair<int,int>, vector<pair<int,int>>, greater<>> pq;
        vector<int> dist(n, INT_MAX);

        pq.push({0, 0}); // {cost, node}
        dist[0] = 0;

        while (!pq.empty()) {
            auto [cost, node] = pq.top();
            pq.pop();

            if (cost > dist[node]) continue;

            for (auto& [next, wt] : adj[node]) {
                if (dist[next] > cost + wt) {
                    dist[next] = cost + wt;
                    pq.push({dist[next], next});
                }
            }
        }

        return dist[n - 1] == INT_MAX ? -1 : dist[n - 1];
    }
};
