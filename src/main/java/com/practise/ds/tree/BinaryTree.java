package com.practise.ds.tree;

import com.practise.commons.TreeNode;

import static com.practise.commons.Utils.*;

public class BinaryTree {

    TreeNode root;

    public BinaryTree() {
        root = null;
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(10, "left");
        bt.add(20, "right");
        bt.add(30, "left");
        bt.add(40, "right");
        bt.add(50, "left");
        bt.add(60, "right");
        TreeNode node = bt.find(30);
        if (node == null) {
            System.out.println("not found");
        } else {
            System.out.println("found");
        }
        printTree(bt.root);
//        bt.delete(50);
        bt.preOrder();
        bt.inOrder();
        bt.postOrder();
    }

    public TreeNode addLeftNode(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        root.left = addLeftNode(root.left, value);
        return root;
    }

    public TreeNode addRightNode(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        root.right = addRightNode(root.right, value);
        return root;
    }

    private TreeNode findTreeNodeByValue(TreeNode root, int value) {
        if (root == null) {
            return null;
        }
        if (root.value == value) return root;
        findTreeNodeByValue(root.left, value);
        findTreeNodeByValue(root.right, value);
        return root;
    }

    /****************************************************************************************/

    // Wrapper class
    public void add(int value, String position) {
        if (position.equals("left")) {
            root = addLeftNode(root, value);
        } else if (position.equals("right")) {
            root = addRightNode(root, value);
        }
    }

    public TreeNode find(int value) {
        return findTreeNodeByValue(root, value);
    }

    public void delete(int value) {
        TreeNode treeNode = find(value);
        if (treeNode == null) {
            System.out.println("element not found");
        } else {
            deleteNode(treeNode);
        }
    }

    //TODO: Implement deleteNode method to handle all the cases(root, full node)
    private TreeNode deleteNode(TreeNode treeNode) {
        if (treeNode.left == null && treeNode.right == null) {
            return null;
        }
        if (treeNode.left == null) {
            return treeNode.right;
        }
        if (treeNode.right == null) {
            return treeNode.left;
        }
        treeNode.left = deleteNode(treeNode.left);
        return treeNode;
    }

    private void preOrder() {
        System.out.println();
        System.out.println("PreOrder Traversal :");
        preOrderTraversal(root);
    }

    private void inOrder() {
        System.out.println();
        System.out.println("InOrder Traversal :");
        inOrderTraversal(root);
    }

    private void postOrder() {
        System.out.println();
        System.out.println("PostOrder Traversal :");
        postOrderTraversal(root);
    }
}
