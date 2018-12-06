import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.*;
import java.util.*;
import BreezySwing.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BankoBuddi extends GBFrame {
	
	TreeMap<String, HashMap<String,Account>> users = new TreeMap<String, HashMap<String,Account>>();
	static Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	static JFrame screen = new BankoBuddi();
	JLabel welcome  = new JLabel("Welcome to BankoBuddi, the most secure and trusted name in banking",SwingConstants.CENTER);
	JButton login = addButton("Login",2,1,1,1);
	JButton createAccount = addButton("Create Account",3,1,1,1);
	JButton managerScreen = addButton("Manager Screen",4,1,1,1);
	//Create Account
	JLabel spacerLeft_Create = addLabel("",1,1,1,1);
	JLabel spacerRight_Create = addLabel("",1,3,1,1);
	JLabel username_Create = new JLabel("Username",SwingConstants.CENTER);
	JTextField usernameEntry_Create = addTextField("",2,2,1,1);
	JLabel usernameConstraints_Create = new JLabel("All usernames must be unique and are case sensitive", SwingConstants.CENTER);
	JLabel password_Create = new JLabel("Password",SwingConstants.CENTER);
	JLabel passwordConstraints_Create = new JLabel("Passwords must contian one lowercase letter, one upercase letter, one number, and be six characters long",SwingConstants.CENTER);
	JTextField passwordEntry_Create = addTextField("",5,2,1,1);
	JLabel initDep_Create = new JLabel("Initial Deposit",SwingConstants.CENTER);
	JTextField initDepEntry_Create = addTextField("",7,2,1,1);
	JLabel initDepConstraints_Create = new JLabel("All deposits must only contain numbers",SwingConstants.CENTER);
	JButton create_Create = addButton("Create",8,2,1,1);
	JButton cancel_Create = addButton("Cancel",9,2,1,1);
	
	
	//Login Screen
	JLabel spacerLeft_Login = addLabel("",1,1,1,1);
	JLabel spacerRight_Login = addLabel("",1,3,1,1);
	JLabel username_Login = new JLabel("Username",SwingConstants.CENTER);
	JTextField usernameEntry_Login = addTextField("",2,2,1,1);
	JLabel password_Login = new JLabel("Password",SwingConstants.CENTER);
	JTextField passwordEntry_Login = addTextField("",4,2,1,1);
	JButton login_Login = addButton("Login",5,2,1,1);
	JButton cancel_Login = addButton("Cancel",6,2,1,1);

	//Manager Screen
	
	//Account Screen
	JLabel welcome_Account  = new JLabel("Welcome to your BankoBuddi Account, how can we assit you today?",SwingConstants.CENTER);
	JLabel spacerLeft_Account = addLabel("",1,1,1,1);
	JLabel spacerRight_Account = addLabel("",1,3,1,1);
	JLabel deposit_Account = new JLabel("Deposit",SwingConstants.CENTER);
	JTextField depositEntry_Account = addTextField("",3,2,1,1);
	JLabel withdrawal_Account = new JLabel("Withdrawal",SwingConstants.CENTER);
	JTextField withdrawalEntry_Account = addTextField("",5,2,1,1);
	JButton withdrawAction_Account = addButton("Withdraw",5,2,1,1);
	JButton depositAction_Account = addButton("Deposit",3,2,1,1);
	JButton viewTransaction_Account = addButton("View Transactions",8,2,1,1);
	JButton showBalance_Account = addButton("Show Balance",9,2,1,1);
	JButton logout_Account = addButton("Logout",10,2,1,1);

	public BankoBuddi() {
		//mainMenu
		add(welcome,1,1,1,1);
		//createAccountMenu
		add(username_Create,1,2,1,1);
		add(password_Create,4,2,1,1);
		add(usernameConstraints_Create,2,2,1,1);
		add(passwordConstraints_Create,5,2,1,1);
		add(initDepConstraints_Create,7,2,1,1);
		add(initDep_Create,6,2,1,1);
		//loginMenu
		add(username_Login,1,2,1,1);
		add(password_Login,3,2,1,1);
		//Account menu
		add(welcome_Account,1,2,1,1);
		add(deposit_Account,2,2,1,1);
		add(withdrawal_Account,4,2,1,1);
		//Hide Everything
		hideAccountMenu();
		hideCreateAccount();
		hideLoginMenu();
		hideManagerMenu();
		
		mainMenu();
	}
	
	Account current = new Account();
	String currentUsername;
	String currentPassword;
	//Does stuff with welcomeScreen buttons
	public void buttonClicked(JButton button) {
		//Main Screen
		if(button == login) {
			hideMainMenu();
			loginMenu();
			
		}
		else if(button == createAccount) {
			hideMainMenu();
			createAccount();
		}
		else if(button == managerScreen) {
			
		}
		
		
		//Create Account
		if(button == create_Create) {
			String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}";
			String temp_username = usernameEntry_Create.getText();
			String temp_password = passwordEntry_Create.getText();
			Double temp_initDep = Double.parseDouble(initDepEntry_Create.getText());
			//tests if that username is taken
			if(users.containsKey(temp_username)) {
				message("Username already exists. Please input a different username.");
			}
			//tests if password is valid
			else if(!temp_password.matches(pattern)) {
				message("Poor password, please try again.");
			}
			//puts user into the treemap
			else {
				users.put(temp_username, new HashMap<String, Account>());
				users.get(temp_username).put(temp_password, new Account(temp_initDep));
				message("Your account has been created");
				hideCreateAccount();
				mainMenu();
			}
		}
		else if (button == cancel_Create) {
			hideCreateAccount();
			mainMenu();
		}	
		
		
		if(button == login_Login) {
			if(!users.containsKey(usernameEntry_Login.getText()))
				message("Incorrect Usename or Password");
			else if(!users.get(usernameEntry_Login.getText()).containsKey(passwordEntry_Login.getText()))
				message("Incorrect Username or Password");
			else {
				current = new Account(users.get(usernameEntry_Login.getText()).get(passwordEntry_Login.getText()).getBalance());
				currentUsername = usernameEntry_Login.getText();
				currentPassword = passwordEntry_Login.getText();
				hideLoginMenu();
				accountMenu();
			}
		}
		else if (button == 	cancel_Login) {
			hideLoginMenu();
			mainMenu();
		}

		//account
		if(button == withdrawAction_Account){
			//Run Withdraw action here
			double amount = Double.parseDouble(withdrawalEntry_Account.getText());
			current.withdrawl(amount);
			withdrawalEntry_Account.setText("");
			message("$"+amount+" Withdrawn, Current Balance: $"+current.getBalance());
		}
		else if(button == depositAction_Account){
			//Run Deposit action here
			double amount = Double.parseDouble(depositEntry_Account.getText());
			current.deposit(amount);
			depositEntry_Account.setText("");
			message("$"+amount+" Deposited, Current Balance: $"+current.getBalance());
		}
		else if(button == viewTransaction_Account) {
			transaction(current.getTransactions());
//			hideAccountMenu();
//			tranactionMenu();
		}
		else if(button == logout_Account){
			users.get(currentUsername).get(currentPassword).setBalance(current.getBalance());
			current = new Account();
			currentUsername = "";
			currentPassword = "";
			hideAccountMenu();
			mainMenu();
		}
		else if(button == showBalance_Account) {
			message("Your Current Balance is $"+current.getBalance());
		}

	}


	public static void main(String[] args) {
		screen.setSize(1000, 600);
		screen.setLocation(dim.width / 2 - screen.getSize().width / 2, dim.height / 2 - screen.getSize().height / 2);
		screen.setVisible(true);

	}


	public void loginMenu() {
		//Show login menu
		cancel_Login.setVisible(true);
		spacerLeft_Login.setVisible(true);
		spacerRight_Login.setVisible(true);
		username_Login.setVisible(true);
		password_Login.setVisible(true);
		login_Login.setVisible(true);
		cancel_Login.setVisible(true);
		username_Login.setVisible(true);
		passwordEntry_Login.setVisible(true);
		usernameEntry_Login.setVisible(true);
		}
	public void hideLoginMenu() {
		//Hide login menu
		spacerLeft_Login.setVisible(false);
		spacerRight_Login.setVisible(false);
		username_Login.setVisible(false);
		password_Login.setVisible(false);
		login_Login.setVisible(false);
		cancel_Create.setVisible(false);
		username_Login.setVisible(false);
		passwordEntry_Login.setVisible(false);
		usernameEntry_Login.setVisible(false);
		cancel_Login.setVisible(false);
		
		
		passwordEntry_Login.setText("");
		usernameEntry_Login.setText("");

	}
	
	public void mainMenu() {
		//Show main menu
		welcome.setVisible(true);
		login.setVisible(true);
		createAccount.setVisible(true);
		managerScreen.setVisible(true);
	}
	public void hideMainMenu() {
		//Hide main menu
		welcome.setVisible(false);
		login.setVisible(false);
		createAccount.setVisible(false);
		managerScreen.setVisible(false);
	}

	public void createAccount() {
		//Show create account menu
		create_Create.setVisible(true);
		initDepEntry_Create.setVisible(true);
		initDep_Create.setVisible(true);
		passwordEntry_Create.setVisible(true);
		password_Create.setVisible(true);
		passwordConstraints_Create.setVisible(true);
		usernameEntry_Create.setVisible(true);
		username_Create.setVisible(true);
		spacerRight_Create.setVisible(true);
		spacerLeft_Create.setVisible(true);
		cancel_Create.setVisible(true);
		usernameConstraints_Create.setVisible(true);
		initDepConstraints_Create.setVisible(true);
	}
	public void hideCreateAccount() {
		//hide create account menu
		create_Create.setVisible(false);
		initDepEntry_Create.setVisible(false);
		initDep_Create.setVisible(false);
		passwordEntry_Create.setVisible(false);
		passwordConstraints_Create.setVisible(false);
		password_Create.setVisible(false);
		usernameEntry_Create.setVisible(false);
		username_Create.setVisible(false);
		spacerLeft_Create.setVisible(false);
		spacerRight_Create.setVisible(false);
		cancel_Create.setVisible(false);
		usernameConstraints_Create.setVisible(false);
		initDepConstraints_Create.setVisible(false);

		passwordEntry_Create.setText("");
		usernameEntry_Create.setText("");
		initDepEntry_Create.setText("");
	}
	
	public void managerMenu() {
		
	}
	public void hideManagerMenu() {
		
	}
	
	public void accountMenu() {		
		welcome_Account.setVisible(true);
		spacerLeft_Account.setVisible(true);
		spacerRight_Account.setVisible(true);
		deposit_Account.setVisible(true);
		depositEntry_Account.setVisible(true);
		withdrawal_Account.setVisible(true);
		withdrawalEntry_Account.setVisible(true);
		withdrawAction_Account.setVisible(true);
		depositAction_Account.setVisible(true);
		logout_Account.setVisible(true);
		viewTransaction_Account.setVisible(true);
		showBalance_Account.setVisible(true);


	}
	public void hideAccountMenu() {
		welcome_Account.setVisible(false);
		spacerLeft_Account.setVisible(false);
		spacerRight_Account.setVisible(false);
		deposit_Account.setVisible(false);
		depositEntry_Account.setVisible(false);
		withdrawal_Account.setVisible(false);
		withdrawalEntry_Account.setVisible(false);
		withdrawAction_Account.setVisible(false);
		depositAction_Account.setVisible(false);
		logout_Account.setVisible(false);
		viewTransaction_Account.setVisible(false);
		showBalance_Account.setVisible(false);


		depositEntry_Account.setText("");
		withdrawalEntry_Account.setText("");
	}
	
	
	
	public void message(String m) {
		JFrame pop = new messageBox(m);
		pop.setSize((m.length()*10), 100);
		pop.setLocation(dim.width / 2 - pop.getSize().width / 2, dim.height / 2 - pop.getSize().height / 2);
		pop.setVisible(true);
	}
	
	public class messageBox extends GBFrame{
		JLabel message = new JLabel("",SwingConstants.CENTER);
		JButton ok = addButton("Ok",2,1,1,1);
		public messageBox(String M){
			add(message);
			message.setText(M);
		}
		
		public void buttonClicked(JButton button) {
			setVisible(false);
		}
	}
	
	
	
	public void transaction(ArrayList<String> m) {
		JFrame pop = new transactionBox(m);
		pop.setSize(500, m.size()*100);
		pop.setLocation(dim.width / 2 - pop.getSize().width / 2, dim.height / 2 - pop.getSize().height / 2);
		pop.setVisible(true);
	}
	
	public class transactionBox extends GBFrame{
		JTextArea transactions = addTextArea("",3,1,1,10);
		JLabel welcome = new JLabel("Here you can see all the transactions on your account",SwingConstants.CENTER);
		JButton ok = addButton("Close",2,1,1,1);
		public transactionBox(ArrayList<String> M){
			add(welcome,1,1,1,1);
			transactions.setEditable(false);
			for(String s: M)
				transactions.append(s + "\n");
		}
		
		public void buttonClicked(JButton button) {
			setVisible(false);
		}
	}
	
}
