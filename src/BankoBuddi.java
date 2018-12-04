import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.*;
import BreezySwing.*;
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
	
	//Transaction Screen
	
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
		//screen.setBackground(Color.BLACK);
		hideCreateAccount();
		hideLoginMenu();
		hideManagerMenu();
		mainMenu();
	}
	

	
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
				hideCreateAccount();
				mainMenu();
			}
		}
		else if (button == cancel_Create) {
			hideCreateAccount();
			mainMenu();
		}	
		
		
		//Login
		if(button == login_Login) {
			if(!users.containsKey(usernameEntry_Login.getText()))
				message("Incorrect Usename or Password");
			else if(!users.get(usernameEntry_Login.getText()).containsKey(passwordEntry_Login.getText()))
				message("Incorrect Username or Password");
			else
				message("Login good");
			
			
		}
		else if (button == 	cancel_Login) {
			hideLoginMenu();
			mainMenu();
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
		
	}
	public void hideAccountMenu() {
		
	}
	public void tranactionMenu() {
		
	}
	public void hideTranactionMenu() {
		
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
}
