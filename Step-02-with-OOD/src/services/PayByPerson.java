class payByPerson implements PaymentProcessor {
    @override
    public void payByPerson(double amount){ System.out.println("Paid by person" + amount);}
}