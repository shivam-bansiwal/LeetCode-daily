// LeetCode 138. Copy List with Random Pointer
// Time Complexity: O(n)
// Space Complexity: O(1) extra (excluding output)

/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) return null;

        // --------------------------------------------------
        // Step 1: Insert copied nodes between original nodes
        // --------------------------------------------------
        Node temp = head;
        while (temp != null) {
            Node copy = new Node(temp.val);
            copy.next = temp.next;
            temp.next = copy;
            temp = copy.next;
        }

        // --------------------------------------------------
        // Step 2: Assign random pointers to copied nodes
        // --------------------------------------------------
        temp = head;
        while (temp != null) {
            if (temp.random != null) {
                temp.next.random = temp.random.next;
            }
            temp = temp.next.next;
        }

        // --------------------------------------------------
        // Step 3: Separate the copied list from original list
        // --------------------------------------------------
        Node dummy = new Node(-1);
        Node copyCurr = dummy;
        temp = head;

        while (temp != null) {
            copyCurr.next = temp.next;
            temp.next = temp.next.next;

            temp = temp.next;
            copyCurr = copyCurr.next;
        }

        return dummy.next;
    }
}
