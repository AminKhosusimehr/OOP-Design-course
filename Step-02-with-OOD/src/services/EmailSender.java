package services;

class EmailSender implements MessageSender{
    @override
    public void sendMessage(Reservation reservation){
        System.out.println("Sending email to " + reservation.customer.email + ": " + "Your reservation confirmed!");
    }
}
