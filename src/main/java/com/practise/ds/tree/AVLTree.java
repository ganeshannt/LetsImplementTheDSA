package com.practise.ds.tree;

import com.practise.commons.AVLNode;

import static com.practise.commons.Utils.*;

public class AVLTree {

    AVLNode root;

    public AVLTree() {
        this.root = null;
    }


    private AVLNode insertInAVL(AVLNode root, int value) {
        if (root == null) {
            return new AVLNode(value);
        }

        if (value < root.value) {
            root.left = insertInAVL(root.left, value);
        } else if (value > root.value) {
            root.right = insertInAVL(root.right, value);
        }

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        root = balance(root, value);
        return root;
    }

    private AVLNode balance(AVLNode root, int value) {
        int balanceFactor = getBalanceFactor(root);
        if (balanceFactor > 1) {
            if (value < root.left.value) {
                return rightRotation(root);
            }
            root.left = leftRotation(root.left);
        }

        if (balanceFactor < -1) {
            if (value > root.right.value) {
                return leftRotation(root);
            }
            root.right = rightRotation(root.right);
        }
        return root;
    }

    private AVLNode leftRotation(AVLNode root) {
        AVLNode a = root.right;
        AVLNode b = a.left;
        a.left = root;
        root.right = b;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
        return a;
    }

    private AVLNode rightRotation(AVLNode root) {
        AVLNode a = root.left;
        AVLNode b = a.right;
        a.right = root;
        root.left = b;

        root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
        a.height = Math.max(getHeight(a.left), getHeight(a.right)) + 1;
        return a;
    }

    private int getBalanceFactor(AVLNode root) {
        if (root == null) {
            return 0;
        }
        return (getHeight(root.left) - getHeight(root.right));
    }

    private int getHeight(AVLNode root) {
        return (root == null) ? 0 : root.height;
    }

    // Wrapper class

    public void insert(int value) {
        root = insertInAVL(root, value);
    }

    private void preOrder() {
        preOrderTraversal(root);
    }

    private void inOrder() {
        inOrderTraversal(root);
    }

    private void postOrder() {
        postOrderTraversal(root);
    }


    public static void main(String[] args) {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(21);
        avlTree.insert(26);
        avlTree.insert(30);
        avlTree.insert(9);
        avlTree.insert(4);
        avlTree.insert(14);
        avlTree.insert(28);
        avlTree.insert(18);
        avlTree.insert(15);
        avlTree.insert(10);
        avlTree.insert(2);
        avlTree.insert(3);
        avlTree.insert(7);
        avlTree.inOrder();
    }


}
