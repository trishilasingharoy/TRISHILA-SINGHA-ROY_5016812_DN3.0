// Notifier.java
interface Notifier {
    void send(String message);
}

// EmailNotifier.java
class EmailNotifier implements Notifier {

    public void send(String message) {
        System.out.println("Sending Email: " + message);
    }
}

// NotifierDecorator.java
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }


    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

// SMSNotifierDecorator.java
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }


    public void send(String message) {
        super.send(message);
        sendSMS(message);
    }

    private void sendSMS(String message) {
        System.out.println("Sending SMS: " + message);
    }
}

// SlackNotifierDecorator.java
class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }


    public void send(String message) {
        super.send(message);
        sendSlack(message);
    }

    private void sendSlack(String message) {
        System.out.println("Sending Slack message: " + message);
    }
}

// DecoratorPatternTest.java
class DecoratorPatternTest {
    public static void main(String[] args) {
        // Create a base EmailNotifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate with SMSNotifier
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        // Decorate with SlackNotifier
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        // Send a notification through all channels
        slackNotifier.send("Hello, here is your test notification!!");
    }
}
