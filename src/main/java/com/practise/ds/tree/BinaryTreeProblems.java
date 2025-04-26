package com.practise.ds.tree;

import com.practise.commons.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeProblems {

    public int maxDepthOrHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(maxDepthOrHeight(root.left), maxDepthOrHeight(root.right));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null || q == null) {
            return false;
        }

        if (p.value != q.value) {
            return false;
        }

        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public boolean isBalanced(TreeNode root) {
        return heightDifference(root) != -1;
    }

    public int heightDifference(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = heightDifference(root.left);
        int right = heightDifference(root.right);

        if (left == -1 || right == -1) return -1;

        if (Math.abs(left - right) > 1) return -1;

        return Math.max(left, right) + 1;
    }


    /*
     * BFS or Level Order Traversal
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * */
    public List<List<Integer>> levelOrderTraversal(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) return answer;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            List<Integer> eachLevelNodeList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();
                eachLevelNodeList.add(node.value);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            answer.add(eachLevelNodeList);
        }
        return answer;
    }

    /*
     * ZigZag Traversal of a binary tree using level order traversal(BFS)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * */
    public List<List<Integer>> ZigZagTraversal(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) return answer;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // leftToRight flag to alternate the order of traversal - modified BFS or level order traversal
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> eachLevelNodeList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.remove();
                if (leftToRight) {
                    eachLevelNodeList.add(node.value);
                } else {
                    // Add to the beginning of the list. If an element exists, it will be shifted to right
                    eachLevelNodeList.add(0, node.value);
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            answer.add(eachLevelNodeList);
            leftToRight = !leftToRight;
        }
        return answer;
    }


    /*
     * Binary tree left side view implementation using level order traversal(BFS)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * */
    public List<Integer> leftSide(TreeNode root) {
        List<Integer> leftSide = new ArrayList<>();
        List<List<Integer>> levelOrder = levelOrderTraversal(root);
        for (List<Integer> level : levelOrder) {
            leftSide.add(level.get(0));
        }
        return leftSide;
    }

    /*
     * Binary tree right side view implementation using level order traversal(BFS)
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     * */
    public List<Integer> rightSide(TreeNode root) {
        List<Integer> rightSide = new ArrayList<>();
        List<List<Integer>> levelOrder = levelOrderTraversal(root);
        for (List<Integer> level : levelOrder) {
            rightSide.add(level.get(level.size() - 1));
        }
        return rightSide;
    }


    /*
     * Binary tree left side view implementation using DFS Preorder traversal with minor change(Root,Right,Left)
     * Time Complexity: O(n)
     * Space Complexity: O(h) where h is the height of the tree
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> rightSide = new ArrayList<>();
        if (root == null) return rightSide;
        recursiveRightSideView(root, 0, rightSide);
        return rightSide;
    }

    private void recursiveRightSideView(TreeNode root, int level, List<Integer> rightSide) {
        if (level == rightSide.size()) {
            rightSide.add(root.value);
        }

        if (root.right != null) {
            recursiveRightSideView(root.right, level + 1, rightSide);
        }
        if (root.left != null) {
            recursiveRightSideView(root.left, level + 1, rightSide);
        }
    }

}