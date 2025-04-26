package com.practise.ds.tree;

import com.practise.commons.TreeNode;

import static com.practise.commons.Utils.printTree;

public class BinarySearchTree {

    TreeNode root;

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(21);
        bst.insert(26);
        bst.insert(30);
        bst.insert(9);
        bst.insert(4);
        bst.find(9);
        bst.delete(9);
        bst.delete(21);
        bst.delete(4);
        printTree(bst.root);
    }

    /**
     * Inserts a value into the Binary Search Tree.
     * Time Complexity: O(log n) on average, O(n) in the worst case.
     * Space Complexity: O(h) where h is the height of the tree.
     */
    public void insert(int value) {
        root = insertInBST(root, value);
    }

    private TreeNode insertInBST(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.value) {
            root.left = insertInBST(root.left, value);
        } else {
            root.right = insertInBST(root.right, value);
        }
        return root;
    }

    /**
     * Finds a value in the Binary Search Tree.
     * Time Complexity: O(log n) on average, O(n) in the worst case.
     * Space Complexity: O(h) where h is the height of the tree.
     */
    private TreeNode find(int value) {
        return findInBST(root, value);
    }

    private TreeNode findInBST(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) {
            return root;
        }
        if (value < root.value) {
            return findInBST(root.left, value);
        } else {
            return findInBST(root.right, value);
        }
    }

    /**
     * Deletes a value from the Binary Search Tree.
     * Time Complexity: O(log n) on average, O(n) in the worst case.
     * Space Complexity: O(h) where h is the height of the tree.
     */
    private void delete(int value) {
        root = deleteInBST(root, value);
    }

    private TreeNode deleteInBST(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (value < root.value) {
            root.left = deleteInBST(root.left, value);
        } else if (value > root.value) {
            root.right = deleteInBST(root.right, value);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.value = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteInBST(root.right, root.value);
        }
        return root;
    }

    /**
     * Finds the minimum value in the tree.
     * Time Complexity: O(h) where h is the height of the tree.
     * Space Complexity: O(1).
     */
    int minValue(TreeNode root) {
        int minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }
}