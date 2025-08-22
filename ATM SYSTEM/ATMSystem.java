import javax.swing.SwingUtilities;

public class ATMSystem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example user: Arshiya, PIN: 1234
            BankAccount account = new BankAccount("Arshiya", "1234", 10000.00);
            LoginScreen loginScreen = new LoginScreen(account);
        });
    }
}
