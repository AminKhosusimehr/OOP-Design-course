class payByPayPall implements PaymentProcessor{
    @override
    public void payByPayPal(double amount){ System.out.println("Paid by PayPal: " + amount); }
}