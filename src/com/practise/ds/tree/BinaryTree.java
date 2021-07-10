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

    public boolean find(int value) {
        TreeNode node = root;
        while (node != null) {
            if (value == node.value) {
                return true;
            }
            if (value < node.value) {
                node = node.left;
            } else if (value > node.value) {
                node = node.right;
            }
        }
        return false;
    }

    public TreeNode deleteInTree(TreeNode root, int value){
       if(root == null) {
           return null;
       }

       if(value <root.value){
           root.left = deleteInTree(root.left, value);
       } else if (value > root.value){
           root.right = deleteInTree(root.right, value);
       }
       
       else {
           if(root.left == null){
               return root.right;
           }
           if(root.right == null){
               return root.left;
           }
           root.value = getLeftSubTreeMaxValue(root.left);
           root.left = deleteInTree(root.left, root.value);
       }
       return root;
    }


    private int getLeftSubTreeMaxValue(TreeNode root) {
        int max = 0;
        while (root != null) {
            if (max < root.value) {
                max = root.value;
            }
            root = root.right;
        }
        return max;
    }

    /****************************************************************************************/
    // Traversal

    public void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
        System.out.println(root.value);
    }

    public void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.println(root.value);
        inOrderTraversal(root.right);
    }

    public void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.println(root.value);
    }

    /****************************************************************************************/

    // Wrapper class

    public void add(int value) {
        root = addInTree(root, value);
    }

    public void delete(int value) {
        root = deleteInTree(root, value);
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
