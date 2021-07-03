package com.practise.ds.tree;

public class BinaryTree {

    TreeNode root;

    class TreeNode {
        TreeNode left;
        TreeNode right;
        int value;

        public TreeNode(int value) {
            this.value = value;

        }
    }

    public BinaryTree() {
        root = null;
    }

    public TreeNode addInTree(TreeNode root, int value) {
        if (root == null) {
            root = new TreeNode(value);
            return root;
        }
        if (value < root.value) {
            root.left = addInTree(root.left, value);
        } else if (value >= root.value) {
            root.right = addInTree(root.right, value);
        }
        return root;
    }

    /****************************************************************************************/
    // Traversal

    public void preOrderTraversal(TreeNode root){
        if(root == null){
            return;
        }
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
        System.out.println(root.value);
    }


    /****************************************************************************************/

    // Wrapper class

    public void add(int value) {
        addInTree(root, value);
    }
    private void preOrder() {
        preOrderTraversal(root);
    }

    public static void main(String[] args) {
        BinaryTree bt = new BinaryTree();
        bt.add(10);
        bt.add(20);
        bt.add(30);
        bt.add(40);
        bt.add(50);
        bt.add(60);
        bt.preOrder();
    }
}
