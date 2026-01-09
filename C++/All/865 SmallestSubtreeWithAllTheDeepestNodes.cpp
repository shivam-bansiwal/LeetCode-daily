// LeetCode 865. Smallest Subtree with all the Deepest Nodes
// Time Complexity:
//  - Approach 1 (DFS bottom-up): O(n)
//  - Approach 2 (BFS + LCA): O(n)
// Space Complexity: O(n)

#include <queue>
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
public:
    // --------------------------------------------------
    // Approach 1: DFS Bottom-Up (Optimal)
    // --------------------------------------------------
    TreeNode* subtreeWithAllDeepest(TreeNode* root) {
        return dfs(root).node;
    }

private:
    struct Pair {
        int depth;
        TreeNode* node;
    };

    Pair dfs(TreeNode* root) {
        if (!root) return {0, nullptr};

        Pair left = dfs(root->left);
        Pair right = dfs(root->right);

        if (left.depth == right.depth) {
            return {left.depth + 1, root};
        }

        return (left.depth > right.depth)
                ? Pair{left.depth + 1, left.node}
                : Pair{right.depth + 1, right.node};
    }

public:
    // --------------------------------------------------
    // Approach 2: BFS + LCA
    // --------------------------------------------------
    TreeNode* subtreeWithAllDeepestBFS(TreeNode* root) {
        queue<TreeNode*> q;
        q.push(root);

        TreeNode* firstDeepest = nullptr;
        TreeNode* lastDeepest = nullptr;

        // BFS to find deepest level
        while (!q.empty()) {
            int size = q.size();
            firstDeepest = q.front();

            for (int i = 0; i < size; i++) {
                TreeNode* node = q.front();
                q.pop();

                if (node->left) q.push(node->left);
                if (node->right) q.push(node->right);

                lastDeepest = node;
            }
        }

        return lca(root, firstDeepest, lastDeepest);
    }

private:
    TreeNode* lca(TreeNode* root, TreeNode* p, TreeNode* q) {
        if (!root || root == p || root == q) {
            return root;
        }

        TreeNode* left = lca(root->left, p, q);
        TreeNode* right = lca(root->right, p, q);

        if (left && right) return root;
        return left ? left : right;
    }
};
