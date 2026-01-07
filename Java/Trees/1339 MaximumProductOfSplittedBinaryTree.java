// LeetCode 1339. Maximum Product of Splitted Binary Tree
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

    private static final int MOD = 1_000_000_007;

    // --------------------------------------------------
    // Way 1: Two DFS passes
    // First DFS -> compute total sum
    // Second DFS -> compute max product
    // --------------------------------------------------
    public int maxProduct(TreeNode root) {
        long[] maxProd = new long[1];

        long totalSum = dfsSum(root);
        dfsProduct(root, totalSum, maxProd);

        return (int) (maxProd[0] % MOD);
    }

    private long dfsSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + dfsSum(root.left) + dfsSum(root.right);
    }

    private long dfsProduct(TreeNode root, long totalSum, long[] maxProd) {
        if (root == null) return 0;

        long leftSum = dfsProduct(root.left, totalSum, maxProd);
        long rightSum = dfsProduct(root.right, totalSum, maxProd);

        long currSum = root.val + leftSum + rightSum;
        maxProd[0] = Math.max(maxProd[0], currSum * (totalSum - currSum));

        return currSum;
    }

    // --------------------------------------------------
    // Way 2: BFS for total sum + DFS for product
    // --------------------------------------------------
    public int maxProductBFS(TreeNode root) {
        long totalSum = 0;
        long[] maxProd = new long[1];

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        // BFS to compute total sum
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            totalSum += node.val;

            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        dfsProduct(root, totalSum, maxProd);
        return (int) (maxProd[0] % MOD);
    }
}
