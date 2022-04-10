package com.practise.random;

import com.practise.commons.TreeNode;
import com.practise.commons.Utils;

/**
 * @author Ganeshan Nagarajan
 * @since 10-04-2022
 */

public class BST {

    TreeNode root;

    BST() {
        this.root = null;
    }

    private TreeNode add(TreeNode root, int value) {

        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (root.value < value)
            root.right = add(root.right, value);
        else
            root.left = add(root.left, value);
        return root;
    }

    private void insert(int i) {
        root = add(root, i);
    }


    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(10);
        bst.insert(20);
        bst.insert(30);
        bst.insert(40);
        bst.insert(5);
        Utils.preOrderTraversal(bst.root);
    }


}
