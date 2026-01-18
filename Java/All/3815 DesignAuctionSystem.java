// LeetCode 3815. Design Auction System
// Time Complexity:
// addBid / updateBid: O(log n)
// removeBid: O(1)
// getHighestBidder: Amortized O(log n)
// Space Complexity: O(n)

import java.util.*;

class AuctionSystem {

    // itemId -> (userId -> bidAmount)
    private Map<Integer, Map<Integer, Integer>> itemUser;

    // itemId -> max heap of [bidAmount, userId]
    private Map<Integer, PriorityQueue<int[]>> itemBids;

    public AuctionSystem() {
        itemUser = new HashMap<>();
        itemBids = new HashMap<>();
    }

    public void addBid(int userId, int itemId, int bidAmount) {
        itemUser
            .computeIfAbsent(itemId, k -> new HashMap<>())
            .put(userId, bidAmount);

        itemBids.computeIfAbsent(itemId, k ->
            new PriorityQueue<>((a, b) -> {
                if (a[0] == b[0]) {
                    return Integer.compare(b[1], a[1]); // higher userId wins tie
                }
                return Integer.compare(b[0], a[0]); // higher bid first
            })
        );

        itemBids.get(itemId).offer(new int[]{bidAmount, userId});
    }

    public void updateBid(int userId, int itemId, int newAmount) {
        itemUser.get(itemId).put(userId, newAmount);
        itemBids.get(itemId).offer(new int[]{newAmount, userId});
    }

    public void removeBid(int userId, int itemId) {
        itemUser.get(itemId).remove(userId);
    }

    public int getHighestBidder(int itemId) {
        PriorityQueue<int[]> pq = itemBids.get(itemId);
        Map<Integer, Integer> users = itemUser.get(itemId);

        if (pq == null || users == null || users.isEmpty()) {
            return -1;
        }

        // Lazy deletion of stale heap entries
        while (!pq.isEmpty()) {
            int[] top = pq.peek(); // [amount, userId]
            int bid = top[0];
            int user = top[1];

            if (users.containsKey(user) && users.get(user) == bid) {
                return user;
            }
            pq.poll(); // stale entry
        }

        return -1;
    }
}
