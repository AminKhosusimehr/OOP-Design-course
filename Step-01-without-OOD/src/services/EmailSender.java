package services;

class EmailSender implements MessageSender{
    @override
    public void sendMessage(String to, String message){
        System.out.println("Sending email to " + to + ": " + message);
    }
}
