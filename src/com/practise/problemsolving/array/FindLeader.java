package com.practise.problemsolving.array;

import java.util.ArrayList;
import java.util.Stack;

public class FindLeader {

    private void firstApproach(int[] arr,int n) {
        ArrayList<Integer> leaderList = new ArrayList<Integer>();
        int leader = 0;
        for (int i = 0; i < arr.length-1; i++) {
                leader = largestElementInRange(arr, arr[i], i+1, arr.length);
                if(leader!=-1){
                    leaderList.add(leader);
                }
        }
    }

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

    private int largestElementInRange(int arr[], int leader, int start, int end) {
        while (start < end) {
            if (leader < arr[start]) {
                return -1;
            }
            start++;
        }
        return leader;
    }

    public static void main(String[] args) {
        FindLeader element = new FindLeader();
        int arr[] = { 1, 3, 2, 3, 3, 3 };

        // { 1, 3, 2, 2, 2 };
        // [1,2,3,3,3]
        // int index[] = { 18,6,6,6,1,-1 };
        // m = element.hashTableApproach(arr);
        // element.firstApproach(arr, arr.length);
        element.bestApproach(arr, arr.length);
        // element.moveahead(arr, 1, 4);
    }

}
