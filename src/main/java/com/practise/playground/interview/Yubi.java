package com.practise.playground.interview;


/*
 *
 * int [] h = [150,168,155,180,175,140,145] -ip
 *
 * int [] b = [180,175,145]
 *
 * q = [145,175,180] ->
 *
 *
 * left -> right
 * queue
 *
 * blocking the view for remaining person ->
 *
 * */

import java.util.ArrayList;
import java.util.List;

 class Yubi {


    public static int[] blockingStudent(int[] studentHeightArr) {

        int maxHeightSoFar = Integer.MIN_VALUE;

        List<Integer> resultList = new ArrayList<>();


        for (int i = studentHeightArr.length - 1; i > 0; i--) {
            if (maxHeightSoFar < studentHeightArr[i]) {
                maxHeightSoFar = studentHeightArr[i];
                resultList.add(0, studentHeightArr[i]);
            }
        }

        int[] result = new int[resultList.size()];

        int index = 0;
        for (int e : resultList) {
            result[index++] = e;
        }

        return result;

    }


    public static void main(String[] args) {
        int[] result = blockingStudent(new int[]{150, 168, 155, 180, 175, 140, 145});

        for (int j : result) {
            System.out.println(j);
        }
    }
}
