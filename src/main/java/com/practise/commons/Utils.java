package com.practise.commons;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Utils {

    private static final Logger logger = Logger.getLogger(Utils.class.getName());

    private Utils() {
        throw new UnsupportedOperationException("Utility class");
    }

    /************************************* Different swap operations **********************************/

    public static void swapByIndex(int[] arr, int firstIndex, int secondIndex) {
        if (arr == null || firstIndex < 0 || secondIndex < 0 || firstIndex >= arr.length || secondIndex >= arr.length) {
            throw new IllegalArgumentException("Invalid index or array");
        }
        int temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    public static void swapByIndex(List<Integer> list, int firstIndex, int secondIndex) {
        if (list == null || firstIndex < 0 || secondIndex < 0 || firstIndex >= list.size() || secondIndex >= list.size()) {
            throw new IllegalArgumentException("Invalid index or list");
        }
        int temp = list.get(firstIndex);
        list.set(firstIndex, list.get(secondIndex));
        list.set(secondIndex, temp);
    }

    public static void swapByIndex(int[][] arr, int firstIndex, int secondIndex) {
        if (arr == null || firstIndex < 0 || secondIndex < 0 || firstIndex >= arr.length || secondIndex >= arr.length) {
            throw new IllegalArgumentException("Invalid index or array");
        }
        if (firstIndex != secondIndex) {
            int temp = arr[firstIndex][secondIndex];
            arr[firstIndex][secondIndex] = arr[secondIndex][firstIndex];
            arr[secondIndex][firstIndex] = temp;
        }
    }

    /************************************* Print Array and List **********************************/

    public static void printArray(int[] arr) {
        if (arr == null) {
            logger.warning("Array is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder("[ ");
            for (int i : arr) {
                stringBuilder.append(i).append(" ");
            }
            stringBuilder.append("]");
            logger.info(stringBuilder::toString);
        }
    }

    public static void printArray(int[][] arr) {
        if (arr == null) {
            logger.warning("2D Array is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder("[ ");
            for (int[] row : arr) {
                stringBuilder.append("\n[ ");
                for (int val : row) {
                    stringBuilder.append(val).append(" ");
                }
                stringBuilder.append("]");
            }
            stringBuilder.append("\n]");
            logger.info(stringBuilder::toString);
        }
    }

    public static void printList(List<Integer> list) {
        if (list == null) {
            logger.warning("List is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder("[ ");
            for (int val : list) {
                stringBuilder.append(val).append(" ");
            }
            stringBuilder.append("]");
            logger.info(stringBuilder::toString);
        }
    }

    /************************************* Print Linked List **********************************/

    public static void printSingleLinkedList(Node head) {
        if (head == null) {
            logger.warning("Linked List is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (head != null) {
                stringBuilder.append("[ ").append(head.value).append(" ]");
                if (head.next != null) {
                    stringBuilder.append(" -> ");
                }
                head = head.next;
            }
            logger.info(stringBuilder::toString);
        }
    }

    public static boolean isLinkedListEqual(Node head1, Node head2) {
        while (head1 != null && head2 != null) {
            if (head1.value != head2.value) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;
    }

    public static void printDoubleLinkedList(DLLNode head) {
        if (head == null) {
            logger.warning("Doubly Linked List is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder();
            while (head != null) {
                stringBuilder.append("[ ").append(head.value).append(" ]");
                if (head.next != null) {
                    stringBuilder.append(" -> ");
                }
                head = head.next;
            }
            logger.info(stringBuilder::toString);
        }
    }

    /************************************* Binary Tree Traversal **********************************/

    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            logger.info("Tree is empty");
            return;
        }
        logger.info(() -> String.valueOf(root.value));
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            logger.info("Tree is empty");
            return;
        }
        inOrderTraversal(root.left);
        logger.info(() -> String.valueOf(root.value));
        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            logger.info("Tree is empty");
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        logger.info(() -> String.valueOf(root.value));
    }

    /************************************* AVL Tree Traversal **********************************/

    public static void preOrderTraversal(AVLNode root) {
        if (root == null) {
            logger.info("AVL Tree is empty");
            return;
        }
        logger.info(() -> String.valueOf(root.value));
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void inOrderTraversal(AVLNode root) {
        if (root == null) {
            logger.info("AVL Tree is empty");
            return;
        }
        inOrderTraversal(root.left);
        logger.info(() -> String.valueOf(root.value));
        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(AVLNode root) {
        if (root == null) {
            logger.info("AVL Tree is empty");
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        logger.info(() -> String.valueOf(root.value));
    }

    /************************************* Print Map **********************************/

    public static void printMap(Map<?, ?> map) {
        if (map == null) {
            logger.warning("Map is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            map.forEach((k, v) -> logger.info(() -> "Key : " + k + ", Value : " + v));
        }
    }
}
