package models;

public interface AccountBehaviour {

    public void withdraw(Account account);

    public void deposit(Account account);

    public void transfer(Account account, Account transferred);

    public double checkBalance(Account account);

    public void currentBalance(Account account);

    public void setValues(Account account);
}
