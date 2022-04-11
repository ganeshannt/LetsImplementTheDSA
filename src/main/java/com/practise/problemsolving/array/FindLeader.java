package com.practise.problemsolving.array;

import java.util.ArrayList;
import java.util.Stack;

/*

Name - Leaders in an array
Link - https://www.interviewbit.com/problems/leaders-in-an-array/

 */
public class FindLeader {

    public static void main(String[] args) {
        FindLeader element = new FindLeader();
        int arr[] = {1, 3, 2, 3, 3, 3};
        element.bestApproach(arr, arr.length);
        // element.moveahead(arr, 1, 4);
    }

    private void firstApproach(int[] arr, int n) {
        ArrayList<Integer> leaderList = new ArrayList<Integer>();
        int leader = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            leader = largestElementInRange(arr, arr[i], i + 1, arr.length);
            if (leader != -1) {
                leaderList.add(leader);
            }
        }
    }

    /*
    Time Complexity - o(n)
    Space Complexity - o(n)
    Note - Reverse order would be a hint to solve
     */

    private void bestApproach(int[] arr, int n) {
        Stack<Integer> leaderStack = new Stack<>();
        ArrayList<Integer> leaderList = new ArrayList<Integer>();
        int leader = Integer.MIN_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (leader < arr[i]) {
                leader = arr[i];
                leaderStack.push(leader);
            }
        }
        while (!leaderStack.empty()) {
            leaderList.add(leaderStack.pop());
        }
    }

    public ArrayList<Integer> ListBestApproach(ArrayList<Integer> arrayList) {
        Stack<Integer> leaderStack = new Stack<>();
        ArrayList<Integer> leaderList = new ArrayList<Integer>();
        int leader = Integer.MIN_VALUE;
        for (int i = arrayList.size() - 1; i >= 0; i--) {
            if (leader < arrayList.get(i)) {
                leader = arrayList.get(i);
                leaderStack.push(leader);
            }
        }
        while (!leaderStack.empty()) {
            leaderList.add(leaderStack.pop());
        }
        return leaderList;
    }

    private int largestElementInRange(int arr[], int leader, int start, int end) {
        while (start < end) {
            if (leader < arr[start]) {
                return -1;
            }
            start++;
        }
        return leader;
    }

}
