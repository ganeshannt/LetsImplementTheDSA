package com.practise.ds.tree;

import com.practise.commons.TreeNode;

public class BinarySearchTreeProblems {


    private static boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // Call the helper function with initial min and max values
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) {
            return true;
        }
        // Check if the current node's value is within the valid range
        if (root.value <= min || root.value >= max) {
            return false;
        }
        // Recursively check the left and right subtrees with updated ranges
        return isValidBST(root.left, min, root.value) && isValidBST(root.right, root.value, max);
    }


    /**
     * This function checks if a binary tree is a sum tree.
     * A sum tree is a tree where the value of each node is equal to the sum of the values of its left and right children.
     * An empty tree is considered a sum tree.
     */
    private static boolean isSumTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true; // An empty tree is a sum tree
        }

        return sumTreeUtil(root);
    }

    private static boolean sumTreeUtil(TreeNode node) {
        // If the node is a leaf node, return its value
        if (node == null) {
            return true;
        }
        if (node.left == null && node.right == null) {
            return true;
        }

        // Recursively calculate the sum of left and right subtrees
        int leftSum = node.left != null ? node.left.value : 0;
        int rightSum = node.right != null ? node.right.value : 0;

        if (node.value == leftSum + rightSum && sumTreeUtil(node.left) && sumTreeUtil(node.right)) {
            return true; // If the current node's value is equal to the sum of its children, continue checking
        }
        return false; // If the current node's value is not equal to the sum of its children, it's not a sum tree
    }


    public static void main(String[] args) {

    }
}