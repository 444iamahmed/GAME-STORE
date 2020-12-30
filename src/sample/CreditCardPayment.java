package sample;

public class CreditCardPayment extends Payment{
    String CVV, ExpirationDate,CreditCardNumber;

    private CreditCardPayment(){

    }
    public static CreditCardPayment getInstance()
    {
        if(instance == null)
            return new CreditCardPayment();
        return (CreditCardPayment) instance;
    }


    @Override
    public void paymentDetails() {
    }
    @Override
    public void makePayment() {

    }
}
