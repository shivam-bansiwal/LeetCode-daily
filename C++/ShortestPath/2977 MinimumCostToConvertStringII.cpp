// LeetCode 2977. Minimum Cost to Convert String II
// Time Complexity: O(N * (L + Dijkstra))
// Space Complexity: O(N + Graph)

#include <vector>
#include <string>
#include <unordered_map>
#include <queue>
#include <set>
#include <climits>
using namespace std;

class Solution {
    static constexpr long long INF = 1e15;

public:
    string SRC, TRGT;
    int N;

    long long minimumCost(
        string source,
        string target,
        vector<string>& original,
        vector<string>& changed,
        vector<int>& cost) {

        SRC = source;
        TRGT = target;
        N = source.size();

        unordered_map<string, vector<pair<string,int>>> adj;
        set<int> lens;

        for (int i = 0; i < original.size(); i++) {
            adj[original[i]].push_back({changed[i], cost[i]});
            lens.insert(original[i].size());
        }

        vector<long long> dp(N + 1, -1);
        unordered_map<string, unordered_map<string,long long>> memo;

        long long ans = solve(0, dp, adj, lens, memo);
        return ans >= INF ? -1 : ans;
    }

private:
    long long solve(
        int idx,
        vector<long long>& dp,
        unordered_map<string, vector<pair<string,int>>>& adj,
        set<int>& lens,
        unordered_map<string, unordered_map<string,long long>>& memo) {

        if (idx == N) return 0;
        if (dp[idx] != -1) return dp[idx];

        long long best = INF;

        if (SRC[idx] == TRGT[idx]) {
            best = solve(idx + 1, dp, adj, lens, memo);
        }

        for (int len : lens) {
            if (idx + len > N) break;

            string sSub = SRC.substr(idx, len);
            string tSub = TRGT.substr(idx, len);

            if (!adj.count(sSub)) continue;

            long long c = dijkstra(sSub, tSub, adj, memo);
            if (c == INF) continue;

            best = min(best, c + solve(idx + len, dp, adj, lens, memo));
        }

        return dp[idx] = best;
    }

    long long dijkstra(
        string src,
        string trgt,
        unordered_map<string, vector<pair<string,int>>>& adj,
        unordered_map<string, unordered_map<string,long long>>& memo) {

        if (memo[src].count(trgt)) return memo[src][trgt];

        unordered_map<string,long long> dist;
        priority_queue<pair<long long,string>,
            vector<pair<long long,string>>,
            greater<>> pq;

        pq.push({0, src});
        dist[src] = 0;

        while (!pq.empty()) {
            auto [d, u] = pq.top(); pq.pop();
            if (u == trgt) break;
            if (!adj.count(u)) continue;

            for (auto& [v, w] : adj[u]) {
                if (!dist.count(v) || d + w < dist[v]) {
                    dist[v] = d + w;
                    pq.push({dist[v], v});
                }
            }
        }

        long long res = dist.count(trgt) ? dist[trgt] : INF;
        memo[src][trgt] = res;
        return res;
    }
};
