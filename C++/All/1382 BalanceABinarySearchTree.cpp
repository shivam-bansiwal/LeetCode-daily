// LeetCode 1382. Balance a Binary Search Tree
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <vector>
using namespace std;

/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */

class Solution {
public:
    TreeNode* balanceBST(TreeNode* root) {
        if (!root) return nullptr;

        vector<int> inorder;
        inOrder(root, inorder);

        return buildBalanced(inorder, 0, (int)inorder.size() - 1);
    }

private:
    void inOrder(TreeNode* node, vector<int>& arr) {
        if (!node) return;
        inOrder(node->left, arr);
        arr.push_back(node->val);
        inOrder(node->right, arr);
    }

    TreeNode* buildBalanced(vector<int>& arr, int l, int r) {
        if (l > r) return nullptr;

        int m = l + (r - l) / 2;
        TreeNode* node = new TreeNode(arr[m]);

        node->left = buildBalanced(arr, l, m - 1);
        node->right = buildBalanced(arr, m + 1, r);

        return node;
    }
};
