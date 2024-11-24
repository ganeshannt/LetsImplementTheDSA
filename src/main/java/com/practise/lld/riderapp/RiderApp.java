package com.practise.lld.riderapp;

/**
 * @author Ganeshan Nagarajan
 * @since 23/11/24
 */

public class RiderApp {

    public static void main(String[] args) {

        Rider rider1 = new Rider("ganesh");


        for (int i = 0; i < 20; i++) {
            rider1.createRide(i, 50, 60, 2);
            System.out.println("charged amount :" + rider1.closeRide() + " Rider id :" + i);
        }
    }
}
