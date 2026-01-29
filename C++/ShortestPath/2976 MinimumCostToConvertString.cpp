// LeetCode 2976. Minimum Cost to Convert String I
// Time Complexity: O(26 * (E log V)) ≈ O(E log V)
// Space Complexity: O(26 * 26 + E)

#include <vector>
#include <queue>
#include <string>
#include <climits>
using namespace std;

class Solution {
public:
    long long minimumCost(
        string source,
        string target,
        vector<char>& original,
        vector<char>& changed,
        vector<int>& cost) {

        vector<vector<pair<int,int>>> adj(26);

        for (int i = 0; i < original.size(); i++) {
            adj[original[i] - 'a'].push_back(
                {changed[i] - 'a', cost[i]}
            );
        }

        vector<vector<long long>> dist(26, vector<long long>(26, -1));
        vector<bool> processed(26, false);

        // Run Dijkstra once per unique source character
        for (char c : source) {
            int idx = c - 'a';
            if (!processed[idx]) {
                dijkstra(idx, adj, dist[idx]);
                processed[idx] = true;
            }
        }

        long long total = 0;

        for (int i = 0; i < source.size(); i++) {
            int s = source[i] - 'a';
            int t = target[i] - 'a';

            if (s == t) continue;

            if (dist[s][t] == -1) return -1;
            total += dist[s][t];
        }

        return total;
    }

private:
    void dijkstra(
        int src,
        vector<vector<pair<int,int>>>& adj,
        vector<long long>& dist) {

        for (int i = 0; i < 26; i++) dist[i] = LLONG_MAX;
        dist[src] = 0;

        priority_queue<
            pair<long long,int>,
            vector<pair<long long,int>>,
            greater<>
        > pq;

        pq.push({0, src});

        while (!pq.empty()) {
            auto [d, u] = pq.top();
            pq.pop();

            if (d > dist[u]) continue;

            for (auto& [v, w] : adj[u]) {
                if (dist[v] > d + w) {
                    dist[v] = d + w;
                    pq.push({dist[v], v});
                }
            }
        }

        for (int i = 0; i < 26; i++) {
            if (dist[i] == LLONG_MAX) dist[i] = -1;
        }
    }
};
