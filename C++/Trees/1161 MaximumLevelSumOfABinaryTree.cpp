// LeetCode 1161. Maximum Level Sum of a Binary Tree
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
public:
    int maxLevelSum(TreeNode* root) {
        queue<TreeNode*> q;

        int maxSum = INT_MIN;
        int smallestLevel = 0;
        int currentLevel = 1;

        // Begin BFS
        q.push(root);

        while (!q.empty()) {
            int size = q.size();
            int levelSum = 0;

            // Process nodes of current level
            while (size-- > 0) {
                TreeNode* node = q.front();
                q.pop();

                levelSum += node->val;

                if (node->left != nullptr) {
                    q.push(node->left);
                }
                if (node->right != nullptr) {
                    q.push(node->right);
                }
            }

            // Track the smallest level with maximum sum
            if (levelSum > maxSum) {
                maxSum = levelSum;
                smallestLevel = currentLevel;
            }

            currentLevel++;
        }

        return smallestLevel;
    }
};
