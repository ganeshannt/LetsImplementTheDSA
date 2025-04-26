package com.practise.lld.ticketbooking.api;

import com.practise.lld.ticketbooking.service.BookingService;
import com.practise.lld.ticketbooking.service.PaymentService;

public class PaymentController {

    private final PaymentService paymentService;
    private final BookingService bookingService;

    public PaymentController(PaymentService paymentService, BookingService bookingService) {
        this.paymentService = paymentService;
        this.bookingService = bookingService;
    }

    public void paymentFailure(String bookingId, String userId) {
        paymentService.processPaymentFailure(bookingService.getBooking(bookingId), userId);
    }

    public void paymentSuccess(String bookingId, String userId) {
        bookingService.confirmBooking(bookingId, userId);
    }
}
