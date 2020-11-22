package models;

public class Account {

	public AccountBehaviour AccBehaviour;

	public double balance;
	public int overDraftLimit;
	public int maxDeposit;
	public int maxWithdrawal;
	public int maxTransfer;
	public String accType;


	public void deposit(){ AccBehaviour.deposit(this);}

	public void withdraw(){ AccBehaviour.withdraw(this);}

	public void checkBalance(){ AccBehaviour.checkBalance(this);}

	public void transfer(Account account, Account transferred) { AccBehaviour.transfer(account, transferred);}

	public void currentBalance( ){ AccBehaviour.currentBalance(this);}


}
