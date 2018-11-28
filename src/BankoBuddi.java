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
	JLabel username_Create = addLabel("Username",1,1,1,1);
	JTextField usernameEntry_Create = addTextField("",1,2,1,1);
	JLabel password_Create = addLabel("Password",2,1,1,1);
	JTextField passwordEntry_Create = addTextField("",2,2,1,1);
	JLabel initDep_Create = addLabel("Initial Deposit",3,1,1,1);
	JTextField initDepEntry_Create = addTextField("",3,2,1,1);
	JButton create_Create = addButton("Create",3,1,2,1);
	//Login Screen

	//Manager Screen
	
	//Account Screen
	
	//Transaction Screen
	
	public BankoBuddi() {
		add(welcome,1,1,1,1);
		hideCreateAccount();
		hideLoginMenu();
		hideManagerMenu();
		mainMenu();
		
	}
	

	
	//Does stuff with welcomeScreen buttons
	public void buttonClicked(JButton button) {
		//Main Screen
		System.out.println("OKay");
		if(button == login) {
			message("1234567890asdaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
	}


	public static void main(String[] args) {
		screen.setSize(1000, 600);
		screen.setLocation(dim.width / 2 - screen.getSize().width / 2, dim.height / 2 - screen.getSize().height / 2);
		screen.setVisible(true);

	}


	public void loginMenu() {
		//Show login menu

	}
	public void hideLoginMenu() {
		//Hide login menu

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
		usernameEntry_Create.setVisible(true);
		username_Create.setVisible(true);
	}
	public void hideCreateAccount() {
		//hide create account menu
		create_Create.setVisible(false);
		initDepEntry_Create.setVisible(false);
		initDep_Create.setVisible(false);
		passwordEntry_Create.setVisible(false);
		password_Create.setVisible(false);
		usernameEntry_Create.setVisible(false);
		username_Create.setVisible(false);
		
		passwordEntry_Create.setText("");
		usernameEntry_Create.setText("");
	}
	
	public void managerMenu() {
		
	}
	public void hideManagerMenu() {
		
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
