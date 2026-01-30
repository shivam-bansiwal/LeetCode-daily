// LeetCode 2977. Minimum Cost to Convert String II
// Time Complexity: O(N * (L + Dijkstra))
// Space Complexity: O(N + Graph)

import java.util.*;

class Solution {

    class Pair {
        String node;
        long dist;

        Pair(String n, long d) {
            node = n;
            dist = d;
        }
    }

    String SRC, TRGT;
    int N;
    final long INF = (long) 1e15;

    public long minimumCost(
            String source,
            String target,
            String[] original,
            String[] changed,
            int[] cost) {

        SRC = source;
        TRGT = target;
        N = source.length();

        Map<String, List<Pair>> adjList = new HashMap<>();
        TreeSet<Integer> substringLengths = new TreeSet<>();
        Map<String, Map<String, Long>> dijkstraDP = new HashMap<>();

        for (int i = 0; i < original.length; i++) {
            adjList
                .computeIfAbsent(original[i], k -> new ArrayList<>())
                .add(new Pair(changed[i], cost[i]));
            substringLengths.add(original[i].length());
        }

        long[] dp = new long[N + 1];
        Arrays.fill(dp, -1);

        long ans = solve(0, dp, adjList, substringLengths, dijkstraDP);
        return ans >= INF ? -1 : ans;
    }

    // DP over string index
    private long solve(
            int idx,
            long[] dp,
            Map<String, List<Pair>> adjList,
            TreeSet<Integer> substringLengths,
            Map<String, Map<String, Long>> dijkstraDP) {

        if (idx == N) return 0;
        if (dp[idx] != -1) return dp[idx];

        long minCost = INF;

        // No operation if characters already match
        if (SRC.charAt(idx) == TRGT.charAt(idx)) {
            minCost = solve(idx + 1, dp, adjList, substringLengths, dijkstraDP);
        }

        // Try all possible substring transformations
        for (int len : substringLengths) {
            if (idx + len > N) break;

            String sSub = SRC.substring(idx, idx + len);
            String tSub = TRGT.substring(idx, idx + len);

            if (!adjList.containsKey(sSub)) continue;

            long convertCost = dijkstra(sSub, tSub, adjList, dijkstraDP);
            if (convertCost == INF) continue;

            minCost = Math.min(
                minCost,
                convertCost + solve(idx + len, dp, adjList, substringLengths, dijkstraDP)
            );
        }

        return dp[idx] = minCost;
    }

    // Dijkstra with memoisation
    private long dijkstra(
            String src,
            String trgt,
            Map<String, List<Pair>> adjList,
            Map<String, Map<String, Long>> dijkstraDP) {

        if (dijkstraDP.containsKey(src) &&
            dijkstraDP.get(src).containsKey(trgt)) {
            return dijkstraDP.get(src).get(trgt);
        }

        Map<String, Long> dist = new HashMap<>();
        PriorityQueue<Pair> pq =
                new PriorityQueue<>((a, b) -> Long.compare(a.dist, b.dist));

        pq.offer(new Pair(src, 0));
        dist.put(src, 0L);

        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            String node = cur.node;
            long d = cur.dist;

            if (node.equals(trgt)) break;
            if (!adjList.containsKey(node)) continue;

            for (Pair p : adjList.get(node)) {
                long nd = d + p.dist;
                if (!dist.containsKey(p.node) || nd < dist.get(p.node)) {
                    dist.put(p.node, nd);
                    pq.offer(new Pair(p.node, nd));
                }
            }
        }

        long res = dist.getOrDefault(trgt, INF);
        dijkstraDP.computeIfAbsent(src, k -> new HashMap<>()).put(trgt, res);
        return res;
    }
}
