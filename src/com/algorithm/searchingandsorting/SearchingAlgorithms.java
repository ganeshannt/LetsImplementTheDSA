package com.algorithm.searchingandsorting;

public class SearchingAlgorithms {

    public static int linearSearch(int arr[],int searchElement){
        for (int i= 0; i < arr.length; i++) {
            if (searchElement == arr[i])
                return i;
        }
        return -1;
    }


    public static void main(String Args[]){
        int arr[] = {23,21,4,2,54,67,86,67,34,7,9,6,43,8,9,665,346,97,546,675,25,76};
        System.out.println(linearSearch(arr, 7));
    }
}
