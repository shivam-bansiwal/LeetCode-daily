// LeetCode 865. Smallest Subtree with all the Deepest Nodes
// Time Complexity:
//  - Approach 1 (DFS bottom-up): O(n)
//  - Approach 2 (BFS + LCA): O(n)
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

    // --------------------------------------------------
    // Approach 1: DFS (Bottom-Up, Optimal)
    // --------------------------------------------------

    private static class Pair {
        int depth;
        TreeNode node;

        Pair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }

    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return dfs(root).node;
    }

    private Pair dfs(TreeNode root) {
        if (root == null) {
            return new Pair(0, null);
        }

        Pair left = dfs(root.left);
        Pair right = dfs(root.right);

        // If both subtrees have same depth, current node is LCA
        if (left.depth == right.depth) {
            return new Pair(left.depth + 1, root);
        }

        // Otherwise return the deeper subtree
        return left.depth > right.depth
                ? new Pair(left.depth + 1, left.node)
                : new Pair(right.depth + 1, right.node);
    }

    // --------------------------------------------------
    // Approach 2: BFS to find deepest nodes + LCA
    // --------------------------------------------------
    public TreeNode subtreeWithAllDeepestBFS(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        TreeNode firstDeepest = null;
        TreeNode lastDeepest = null;

        // Level-order traversal to find deepest level
        while (!q.isEmpty()) {
            int size = q.size();
            firstDeepest = q.peek();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);

                lastDeepest = node;
            }
        }

        // Find LCA of the first and last nodes at deepest level
        return lca(root, firstDeepest, lastDeepest);
    }

    private TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }

        TreeNode left = lca(root.left, p, q);
        TreeNode right = lca(root.right, p, q);

        if (left != null && right != null) {
            return root;
        }

        return left != null ? left : right;
    }
}
