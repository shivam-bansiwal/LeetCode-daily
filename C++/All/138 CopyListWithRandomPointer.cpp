// LeetCode 138. Copy List with Random Pointer
// Time Complexity: O(n)
// Space Complexity: O(1) extra (excluding output)

/*
// Definition for a Node.
class Node {
public:
    int val;
    Node* next;
    Node* random;

    Node(int _val) {
        val = _val;
        next = nullptr;
        random = nullptr;
    }
};
*/

class Solution {
public:
    Node* copyRandomList(Node* head) {
        if (!head) return nullptr;

        // --------------------------------------------------
        // Step 1: Insert copied nodes between original nodes
        // --------------------------------------------------
        Node* temp = head;
        while (temp) {
            Node* copy = new Node(temp->val);
            copy->next = temp->next;
            temp->next = copy;
            temp = copy->next;
        }

        // --------------------------------------------------
        // Step 2: Assign random pointers
        // --------------------------------------------------
        temp = head;
        while (temp) {
            if (temp->random) {
                temp->next->random = temp->random->next;
            }
            temp = temp->next->next;
        }

        // --------------------------------------------------
        // Step 3: Separate copied list
        // --------------------------------------------------
        Node* dummy = new Node(-1);
        Node* copyCurr = dummy;
        temp = head;

        while (temp) {
            copyCurr->next = temp->next;
            temp->next = temp->next->next;

            temp = temp->next;
            copyCurr = copyCurr->next;
        }

        return dummy->next;
    }
};
