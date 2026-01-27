// LeetCode 3650. Minimum Cost Path with Edge Reversals
// Time Complexity: O((n + m) log n)
// Space Complexity: O(n + m)

import java.util.*;

class Solution {
    public int minCost(int n, int[][] edges) {

        // Build directed graph with asymmetric weights
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            // Forward edge: normal cost
            adj.get(u).add(new int[]{v, w});
            // Reverse edge: double cost
            adj.get(v).add(new int[]{u, w * 2});
        }

        // Dijkstra's algorithm
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{0, 0}); // {node, cost}

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0], cost = cur[1];

            if (cost > dist[node]) continue;

            for (int[] nei : adj.get(node)) {
                int next = nei[0];
                int wt = nei[1];

                if (dist[next] > cost + wt) {
                    dist[next] = cost + wt;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        return dist[n - 1] == Integer.MAX_VALUE ? -1 : dist[n - 1];
    }
}
