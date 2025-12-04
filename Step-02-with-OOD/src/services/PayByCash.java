class payByCash implements PaymentProcessor{
    @override
    public void pay(double amount){ System.out.println("Paid by cash: " + amount); }
}