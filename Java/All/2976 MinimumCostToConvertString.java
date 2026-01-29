// LeetCode 2976. Minimum Cost to Convert String I
// Time Complexity: O(26 * (E log V)) ≈ O(E log V)
// Space Complexity: O(26 * 26 + E)

import java.util.*;

class Solution {

    class Pair {
        char node;
        long dist;

        Pair(char n, long d) {
            node = n;
            dist = d;
        }
    }

    public long minimumCost(
            String source,
            String target,
            char[] original,
            char[] changed,
            int[] cost) {

        int n = source.length();

        // Build directed graph
        Map<Character, List<Pair>> adj = new HashMap<>();
        for (int i = 0; i < original.length; i++) {
            adj.computeIfAbsent(original[i], k -> new ArrayList<>())
                    .add(new Pair(changed[i], cost[i]));
        }

        // dist[u][v] = minimum cost to convert u -> v
        long[][] dist = new long[26][26];
        for (int i = 0; i < 26; i++) {
            Arrays.fill(dist[i], -1);
        }

        boolean[] processed = new boolean[26];

        // Run Dijkstra once per unique source character
        for (char c : source.toCharArray()) {
            int idx = c - 'a';
            if (!processed[idx]) {
                dijkstraAll(c, adj, dist[idx]);
                processed[idx] = true;
            }
        }

        long total = 0;

        for (int i = 0; i < n; i++) {
            char s = source.charAt(i);
            char t = target.charAt(i);

            if (s == t) continue;

            long d = dist[s - 'a'][t - 'a'];
            if (d == -1) return -1;

            total += d;
        }

        return total;
    }

    // Dijkstra from a single source to all characters
    private void dijkstraAll(
            char src,
            Map<Character, List<Pair>> adj,
            long[] dist) {

        Arrays.fill(dist, Long.MAX_VALUE);
        dist[src - 'a'] = 0;

        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));
        pq.offer(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            char u = cur.node;
            long d = cur.dist;

            if (d > dist[u - 'a']) continue;
            if (!adj.containsKey(u)) continue;

            for (Pair p : adj.get(u)) {
                char v = p.node;
                long nd = d + p.dist;

                if (nd < dist[v - 'a']) {
                    dist[v - 'a'] = nd;
                    pq.offer(new Pair(v, nd));
                }
            }
        }

        // Mark unreachable nodes
        for (int i = 0; i < 26; i++) {
            if (dist[i] == Long.MAX_VALUE) {
                dist[i] = -1;
            }
        }
    }
}
