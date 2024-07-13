package com.practise.ds.linkedlist.problem;

import com.practise.commons.DLLNode;

import java.util.HashMap;
import java.util.Map;

public class DLLProblems {

    DLLNode head;

    public static void main(String[] args) {
        DLLProblems dllProblems = new DLLProblems();

    }

    private DLLNode insert(int data) {
        DLLNode newNode = new DLLNode(data);
        if (head == null) {
            head = newNode;
            return head;
        }
        newNode.next = head.next;
        head = newNode;
        return head;
    }


    /*
    Name - Copy List with Random Pointer
    Link - https://leetcode.com/problems/copy-list-with-random-pointer/
    Time Complexity - O(n)
    Space Complexity - o(n) - used hashmap
    Note - use hash map to store value and address of node then create clone list
    */


    private void cloneDLLApproach1(DLLNode head) {
        Map<Integer, Integer> arbitraryHashTable = new HashMap<Integer, Integer>();
        Map<Integer, DLLNode> actualHashTable = new HashMap<Integer, DLLNode>();
        DLLNode temp = head;
        DLLNode cloneList = head;
        while (temp != null) {
            if (temp.previous != null) {
                arbitraryHashTable.put(temp.value, temp.previous.value);
            }
            cloneList = insert(temp.value);
            actualHashTable.put(cloneList.value, cloneList.next);
        }
        DLLNode cloneTemp = cloneList;
        while (cloneTemp != null) {
            cloneTemp.previous = actualHashTable.get(arbitraryHashTable.get(cloneTemp.value));
        }
    }


    /*
    Name - Copy List with Random Pointer
    Link - https://leetcode.com/problems/copy-list-with-random-pointer/
    Time Complexity - O(n)
    Space Complexity - o(1)
    Note - Create copy of current and attach that copy node next to current node and split list
    */
    private void cloneDLLApproach2(DLLNode head) {
        if (head == null) return;
        DLLNode temp = head;
        // alternatively create node
        while (temp != null) {
            DLLNode newNode = new DLLNode(temp.value);
            newNode.next = temp.next;
            temp.next = newNode;
            temp = temp.next.next;
        }
        temp = head;
        // if condition needed to handle the edge case - null
        while (temp != null) {
            if (temp.previous != null) {
                temp.next.previous = temp.previous.next;
            }
            temp = temp.next.next;
        }
        temp = head;
        DLLNode clone = head.next;
        DLLNode cloneHead = clone;
        while (temp != null && clone != null) {
            temp.next = (temp.next != null) ? temp.next.next : null;
            clone.next = (clone.next != null) ? clone.next.next : null;
            temp = temp.next;
            clone = clone.next;
        }
    }

}
