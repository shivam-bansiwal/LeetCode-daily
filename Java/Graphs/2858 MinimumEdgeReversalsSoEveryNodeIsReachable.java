// LeetCode 2858. Minimum Edge Reversals So Every Node Is Reachable
// Approach: Tree DP + DFS (re-rooting idea)
// Time: O(n), Space: O(n)

import java.util.*;

class Solution {
    public int[] minEdgeReversals(int n, int[][] edges) {

        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        int startNode = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1];

            adj.get(u).add(new int[]{v, 0}); // original direction
            adj.get(v).add(new int[]{u, 1}); // reversed edge cost

            if (adj.get(u).size() == 1) startNode = u;
            if (adj.get(v).size() == 1) startNode = v;
        }

        boolean[] vis = new boolean[n];
        int[] depth = new int[n];
        int[] cost = new int[n];

        int totalCost = dfs(startNode, 0, adj, vis, depth, cost);

        int[] ans = new int[n];
        ans[startNode] = totalCost;

        for (int i = 0; i < n; i++) {
            if (i == startNode) continue;

            int costToStart = depth[i] - cost[i];
            int costToOthers = totalCost - cost[i];
            ans[i] = costToStart + costToOthers;
        }

        return ans;
    }

    private int dfs(int node, int d, List<List<int[]>> adj,
                    boolean[] vis, int[] depth, int[] cost) {

        vis[node] = true;
        depth[node] = d;
        int total = 0;

        for (int[] e : adj.get(node)) {
            int next = e[0], w = e[1];
            if (!vis[next]) {
                cost[next] = cost[node] + w;
                total += w + dfs(next, d + 1, adj, vis, depth, cost);
            }
        }
        return total;
    }
}
