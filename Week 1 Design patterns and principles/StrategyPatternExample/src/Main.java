interface PaymentStrategy {
    void pay(int amount);
}

// Step 2: Implement Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }


}

class PayPalPayment implements PaymentStrategy {
    private final String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

// Step 3: Implement Context Class
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(int amount) {
        paymentStrategy.pay(amount);
    }
}

// Step 4: Test the Strategy Implementation
class StrategyPatternExample {
    public static void main(String[] args) {
        // Create context
        PaymentContext paymentContext = new PaymentContext();

        // Pay using Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("7834-5678-9876-9980");
        paymentContext.setPaymentStrategy(creditCardPayment);
        paymentContext.executePayment(10000);

        // Pay using PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("tsroy@gmail.com");
        paymentContext.setPaymentStrategy(payPalPayment);
        paymentContext.executePayment(20000);
    }
}