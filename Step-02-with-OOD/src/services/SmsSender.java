class smsSender implements MessageSender {
    @override
    public void sendMessage(String to, String message){
        System.out.println("Sending sms to " + reservation.customer.mobile  + ": " + "Your reservation confirmed!");
    }
}