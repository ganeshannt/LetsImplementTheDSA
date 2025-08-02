package com.practise.ds.tuf.advance;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class HeapFaq {


    /**
     * PayPal question
     * Calculates the maximum revenue achievable by selling 'm' items.
     * Item prices are dynamic: price = current quantity of that item type.
     * The Strategy involves repeatedly selling one item from the type with the
     * highest current quantity until 'm' items are sold.
     *
     * @param quantity      Initial quantities of each item type.
     * @param customerCount or (m items) Total number of items to sell.
     * @return The maximum possible revenue.
     */
    static long getMaximumRevenue(List<Integer> quantity, int customerCount) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (Integer q : quantity) {
            if (q != 0) maxHeap.add(q);
        }

        long totalRevenue = 0;

        for (int i = 0; i < customerCount; i++) {
            if (!maxHeap.isEmpty()) {
                int maxQuantity = maxHeap.poll();
                totalRevenue += maxQuantity;

                if (maxQuantity > 0) {
                    maxHeap.add(maxQuantity - 1);
                }
            }
        }
        return totalRevenue;
    }


    public int[] maxSubsequence(int[] nums, int k) {

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int i = 0; i < nums.length; i++) {
            pq.offer(new int[]{nums[i], i});
            if (k < pq.size()) {
                pq.poll();
            }
        }

        List<int[]> list = new ArrayList<>();

        list.addAll(pq);

        list.sort(Comparator.comparing(a -> a[1]));

        int[] result = new int[list.size()];
        int count = 0;

        for (int[] n : list) {
            result[count++] = n[0];
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMaximumRevenue(List.of(1, 2, 4), 4));
    }

}
