import java.util.*;

public class Transaction {
	private String type;
	private double amount;
	
	
	public Transaction(String t, double a){
		this.type = t;
		this.amount = a;
	}
	
	public String toString() {
		return (type +": $"+Double.toString(amount));
	}
}
