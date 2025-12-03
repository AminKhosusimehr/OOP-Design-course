package services;

class SmsSender implements MessageSender {

    @Override
    public void sendMessage(String to, String message) {
        System.out.println("Sending Sms to " + to + ": " + message);
    }
}
