// LeetCode 1161. Maximum Level Sum of a Binary Tree
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.LinkedList;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();

        int maxSum = Integer.MIN_VALUE;
        int smallestLevel = 0;
        int currentLevel = 1;

        // Start BFS from root
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;

            // Process all nodes at current level
            while (size-- > 0) {
                TreeNode node = queue.poll();
                levelSum += node.val;

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            // Update max sum and smallest level
            if (levelSum > maxSum) {
                maxSum = levelSum;
                smallestLevel = currentLevel;
            }

            currentLevel++;
        }

        return smallestLevel;
    }
}
