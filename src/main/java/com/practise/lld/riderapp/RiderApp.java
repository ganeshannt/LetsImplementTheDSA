package com.practise.lld.riderapp;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

public class RiderApp {

    public static void main(String[] args) {

        Rider rider1 = new Rider("ganesh");
        rider1.createRide(1, 10, 30, 1);
        System.out.println("charged amount :" + rider1.closeRide());

        rider1.createRide(1, 50, 30, 2);
        System.out.println("charged amount :" + rider1.closeRide());

    }
}
