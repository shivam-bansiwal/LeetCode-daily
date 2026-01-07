// LeetCode 1339. Maximum Product of Splitted Binary Tree
// Time Complexity: O(n)
// Space Complexity: O(n)

#include <queue>
#include <climits>
using namespace std;

/**
 * Definition for a binary tree node.
 */
struct TreeNode {
    int val;
    TreeNode *left;
    TreeNode *right;
    TreeNode() : val(0), left(nullptr), right(nullptr) {}
    TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
    TreeNode(int x, TreeNode *left, TreeNode *right)
        : val(x), left(left), right(right) {}
};

class Solution {
    static const int MOD = 1'000'000'007;

public:
    // --------------------------------------------------
    // Way 1: Two DFS passes
    // --------------------------------------------------
    int maxProduct(TreeNode* root) {
        long long maxProd = 0;

        long long totalSum = dfsSum(root);
        dfsProduct(root, totalSum, maxProd);

        return (int)(maxProd % MOD);
    }

private:
    long long dfsSum(TreeNode* root) {
        if (!root) return 0;
        return root->val + dfsSum(root->left) + dfsSum(root->right);
    }

    long long dfsProduct(TreeNode* root, long long totalSum, long long& maxProd) {
        if (!root) return 0;

        long long leftSum = dfsProduct(root->left, totalSum, maxProd);
        long long rightSum = dfsProduct(root->right, totalSum, maxProd);

        long long currSum = root->val + leftSum + rightSum;
        maxProd = max(maxProd, currSum * (totalSum - currSum));

        return currSum;
    }

public:
    // --------------------------------------------------
    // Way 2: BFS + DFS
    // --------------------------------------------------
    int maxProductBFS(TreeNode* root) {
        long long totalSum = 0;
        long long maxProd = 0;

        queue<TreeNode*> q;
        q.push(root);

        // BFS to compute total sum
        while (!q.empty()) {
            TreeNode* node = q.front();
            q.pop();
            totalSum += node->val;

            if (node->left) q.push(node->left);
            if (node->right) q.push(node->right);
        }

        dfsProduct(root, totalSum, maxProd);
        return (int)(maxProd % MOD);
    }
};
