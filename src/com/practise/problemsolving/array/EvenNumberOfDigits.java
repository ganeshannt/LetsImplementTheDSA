package com.practise.problemsolving.array;

import org.junit.Assert;
import org.junit.Test;

public class EvenNumberOfDigits {

    private int bruteForceApproach(int arr[]) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            num = arr[i];
            while (num > 0) {
                num /= 10;
                count++;
            }
            arr[i] = count;
            count = 0;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0)
                count++;
        }
        return count;
    }

    

    @Test
    public void testEvenNumberOfDigits() {
        EvenNumberOfDigits digits = new EvenNumberOfDigits();
        int arr[] = {555,901,482,1771};
        Assert.assertEquals(1, digits.bruteForceApproach(arr));
    }

}