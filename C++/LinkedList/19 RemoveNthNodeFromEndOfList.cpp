// LeetCode 19. Remove Nth Node From End of List
// Time Complexity:
//  - Approach 1 (Two Pointers): O(n)
//  - Approach 2 (Length + Traversal): O(n)
// Space Complexity: O(1)

using namespace std;

/**
 * Definition for singly-linked list.
 */
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int x) : val(x), next(nullptr) {}
    ListNode(int x, ListNode *next) : val(x), next(next) {}
};

class Solution {
public:
    // --------------------------------------------------
    // Approach 1: Two Pointers (Optimal)
    // --------------------------------------------------
    ListNode* removeNthFromEnd(ListNode* head, int n) {
        if (!head || !head->next) {
            return nullptr;
        }

        ListNode* fast = head;

        // Move fast n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast->next;
        }

        // Remove head if needed
        if (!fast) {
            return head->next;
        }

        ListNode* slow = head;

        while (fast->next) {
            slow = slow->next;
            fast = fast->next;
        }

        slow->next = slow->next->next;
        return head;
    }

    // --------------------------------------------------
    // Approach 2: Length Calculation
    // --------------------------------------------------
    ListNode* removeNthFromEndLength(ListNode* head, int n) {
        if (!head || !head->next) {
            return nullptr;
        }

        int size = 0;
        ListNode* temp = head;

        while (temp) {
            size++;
            temp = temp->next;
        }

        int target = size - n;

        if (target == 0) {
            return head->next;
        }

        temp = head;
        for (int i = 1; i < target; i++) {
            temp = temp->next;
        }

        temp->next = temp->next->next;
        return head;
    }
};
