import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ATMGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                BankAccount account = new BankAccount(1000); // Initial balance of ₹1000
                ATMFrame atmFrame = new ATMFrame(account);
                atmFrame.setVisible(true);
            }
        });
    }
}

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: ₹" + amount);
        } else {
            System.out.println("Invalid deposit amount. Please try again.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrawn: ₹" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance. Please try again.");
        }
    }
}

class ATMFrame extends JFrame {
    private JTextField amountField;
    private JLabel balanceLabel;
    private BankAccount account;

    public ATMFrame(BankAccount account) {
        this.account = account;
        initialize();
    }

    private void initialize() {
        setTitle("ATM Machine");
        setBounds(100, 100, 400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel lblBalance = new JLabel("Balance:");
        lblBalance.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblBalance.setBounds(50, 30, 70, 20);
        getContentPane().add(lblBalance);

        balanceLabel = new JLabel("₹" + account.getBalance());
        balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        balanceLabel.setBounds(130, 30, 200, 20);
        getContentPane().add(balanceLabel);

        JLabel lblAmount = new JLabel("Amount:");
        lblAmount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblAmount.setBounds(50, 70, 70, 20);
        getContentPane().add(lblAmount);

        amountField = new JTextField();
        amountField.setBounds(130, 70, 200, 20);
        getContentPane().add(amountField);
        amountField.setColumns(10);

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setBounds(50, 110, 120, 30);
        getContentPane().add(btnDeposit);

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setBounds(210, 110, 120, 30);
        getContentPane().add(btnWithdraw);

        JButton btnCheckBalance = new JButton("Check Balance");
        btnCheckBalance.setBounds(130, 160, 120, 30);
        getContentPane().add(btnCheckBalance);

        btnDeposit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.deposit(amount);
                    updateBalance();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ATMFrame.this, "Please enter a valid amount.");
                }
            }
        });

        btnWithdraw.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    account.withdraw(amount);
                    updateBalance();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ATMFrame.this, "Please enter a valid amount.");
                }
            }
        });

        btnCheckBalance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateBalance();
            }
        });
    }

    private void updateBalance() {
        balanceLabel.setText("₹" + account.getBalance());
    }
}
