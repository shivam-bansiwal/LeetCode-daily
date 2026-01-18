// LeetCode 3815. Design Auction System
// Time Complexity:
// addBid / updateBid: O(log n)
// removeBid: O(1)
// getHighestBidder: Amortized O(log n)
// Space Complexity: O(n)

#include <unordered_map>
#include <queue>
using namespace std;

class AuctionSystem {
private:
    // itemId -> (userId -> bidAmount)
    unordered_map<int, unordered_map<int, int>> itemUser;

    // itemId -> max heap of {bidAmount, userId}
    unordered_map<int, priority_queue<pair<int, int>>> itemBids;

public:
    AuctionSystem() {}

    void addBid(int userId, int itemId, int bidAmount) {
        itemUser[itemId][userId] = bidAmount;
        itemBids[itemId].push({bidAmount, userId});
    }

    void updateBid(int userId, int itemId, int newAmount) {
        itemUser[itemId][userId] = newAmount;
        itemBids[itemId].push({newAmount, userId});
    }

    void removeBid(int userId, int itemId) {
        itemUser[itemId].erase(userId);
    }

    int getHighestBidder(int itemId) {
        if (!itemBids.count(itemId) || itemUser[itemId].empty()) {
            return -1;
        }

        auto& pq = itemBids[itemId];
        auto& users = itemUser[itemId];

        // Lazy removal of outdated bids
        while (!pq.empty()) {
            auto [bid, user] = pq.top();
            if (users.count(user) && users[user] == bid) {
                return user;
            }
            pq.pop(); // stale entry
        }

        return -1;
    }
};
