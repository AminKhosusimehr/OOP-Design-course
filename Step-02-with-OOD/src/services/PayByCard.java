class PayByCard implements PaymentProcessor {
    @override
    public void pay(double amount){ System.out.println("Paid by card: " + amount); }
}