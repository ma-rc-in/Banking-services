package controllers;

import models.Account;
import models.AccountBehaviour;

import java.util.Scanner;

public class LimitedAccess extends Account implements AccountBehaviour {

	Scanner myScanner = new Scanner(System.in);

	@Override
	public void withdraw(Account account) {
		System.out.println("How much would you like to withdraw today?");
		int amount = Integer.parseInt(myScanner.nextLine());
		if (amount > account.maxWithdrawal){
			System.out.println("Sorry, maximum withdrawl for this account is £300. Please enter a new value.");
			withdraw(account);
		}
		if (amount <= (int) checkBalance(account)) {
			System.out.println("You have successfully withdrawn £" + amount);
			account.balance -= amount;
			System.out.println("Current balance is: £" + checkBalance(account));
		}
		else {
			System.out.println("Sorry, you do not have sufficient funds available to complete this transaction.");
			System.out.println("Would you like to check your current balance? (y/n)");
			String input = myScanner.nextLine();
			if (input.equalsIgnoreCase("y")) {
				System.out.println("Your current balance is: £" + checkBalance(account));
				String retry = myScanner.nextLine();
				if (retry.equalsIgnoreCase("y")) {
					withdraw(account);
				}
			}
		}
	}

	@Override
	public void deposit(Account account) {

		System.out.println("How much would you like to deposit today?");
		int amount = Integer.parseInt(myScanner.nextLine());
		if (amount > account.maxDeposit){
			System.out.println("Sorry, maximum deposit for this account is £500. Please enter a new value.");
			deposit(account);
		}
		if (amount <= account.maxDeposit) {
			System.out.println("You have successfully deposit £" + amount + " into your account.");
			account.balance += amount;
			System.out.println("Current balance is: £" + checkBalance(account));
		}
		else {
			System.out.println("Sorry, deposit cannot exceed £10.000");
			System.out.println("Would you like to check your current balance? (y/n)");
			String input = myScanner.nextLine();
			if (input.equalsIgnoreCase("y")) {
				System.out.println("Your current balance is: £" + checkBalance(account));
				String retry = myScanner.nextLine();
				if (retry.equalsIgnoreCase("y")) {
					deposit(account);
				}
			}
		}

	}
	@Override
	public void transfer(Account account, Account transferred) {
		System.out.println("Please enter the amount of money to transfer.");
		int transfer = Integer.parseInt(myScanner.nextLine());
		if (transfer <= account.maxTransfer && (transfer <= account.balance)) {
			System.out.println("£" + transfer + " transferred");
			account.balance = account.balance - transfer;
			transferred.balance = transferred.balance + transfer;
			System.out.println("Your current balance is: £" + checkBalance(account));
			System.out.println("The balance on the transferred account is: £" + transferred.balance);
		} else {
			System.out.println("Sorry, transfer cannot exceed £50. Please enter a new value");
			transfer(account, transferred);
		}

	}

	@Override
	public double checkBalance(Account account) {
		return account.balance;
	}
	@Override
	public void currentBalance(Account account) {
		System.out.println("Your current balance is: £" + account.balance);
	}

	@Override
	public void setValues(Account account) {
		account.maxDeposit = 500;
		account.maxWithdrawal = 300;
		account.maxTransfer = 50;
		account.overDraftLimit = 0;

		System.out.println("Would you like to make an initial deposit to your new account (y/n)");
		String input = myScanner.nextLine();

		if (input.equalsIgnoreCase("y")) {
			account.AccBehaviour.deposit(account);
		}
	}
}
