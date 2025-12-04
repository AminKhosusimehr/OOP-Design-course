package services;

import constants.Notifier;
import constants.PaymentMethods;

public class ReservationService {
    private IPaymentProcessor paymentMethod;
    private IMessageSender notifier;

    public ReservationService(IPaymentProcessor paymentMethod, IMessageSender notifier) {
        this.paymentMethod = paymentMethod;
        this.notifier = notifier;
    }

    public void makeReservation(Reservation res) {
        System.out.println("Processing reservation for " + res.customer.name);

        // Apply city discount
        if(res.customer.city.equals("Paris")) {
            System.out.println("Apply city discount for Paris!");
            res.room.price *= 0.9;
        }

        // Pay using injected payment method
        paymentMethod.pay(res.totalPrice());

        // Print invoice
        System.out.println("----- INVOICE -----");
        System.out.println("hotel.Customer: " + res.customer.name);
        System.out.println("hotel.Room: " + res.room.number + " (" + res.room.type + ")");
        System.out.println("Total: " + res.totalPrice());
        System.out.println("-------------------");

        // Notify using injected notifier
        notifier.sendMessage(res);
    }
}