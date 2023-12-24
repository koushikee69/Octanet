import java.util.ArrayList;
import java.util.Scanner;

class Transaction {
    String type;
    double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "type='" + type + '\'' +
                ", amount=" + amount +
                '}';
    }
}

class BankAccount {
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));
    }

    public boolean withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            return true; // Withdrawal successful
        } else {
            return false; // Insufficient funds
        }
    }

    public void transfer(BankAccount targetAccount, double amount) {
        if (balance >= amount) {
            balance -= amount;
            targetAccount.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + targetAccount.hashCode(), amount));
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void displayTransactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }
}

public class ATM {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating a account with an initial balance
        BankAccount account = new BankAccount(5000.0);

        // Interface
        System.out.println("Welcome to the ATM!");
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Check Balance
                    displayBalance(account);
                    break;
                case 2:
                    // Deposit
                    System.out.print("Enter the deposit amount: $");
                    double depositAmount = scanner.nextDouble();
                    deposit(account, depositAmount);
                    break;
                case 3:
                    // Withdraw
                    System.out.print("Enter the withdrawal amount: $");
                    double withdrawalAmount = scanner.nextDouble();
                    withdraw(account, withdrawalAmount);
                    break;
                case 4:
                    System.out.print("Enter transfer amount: ");
                    double transferamount = scanner.nextDouble();
                    BankAccount targetAccount = new BankAccount(0);
                    account.transfer(targetAccount, transferamount);
                    break;
                case 5:
                    account.displayTransactionHistory();
                    break;
                case 6:
                    // Exit
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // display the account balance
    private static void displayBalance(BankAccount account) {
        System.out.println("Account Balance: $" + account.getBalance());
    }

    // deposit money into the account
    private static void deposit(BankAccount account, double amount) {
        account.deposit(amount);
        System.out.println("Deposit successful. New balance: $" + account.getBalance());
    }

    // withdraw money from the account
    private static void withdraw(BankAccount account, double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: $" + account.getBalance());
        } else {
            System.out.println("Insufficient funds. Withdrawal failed.");
        }
    }
}