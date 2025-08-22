import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LoginScreen extends JFrame implements ActionListener {
    private final BankAccount account;
    private final JTextField nameField;
    private final JPasswordField pinField;
    private final JButton loginBtn;
    private final JLabel statusLabel;

    public LoginScreen(BankAccount account) {
        this.account = account;

        // Frame settings
        setTitle("ATM Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1, 10, 10));

        JLabel title = new JLabel("ATM LOGIN", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));

        nameField = new JTextField();
        nameField.setBorder(BorderFactory.createTitledBorder("Enter Name"));

        pinField = new JPasswordField();
        pinField.setBorder(BorderFactory.createTitledBorder("Enter PIN"));

        loginBtn = new JButton("Login");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(46, 204, 113));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFocusPainted(false);
        loginBtn.addActionListener(this);

        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        add(title);
        add(nameField);
        add(pinField);
        add(loginBtn);
        add(statusLabel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameField.getText().trim();
        String pin = new String(pinField.getPassword());

        if (name.equalsIgnoreCase(account.getAccountHolder()) && account.validatePin(pin)) {
            dispose(); // Close login screen
            new ATMInterface(account); // Open ATM screen
        } else {
            statusLabel.setText("❌ Invalid name or PIN!");
        }
    }
}
