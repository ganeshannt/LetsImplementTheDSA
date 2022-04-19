package com.practise.commons;

import java.util.List;

public class Utils {


    /************************************* Different swap operation **********************************/

    public static void swapByIndex(int arr[], int first_index, int second_index) {
        int temp = arr[first_index];
        arr[first_index] = arr[second_index];
        arr[second_index] = temp;
    }

    public static void swapByIndex(List<Integer> list, int first_index, int second_index) {
        int temp = list.get(first_index);
        list.set(first_index, list.get(second_index));
        list.set(second_index, temp);
    }

    public static void swapByIndex(int arr[][], int first_index, int second_index) {
        if (first_index != second_index) {
            int temp = arr[first_index][second_index];
            arr[first_index][second_index] = arr[second_index][first_index];
            arr[second_index][first_index] = temp;
        }
    }

    /************************************* Print Array and List **********************************/

    public static void printArray(int arr[]) {
        System.out.print("[ ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.print(" ]");
        System.out.println();
    }

    public static void printArray(int arr[][]) {
        int row = arr.length;
        int colum = arr[0].length;
        System.out.print("[ ");
        for (int i = 0; i < row; i++) {
            System.out.println();
            System.out.print("[ ");
            for (int j = 0; j < colum; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.print(" ]");
        }
        System.out.println();
        System.out.print("]");
    }

    public static void printList(List<Integer> list) {
        System.out.print("[ ");
        for (int val : list) {
            System.out.println(val);
        }
        System.out.print(" ]");
        System.out.println();
    }

    /************************************* Print Linked List **********************************/

    public static void printSingleLinkedList(Node head) {
        while (head != null) {
            System.out.print("[ ");
            System.out.print(head.value);
            System.out.print(" ]");
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    public static boolean isLinkedListEqual(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (!(head1.equals(head2))) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        if (head1 == null && head2 == null) {
            return true;
        }
        return false;
    }

    public static void printDoubleLinkedList(DLLNode head) {
        while (head != null) {
            System.out.print("[ ");
            System.out.print(head.value);
            System.out.print(" ]");
            if (head.next != null) {
                System.out.print(" -> ");
            }
            head = head.next;
        }
        System.out.println();
    }

    /************************************* Binary Tree Traversal **********************************/

    /*
     * TC = o(n)
     * root->left->right
     * print value when you visit node for first time.
     * */
    public static void preOrderTraversal(TreeNode root) {

        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /*
     * TC = o(n)
     * left->root->right
     * print value when you visit node for first time.
     * */
    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value + " ");
        inOrderTraversal(root.right);
    }

    /*
     * TC = o(n)
     * left->right->root
     * print value when you visit node for first time.
     * */
    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    /************************************* AVL Tree Traversal **********************************/

    /*
     * TC = o(n)
     * root->left->right
     * print value when you visit node for first time.
     * */
    public static void preOrderTraversal(AVLNode root) {

        if (root == null) {
            System.out.println();
            return;
        }
        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /*
     * TC = o(n)
     * left->root->right
     * print value when you visit node for first time.
     * */
    public static void inOrderTraversal(AVLNode root) {
        if (root == null) {
            System.out.println();
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value + " ");
        inOrderTraversal(root.right);
    }

    /*
     * TC = o(n)
     * left->right->root
     * print value when you visit node for first time.
     * */
    public static void postOrderTraversal(AVLNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }
}
