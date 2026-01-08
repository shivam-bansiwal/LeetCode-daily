// LeetCode 19. Remove Nth Node From End of List
// Time Complexity:
//  - Approach 1 (Two Pointers): O(n)
//  - Approach 2 (Length + Traversal): O(n)
// Space Complexity: O(1)

import java.util.ArrayList;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {

    // --------------------------------------------------
    // Approach 1: Two Pointers (Optimal)
    // --------------------------------------------------
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;

        // Move fast pointer n steps ahead
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // If fast becomes null, remove head
        if (fast == null) {
            return head.next;
        }

        ListNode slow = head;

        // Move both pointers until fast reaches the end
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Remove the target node
        slow.next = slow.next.next;
        return head;
    }

    // --------------------------------------------------
    // Approach 2: Length Calculation (Less Optimal)
    // --------------------------------------------------
    public ListNode removeNthFromEndLength(ListNode head, int n) {
        if (head == null || head.next == null) {
            return null;
        }

        int size = 0;
        ListNode temp = head;

        // Calculate length
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        int target = size - n;

        // Remove head
        if (target == 0) {
            return head.next;
        }

        temp = head;
        for (int i = 1; i < target; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;
        return head;
    }
}
