public class BankAccount {
    private String accountHolder;
    private String pin;
    private double balance;

    public BankAccount(String accountHolder, String pin, double initialBalance) {
        this.accountHolder = accountHolder;
        this.pin = pin;
        this.balance = initialBalance;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public boolean validatePin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public double getBalance() {
        return balance;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }
}
