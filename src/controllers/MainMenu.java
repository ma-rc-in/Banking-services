package controllers;

import models.*;

import java.util.Scanner;
import java.util.ArrayList;

public class MainMenu {

    User user;
    Scanner myScanner = new Scanner(System.in);

    public MainMenu(User user) {
        this.user = user;
        System.out.println("Welcome Back " + user.getForename());
        getAccount();
    }

    private void getAccount() {
        if (user.getAccount().size() > 0) {
            System.out.println("Which account would you like to access?");
            int counter = 1;
            for (Account a : user.getAccount()) {
                System.out.println(counter + " - " + a.accType);
                counter++;
            }
            try {
                String input = myScanner.nextLine();
                Account a = user.getAccount().get((Integer.parseInt(input)) - 1);
                menu(a);
            } catch (Exception ex) {
                System.out.println("Please enter a valid input");
                getAccount();
            }
        } else {
            System.out.println("You do not currently hold an account with us.");
            System.out.println("Would you like to open a new account now? (y/n)");
            String input = myScanner.nextLine();
            if (input.equalsIgnoreCase("y")) {
                accountChoice();
            } else if (input.equalsIgnoreCase("n")) {
                System.out.println("Very well");
                System.out.println("Do you wish to continue? (y/n)");
                String cont = myScanner.nextLine();
                if (cont.equalsIgnoreCase("y")) {
                    getAccount();
                } else if (cont.equalsIgnoreCase("n")) {
                    System.out.println("You have been logged out .");
                    System.exit(0);
                } else {
                    System.out.println("Invalid input provided, continuing...");
                    getAccount();
                }
            } else {
                System.out.println("Invalid input provided, please try again.");
                getAccount();
            }
        }
    }

    private void accountChoice() {
        System.out.println("What type of account would you like to open?");
        System.out.println("1 - Personal Current Account");
        System.out.println("2 - Savings Account");
        System.out.println("3 - Student Account");
        String input = myScanner.nextLine();

        switch (input) {
            case "1":
                createAccount("Current");
                break;

            case "2":
                createAccount("Savings");
                break;

            case "3":
                createAccount("Student");
                break;

            default:
                System.out.println("Invalid input, please try again.");
                accountChoice();
        }
    }

    private void createAccount(String type) {
        if (type.equals("Current")) {
            CurrentAccount ca = new CurrentAccount(user);
            user.getAccount().add(ca);
        } else if (type.equals("Savings")) {
            SavingsAccount sav = new SavingsAccount(user);
            user.getAccount().add(sav);
        } else {
            StudentAccount stu = new StudentAccount(user);
            user.getAccount().add(stu);
        }
        System.out.println("Congratulations on opening up your new account");
        System.out.println("Returning to main menu...");
        getAccount();
    }
    private void menu(Account account) {
        System.out.println("What would you like to do today?");
        System.out.println("1 - Check Account Balance");
        System.out.println("2 - Make a Withdrawal");
        System.out.println("3 - Make a Deposit");
        System.out.println("4 - Make a Transfer");
        System.out.println("5 - Create a new account");
        System.out.println("6 - Access current accounts");
        System.out.println("7 - Log out");
        String input = myScanner.nextLine();
        getUserChoice(input, account);
    }

    private void getUserChoice(String input, Account account) {

        switch (input) {
            case "1":
                account.currentBalance();
                menu(account);
                break;

            case "2":
                account.withdraw();
                menu(account);
                break;

            case "3":
                account.deposit();
                menu(account);
                break;

            case "4":
                ArrayList<Account> userAccounts = user.getAccount();
                System.out.println("Please select the account to which you want to make the transfer:");
                Account[] accountsList = new Account[10];
                for (Account acc : userAccounts) {
                    if (!acc.accType.equals(account.accType)) {
                        int counter = 1;
                        System.out.println(counter + " " + acc.accType + " account.");
                        accountsList[counter - 1] = acc;
                    }
                }
                String userSelection = myScanner.nextLine();
                Account transferred = accountsList[Integer.parseInt(userSelection)-1];
                account.transfer(account, transferred);
                break;

            case "5":
                accountChoice();
                menu(account);
                break;

            case "6":
                getAccount();
                menu(account);
                break;

            case "7":
                System.out.println("Goodbye and have a nice day!");
                System.exit(0);

            default:
                System.out.println("Invalid input received, please try again");
                menu(account);
        }
    }
}
