// LeetCode 1382. Balance a Binary Search Tree
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.*;

// Definition for a binary tree node.
// public class TreeNode {
//     int val;
//     TreeNode left;
//     TreeNode right;
//     TreeNode() {}
//     TreeNode(int val) { this.val = val; }
//     TreeNode(int val, TreeNode left, TreeNode right) {
//         this.val = val;
//         this.left = left;
//         this.right = right;
//     }
// }

class Solution {
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;

        List<Integer> inorder = new ArrayList<>();
        inOrder(root, inorder);

        return buildBalanced(inorder, 0, inorder.size() - 1);
    }

    private TreeNode buildBalanced(List<Integer> arr, int l, int r) {
        if (l > r) return null;

        int m = l + (r - l) / 2;
        TreeNode node = new TreeNode(arr.get(m));

        node.left = buildBalanced(arr, l, m - 1);
        node.right = buildBalanced(arr, m + 1, r);

        return node;
    }

    private void inOrder(TreeNode node, List<Integer> arr) {
        if (node == null) return;
        inOrder(node.left, arr);
        arr.add(node.val);
        inOrder(node.right, arr);
    }
}
