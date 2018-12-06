import java.util.*;

public class Account {
	private double balance;
	private ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public Account() {
		
	}
	
	public Account(double b) {
		this.balance = b;
	}
	
	public void deposit(double a) {
		this.balance = this.balance + a;
		transactions.add(new Transaction("Deposit",a));
	}
	
	public void withdrawl(double a) {
		this.balance = this.balance - a;
		transactions.add(new Transaction("Withdrawl",a));
	}
	
	public String toString() {
		return Double.toString(balance);
	}
	
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(double a) {
		balance = a;
	}
	
	public ArrayList<String> getTransactions(){
		ArrayList<String> stringTransactions = new ArrayList<String>();
		for(Transaction t: transactions)
			stringTransactions.add(t.toString());
		return stringTransactions;
	}
	
}
