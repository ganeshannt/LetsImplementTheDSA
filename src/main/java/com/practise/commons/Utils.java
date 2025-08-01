package com.practise.commons;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Utils {

    public static final String AVL_TREE_IS_EMPTY = "AVL Tree is empty";
    public static final String TREE_IS_EMPTY = "Tree is empty";
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

//    public static void printList(List<Integer> list) {
//        if (list == null) {
//            logger.warning("List is null");
//            return;
//        }
//        if (logger.isLoggable(Level.INFO)) {
//            StringBuilder stringBuilder = new StringBuilder("[ ");
//            for (int val : list) {
//                stringBuilder.append(val).append(" ");
//            }
//            stringBuilder.append("]");
//            logger.info(stringBuilder::toString);
//        }
//    }

    public static void printList(List<?> list) {

        if (list == null) {
            logger.warning("List is null");
            return;
        }
        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder("[ ");
            for (Object val : list) {
                stringBuilder.append(val).append(" ");
            }
            stringBuilder.append("]");
            logger.info(stringBuilder::toString);
        }

    }


    private static String formatListToString(List<?> list) {
        if (list == null) {
            return "null"; // Represent a null inner list explicitly
        }
        StringBuilder stringBuilder = new StringBuilder("[ ");
        for (Object val : list) {
            // Handle potential null elements within the inner list
            stringBuilder.append(val == null ? "null" : val).append(" ");
        }
        // The original code leaves a trailing space before the closing bracket.
        // If you prefer no trailing space, you could do:
        // if (list.size() > 0) {
        //     stringBuilder.setLength(stringBuilder.length() - 1); // Remove last space
        // }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * Prints the elements of a list of lists using the logger at INFO level.
     * Handles null outer list and null inner lists gracefully.
     * Format: [ [ inner1_elem1 inner1_elem2 ] [ inner2_elem1 ] null ]
     *
     * @param listOfList The list of lists to print.
     */
    public static void printListOfList(List<List<?>> listOfList) {
        if (listOfList == null) {
            logger.warning("List of lists is null");
            return;
        }

        if (logger.isLoggable(Level.INFO)) {
            StringBuilder stringBuilder = new StringBuilder("[ ");

            boolean firstInnerList = true;
            for (List<?> innerList : listOfList) {
                if (!firstInnerList) {
                    stringBuilder.append(", "); // Use comma and space as separator between inner lists
                }

                // Use the helper method to get the string representation of the inner list
                stringBuilder.append(formatListToString(innerList));

                firstInnerList = false;
            }

            // The outer list format starts with "[ " and ends with " ]"
            // If the list was not empty, remove the potentially extra ", " or space before the closing bracket
            // However, the current logic appends ", " *before* subsequent elements,
            // and the formatListToString helper adds its own spaces.
            // Let's refine the outer structure to be cleaner: [inner1, inner2, null]
            // Let's redo the outer loop structure for a cleaner format like [ [..], [..] ]

            stringBuilder = new StringBuilder("["); // Start without space inside bracket

            firstInnerList = true;
            for (List<?> innerList : listOfList) {
                if (!firstInnerList) {
                    stringBuilder.append(", "); // Use comma and space as separator
                }
                stringBuilder.append(formatListToString(innerList)); // Append the formatted inner list
                firstInnerList = false;
            }

            stringBuilder.append("]"); // End without space inside bracket

            logger.info(stringBuilder::toString);
        }
    }

    /************************************* Print Linked List **********************************/

    public static void printSingleLinkedList(SLLNode head) {
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

    public static boolean isLinkedListEqual(SLLNode head1, SLLNode head2) {
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

    public static void printTree(TreeNode root) {
        printTree(root, "", true);
    }

    private static void printTree(TreeNode root, String prefix, boolean isLeft) {
        if (root == null) {
            System.out.println("Empty tree");  // Or handle empty tree differently
            return;
        }
        if (root.right != null) {
            printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
        }

        System.out.println(prefix + (isLeft ? "└── " : "┌── ") + root.value);

        if (root.left != null) {
            printTree(root.left, prefix + (isLeft ? "    " : "│   "), true);
        }
    }


    private static void printTree(AVLNode root, String prefix, boolean isLeft) {
        if (root == null) {
            return;
        }
        System.out.println(prefix + (isLeft ? "├── " : "└── ") + root.value);
        printTree(root.left, prefix + (isLeft ? "│   " : "    "), true);
        printTree(root.right, prefix + (isLeft ? "│   " : "    "), false);
    }

    public static void printTree(AVLNode root) {
        printTree(root, "", true);
    }


    public static void preOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value + " ");
        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }

    /************************************* AVL Tree Traversal **********************************/

    public static void preOrderTraversal(AVLNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public static void inOrderTraversal(AVLNode root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value + " ");
        inOrderTraversal(root.right);
    }

    public static void postOrderTraversal(AVLNode root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value + " ");
    }


    public static void levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();

            System.out.println(node.value);

            if (root.left != null) queue.add(root.left);
            if (root.right != null) queue.add(root.right);
        }
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
