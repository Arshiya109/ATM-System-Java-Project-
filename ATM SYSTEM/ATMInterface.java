import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ATMInterface extends JFrame implements ActionListener {
    private final BankAccount account;
    private final JLabel messageLabel, balanceLabel, welcomeLabel;
    private final JTextField amountField;
    private final JButton withdrawBtn, depositBtn, checkBalanceBtn, exitBtn;

    public ATMInterface(BankAccount account) {
        this.account = account;

        // Frame settings
        setTitle("ATM Machine");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel header = new JPanel();
        welcomeLabel = new JLabel("🙏🏻 HELLO " + account.getAccountHolder().toUpperCase());
        welcomeLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        welcomeLabel.setForeground(Color.WHITE);
        header.setBackground(new Color(52, 152, 219));
        header.add(welcomeLabel);
        add(header, BorderLayout.NORTH);

        // Center Panel (Main Options)
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(5, 1, 10, 10));
        center.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        balanceLabel = new JLabel("Balance: ₹" + account.getBalance(), SwingConstants.CENTER);
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));

        amountField = new JTextField();
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        amountField.setBorder(BorderFactory.createTitledBorder("Enter Amount"));

        withdrawBtn = new JButton("Withdraw");
        depositBtn = new JButton("Deposit");
        checkBalanceBtn = new JButton("Check Balance");
        exitBtn = new JButton("Exit");

        JButton[] buttons = {withdrawBtn, depositBtn, checkBalanceBtn, exitBtn};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
            btn.setBackground(new Color(41, 128, 185));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.addActionListener(this);
        }

        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        messageLabel.setForeground(new Color(192, 57, 43));

        center.add(balanceLabel);
        center.add(amountField);
        center.add(withdrawBtn);
        center.add(depositBtn);
        center.add(checkBalanceBtn);

        add(center, BorderLayout.CENTER);
        add(exitBtn, BorderLayout.SOUTH);
        add(messageLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = amountField.getText().trim();
        double amount = 0;

        if (!input.isEmpty()) {
            try {
                amount = Double.parseDouble(input);
            } catch (NumberFormatException ex) {
                messageLabel.setText("⚠ Invalid amount entered!");
                return;
            }
        }

        if (e.getSource() == withdrawBtn) {
            if (account.withdraw(amount)) {
                messageLabel.setText("✅ Withdrawal of ₹" + amount + " successful!");
            } else {
                messageLabel.setText("❌ Insufficient Balance or Invalid Amount!");
            }
        } else if (e.getSource() == depositBtn) {
            if (amount > 0) {
                account.deposit(amount);
                messageLabel.setText("✅ Deposit of ₹" + amount + " successful!");
            } else {
                messageLabel.setText("⚠️ Enter a valid deposit amount!");
            }
        } else if (e.getSource() == checkBalanceBtn) {
            messageLabel.setText("💰 Current Balance: ₹" + account.getBalance());
        } else if (e.getSource() == exitBtn) {
            JOptionPane.showMessageDialog(this, "Thank you for using ATM! Goodbye 👋🏻");
            System.exit(0);
        }

        balanceLabel.setText("Balance: ₹" + account.getBalance());
        amountField.setText("");
    }
}
